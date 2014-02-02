import com.resilience.MediaCategory
import com.resilience.security.User

class BootStrap {

    def init = { servletContext ->
        User dummyUser = new User(
                username:"user",
                password:"toto"
        )
        MediaCategory dummyCategory = new MediaCategory(
                mediaCategoryDesc: "testCategory"
        )

        dummyUser.save(failOnError: true)
        dummyCategory.save(failOnError: true)

    }
    def destroy = {
    }
}
