package com.resilience.ws

import com.resilience.Media
import com.resilience.MediaCategory
import com.resilience.MediaType
import com.resilience.security.User
import grails.converters.JSON
import grails.converters.XML

class ApiController {

    def userService
    def mediaService

    static namespace = 'v1'

    static allowedMethods = [register: ["POST"], subscribe: ["PUT"], getMediaCategories: ["GET"],
            getMediaCategoriesByUser: ["GET"]]
    static responseFormats = ['json', 'xml']


    def register = {

        if (!params.username || !params.password) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = userService.registerClient(params.username, params.password)

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

    def login = {
        User user = userService.getUserWithCredentials(request)
        if(!user) {
            forward(controller: "error", action: "userNotFound")
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


        if(!params.userId || !params.mediaCategoryId) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = User.findById(params.userId)
        if(!user) {
            forward(controller: "error", action: "userNotFound")
            return
        }

        MediaCategory category = MediaCategory.findById(params.mediaCategoryId)
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

    def addMedia = {
        if(!params.url || !params.mediaTypeId || !params.mediaCategoryId || !params.mediaName || !params.mediaDescription ||
        !params.userId) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        MediaType mediaType = MediaType.findById(params.mediaTypeId)

        if(!mediaType) {
            forward(controller: "error", action: "mediaTypeNotFound")
            return
        }

        User user = User.findById(params.userId)

        if(!user) {
            forward(controller: "error", action: "userNotFound")
            return
        }

        Media media = mediaService.addMedia(mediaType,params.mediaName,params.mediaDescription,params.url,user)


        render withFormat  {
            json {
                render media as JSON
            }
            xml {
                render media as XML
            }
        }

    }

    def deleteMedia = {

        if(!params.id) {
            forward(controller: "error", action: "missingParameter")
            return
        }

       Media media = Media.findById(params.id)

        if(!media) {
            forward(controller: "error", action: "mediaNotFound")
            return
        }

        media.delete()

    }



    def unsubscribe = {


        if(!params.userId || !params.mediaCategoryId) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = User.findById(params.userId)
        if(!user) {
            forward(controller: "error", action: "userNotFound")
            return
        }

        MediaCategory category = MediaCategory.findById(params.mediaCategoryId)
        if(!category) {
            forward(controller: "error", action: "categoryNotFound")
            return
        }

        if(!user.getSubscriptions().contains(category)) {
            forward(controller: "error", action: "notSubscribed")
            return
        }

        user.removeFromSubscriptions(category)

        render withFormat  {
            json {
                render category as JSON
            }
            xml {
                render category as XML
            }
        }
    }


    def getMediaCategories =  {

        Collection<MediaCategory> mediaCategories = MediaCategory.list()

        render withFormat {
            json {
                render mediaCategories as JSON
            }
            xml {
                render mediaCategories as XML
            }
        }

    }

    def getMediaTypes =  {

        Collection<MediaType> mediaTypes = MediaType.list()

        render withFormat {
            json {
                render mediaTypes as JSON
            }
            xml {
                render mediaTypes as XML
            }
        }

    }

    def getMediaCategoriesByUser =  {

        if(!params.id) {
            forward(controller: "error", action: "missingParameter")
            return
        }

        User user = User.findById(params.id)

        if(!user) {
            forward(controller: "error", action: "userNotFound")
            return
        }

        Collection<MediaCategory> mediaCategories = user.getSubscriptions()

        render withFormat {
            json {
                render mediaCategories as JSON
            }
            xml {
                render mediaCategories as XML
            }
        }

    }

}
