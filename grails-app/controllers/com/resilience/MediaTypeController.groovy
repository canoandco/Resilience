package com.resilience



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MediaTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MediaType.list(params), model: [mediaTypeInstanceCount: MediaType.count()]
    }

    def show(MediaType mediaTypeInstance) {
        respond mediaTypeInstance
    }

    def create() {
        respond new MediaType(params)
    }

    @Transactional
    def save(MediaType mediaTypeInstance) {
        if (mediaTypeInstance == null) {
            notFound()
            return
        }

        if (mediaTypeInstance.hasErrors()) {
            respond mediaTypeInstance.errors, view: 'create'
            return
        }

        mediaTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mediaTypeInstance.label', default: 'MediaType'), mediaTypeInstance.id])
                redirect mediaTypeInstance
            }
            '*' { respond mediaTypeInstance, [status: CREATED] }
        }
    }

    def edit(MediaType mediaTypeInstance) {
        respond mediaTypeInstance
    }

    @Transactional
    def update(MediaType mediaTypeInstance) {
        if (mediaTypeInstance == null) {
            notFound()
            return
        }

        if (mediaTypeInstance.hasErrors()) {
            respond mediaTypeInstance.errors, view: 'edit'
            return
        }

        mediaTypeInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MediaType.label', default: 'MediaType'), mediaTypeInstance.id])
                redirect mediaTypeInstance
            }
            '*' { respond mediaTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MediaType mediaTypeInstance) {

        if (mediaTypeInstance == null) {
            notFound()
            return
        }

        mediaTypeInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MediaType.label', default: 'MediaType'), mediaTypeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mediaTypeInstance.label', default: 'MediaType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
