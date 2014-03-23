package com.resilience

import com.resilience.security.Role
import com.resilience.security.User
import com.resilience.security.UserRole
import grails.transaction.Transactional

import javax.servlet.http.HttpServletRequest

@Transactional
class UserService {


    def getUserWithCredentials(HttpServletRequest request) {

        def authString = request.getHeader('Authorization')

        def encodedPair
        def decodedPair
        def credentials
        def username
        def password

        if(!authString) {
            return null
        }

        encodedPair = authString - 'Basic '
        decodedPair = new String(new sun.misc.BASE64Decoder().decodeBuffer(encodedPair));
        credentials = decodedPair.split(':')
        username = credentials[0]
        password = credentials[1]

        User user = User.findByUsername(username)

        return user

    }


    def User registerClient(String username, String password) {
        if (User.findAllByUsername(username).size()) {
            return null;
        }
        User user = new User(
                username: username,
                password: password
        )
        user.save(failOnError: true)
        Role role = Role.findByAuthority("ROLE_USER")
        UserRole userRole = new UserRole()
        userRole.setUser(user)
        userRole.setRole(role)
        userRole.save(failOnError: true)
        return user
    }

}
