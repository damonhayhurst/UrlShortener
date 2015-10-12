package urlshortener



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UrlController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static scaffold = Url

    def randomService
    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Url.list(params), model:[urlInstanceCount: Url.count()]
    }

    def show(Url urlInstance) {
        respond urlInstance
    }

    def create() {
        respond new Url(params)
    }



    @Transactional
    def save(Url urlInstance) {
        def user = spingSecurityService.currentUser
        if (urlInstance == null) {
            notFound()
            return
        }

        if (urlInstance.hasErrors()) {
            respond urlInstance.errors, view:'create'
            return
        }
        if (!urlInstance.shortUrlName){
            urlInstance.shortUrlName = randomService.createShort()
        }
        urlInstance.user = user
        urlInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'url.label', default: 'Url'), urlInstance.id])
                redirect urlInstance
            }
            '*' { respond urlInstance, [status: CREATED] }
        }
    }

    def edit(Url urlInstance) {
        respond urlInstance
    }

    @Transactional
    def update(Url urlInstance) {
        if (urlInstance == null) {
            notFound()
            return
        }

        if (urlInstance.hasErrors()) {
            respond urlInstance.errors, view:'edit'
            return
        }

        urlInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Url.label', default: 'Url'), urlInstance.id])
                redirect urlInstance
            }
            '*'{ respond urlInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Url urlInstance) {

        if (urlInstance == null) {
            notFound()
            return
        }

        urlInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Url.label', default: 'Url'), urlInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'url.label', default: 'Url'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
