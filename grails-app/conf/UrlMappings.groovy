class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')


        // Mapping API REST Resilience v1

        "/api/v1/user" {
            controller = "api"
            action = [GET:"login",POST:"register"]
            namespace = 'v1'
        }


        "/api/v1/media/?${id}?" {
            controller = "api"
            action = [POST:"addMedia",PUT:"updateMedia",DELETE:"deleteMedia"]
            namespace = 'v1'
        }

        "/api/v1/mediacategory" {
            controller = "api"
            action = [GET:"getMediaCategories"]
            namespace = 'v1'
        }

        "/api/v1/mediatype" {
            controller = "api"
            action = [GET:"getMediaTypes"]
            namespace = 'v1'
        }

        "/api/v1/user/?${id}?/mediacategory" {
            controller = "api"
            action = [GET:"getMediaCategoriesByUser"]
            namespace = 'v1'
        }

        "/api/v1/mediacategory/?${mediaCategoryId}?/user/?${userId}?" {
            controller = "api"
            action = [PUT:"subscribe"]
            namespace = 'v1'
        }

        "/api/v1/user/?${userId}?/mediacategory/?${mediaCategoryId}?" {
            controller = "api"
            action = [PUT:"unsubscribe"]
            namespace = 'v1'
        }



	}
}
