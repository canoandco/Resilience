package com.resilience

import com.resilience.security.User
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
        return user
    }


}
