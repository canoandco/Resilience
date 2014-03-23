class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')


        // Mapping API REST Resilience v1

        "/api/v1/login?(.${format})?" {
            controller = "api"
            action = [GET:"login"]
            namespace = 'v1'
        }

        "/api/v1/user?(.${format})?" {
            controller = "api"
            action = [POST:"register"]
            namespace = 'v1'
        }


        "/api/v1/media?(.${format})?/?${id}?" {
            controller = "api"
            action = [POST:"addMedia",PUT:"updateMedia",DELETE:"deleteMedia"]
            namespace = 'v1'
        }

        "/api/v1/mediacategory?(.${format})?" {
            controller = "api"
            action = [GET:"getMediaCategories"]
            namespace = 'v1'
        }

        "/api/v1/mediatype?(.${format})?" {
            controller = "api"
            action = [GET:"getMediaTypes"]
            namespace = 'v1'
        }

        "/api/v1/user/?${id}?/mediacategory?(.${format})?" {
            controller = "api"
            action = [GET:"getMediaCategoriesByUser"]
            namespace = 'v1'
        }

        "/api/v1/mediacategory/?${mediaCategoryId}?/user/?${userId}?/?(.${format})?" {
            controller = "api"
            action = [PUT:"subscribe"]
            namespace = 'v1'
        }

        "/api/v1/user/?${userId}?/mediacategory/?${mediaCategoryId}?/?(.${format})?" {
            controller = "api"
            action = [PUT:"unsubscribe"]
            namespace = 'v1'
        }



	}
}
