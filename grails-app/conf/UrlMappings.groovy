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
        "/api/v1/user/register?(.${format})?" {
            controller = "api"
            action = [POST:"register"]
            namespace = 'v1'
        }


        "/api/v1/mediacategory?(.${format})?" {
            controller = "api"
            action = [GET:"getMediaCategories"]
            namespace = 'v1'
        }

        "/api/v1/user/?${username}?/mediacategory?(.${format})?" {
            controller = "api"
            action = [GET:"getMediaCategoriesByUser"]
            namespace = 'v1'
        }

        "/api/v1/user/?${username}?/mediacategory/?${categoryId}?/?(.${format})?" {
            controller = "api"
            action = [PUT:"subscribe"]
            namespace = 'v1'
        }



	}
}
