package com.resilience.ws

import com.resilience.MediaCategory
import com.resilience.security.User
import grails.converters.JSON
import grails.converters.XML

class ApiController {

    def clientService

    static namespace = 'v1'

    static allowedMethods = [init: ["POST", "GET"], scan: ["POST"]]
    static responseFormats = ['json', 'xml']


    def register = {
        if (!params.username || !params.password) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = clientService.registerClient(params.username, params.password)

        if (!user) {
            forward(controller: "error", action: "userExists")
            return
        }

        render withFormat  {
            json {
                render user as JSON
            }
            xml {
                render user as XML
            }
        }
    }

    def subscribe = {
        if(!params.username || !params.categoryId) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = User.findByUsername(params.username)
        if(!user) {
            forward(controller: "error", action: "userNotFound")
            return
        }

        MediaCategory category = MediaCategory.findById(params.categoryId)
        if(!category) {
            forward(controller: "error", action: "categoryNotFound")
            return
        }

        if(user.getSubscriptions().contains(category)) {
            forward(controller: "error", action: "alreadySubscribed")
            return
        }

        user.addToSubscriptions(category)

        render withFormat  {
            json {
              render category as JSON
            }
            xml {
              render category as XML
            }
        }
    }
}
