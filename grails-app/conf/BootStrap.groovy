import com.resilience.MediaCategory
import com.resilience.security.Role
import com.resilience.security.User
import com.resilience.security.UserRole

class BootStrap {

    def init = { servletContext ->
        User dummyUser = new User(
                username:"user",
                password:"toto",
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
                mediaCategoryDesc: "testCategory"
        )


        dummyCategory.save(failOnError: true)
        dummyUserRole.save(failOnError: true)

    }
    def destroy = {
    }
}
