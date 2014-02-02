package com.resilience



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MediaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Media.list(params), model:[mediaInstanceCount: Media.count()]
    }

    def show(Media mediaInstance) {
        respond mediaInstance
    }

    def create() {
        respond new Media(params)
    }

    @Transactional
    def save(Media mediaInstance) {
        if (mediaInstance == null) {
            notFound()
            return
        }

        if (mediaInstance.hasErrors()) {
            respond mediaInstance.errors, view:'create'
            return
        }

        mediaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mediaInstance.label', default: 'Media'), mediaInstance.id])
                redirect mediaInstance
            }
            '*' { respond mediaInstance, [status: CREATED] }
        }
    }

    def edit(Media mediaInstance) {
        respond mediaInstance
    }

    @Transactional
    def update(Media mediaInstance) {
        if (mediaInstance == null) {
            notFound()
            return
        }

        if (mediaInstance.hasErrors()) {
            respond mediaInstance.errors, view:'edit'
            return
        }

        mediaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Media.label', default: 'Media'), mediaInstance.id])
                redirect mediaInstance
            }
            '*'{ respond mediaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Media mediaInstance) {

        if (mediaInstance == null) {
            notFound()
            return
        }

        mediaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Media.label', default: 'Media'), mediaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mediaInstance.label', default: 'Media'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
