package com.resilience

import com.resilience.security.Role
import com.resilience.security.User
import com.resilience.security.UserRole
import grails.transaction.Transactional

@Transactional
class ClientService {


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
