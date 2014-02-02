package com.resilience



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MediaCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MediaCategory.list(params), model: [mediaCategoryInstanceCount: MediaCategory.count()]
    }

    def show(MediaCategory mediaCategoryInstance) {
        respond mediaCategoryInstance
    }

    def create() {
        respond new MediaCategory(params)
    }

    @Transactional
    def save(MediaCategory mediaCategoryInstance) {
        if (mediaCategoryInstance == null) {
            notFound()
            return
        }

        if (mediaCategoryInstance.hasErrors()) {
            respond mediaCategoryInstance.errors, view: 'create'
            return
        }

        mediaCategoryInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mediaCategoryInstance.label', default: 'MediaCategory'), mediaCategoryInstance.id])
                redirect mediaCategoryInstance
            }
            '*' { respond mediaCategoryInstance, [status: CREATED] }
        }
    }

    def edit(MediaCategory mediaCategoryInstance) {
        respond mediaCategoryInstance
    }

    @Transactional
    def update(MediaCategory mediaCategoryInstance) {
        if (mediaCategoryInstance == null) {
            notFound()
            return
        }

        if (mediaCategoryInstance.hasErrors()) {
            respond mediaCategoryInstance.errors, view: 'edit'
            return
        }

        mediaCategoryInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MediaCategory.label', default: 'MediaCategory'), mediaCategoryInstance.id])
                redirect mediaCategoryInstance
            }
            '*' { respond mediaCategoryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MediaCategory mediaCategoryInstance) {

        if (mediaCategoryInstance == null) {
            notFound()
            return
        }

        mediaCategoryInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MediaCategory.label', default: 'MediaCategory'), mediaCategoryInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mediaCategoryInstance.label', default: 'MediaCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
