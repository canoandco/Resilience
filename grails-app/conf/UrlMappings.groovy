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
        "/api/v1/register?(.${format})?" {
            controller = "api"
            action = [POST:"register"]
            namespace = 'v1'
        }

        "/api/v1/subscribe?(.${format})?" {
            controller = "api"
            action = [POST:"subscribe"]
            namespace = 'v1'
        }

	}
}
