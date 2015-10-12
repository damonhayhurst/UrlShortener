import urlshortener.*

class BootStrap {

    def init = { servletContext ->

        environments {
            development {
                if (!Url.count()) {
                    createSampleData()
                }
            }
            test {
                if (!Url.count()){
                    createSampleData()
                }
            }
        }
    }

    def randomService

    private createSampleData() {
        def r = new Random()
        def benc = new User(username: 'benc', password: 'password', email: "email@mail.com")
        def damon = new User(username: 'damon', password: '1234', email: "email2@mail.com")
        [
         damon, benc
        ].each{user ->
            user.save()
            if(user.hasErrors()){
                log.error "user errors " + user.errors
                return
            }
        }
        [
            new Url(url: 'https://www.facebook.com/', shortUrlName: randomService.createShort(), user:damon),
            new Url(url: 'https://grails.org/plugins/tag/mongodb',  shortUrlName: randomService.createShort(), user:damon)
        ].each{ url->
            url.save()
            if(url.hasErrors()){
                log.error "url errors: "+url.errors
                return
            }

            r.nextInt(16).times{
                Click click = new Click(url: url)
                click.save(failOnError: true)
                if(click.hasErrors()){
                    log.error "click errors: "+click.errors
                    return
                }
            }

        }

        log.info "init'd: "+[urls:Url.count(), clicks:Click.count()]


    }
}
