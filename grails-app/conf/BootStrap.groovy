import com.resilience.Media
import com.resilience.MediaCategory
import com.resilience.MediaType
import com.resilience.security.Role
import com.resilience.security.User
import com.resilience.security.UserRole
import com.resilience.tools.Converter

class BootStrap {

    def init = { servletContext ->
        User dummyUser = new User(
                username: "user",
                password: "toto",
                enabled: true
        )

        Role dummyRoleAdmin = new Role(
                authority: "ROLE_ADMIN"
        )

        Role dummyRoleUser = new Role(
                authority: "ROLE_USER"
        )

        dummyUser.save(failOnError: true)
        dummyRoleUser.save(failOnError: true)
        dummyRoleAdmin.save(failOnError: true)

        UserRole dummyUserRole = new UserRole()
        dummyUserRole.setUser(dummyUser)
        dummyUserRole.setRole(dummyRoleUser)

        MediaCategory dummyCategory = new MediaCategory(
                mediaCategoryName: "testName",
                mediaCategoryDesc: "testCategory"
        )

        MediaType dummyType = new MediaType(
                mediaTypeName: "typeTestName",
                mediaTypeDesc: "typeTestDesc"
        )

        dummyType.save(failOnError: true)

        Media dummyMedia = new Media(
                provider: dummyUser,
                mediaType: dummyType,
                mediaLink: "link",
                mediaName: "testMedia",
                mediaDescription: "testMediaDescription"
        )

        dummyMedia.save(failOnError: true)
        dummyCategory.addToMedias(dummyMedia)
        dummyCategory.save(failOnError: true)
        dummyUserRole.save(failOnError: true)

        Converter.registerMarshallers()

    }
    def destroy = {
    }
}

