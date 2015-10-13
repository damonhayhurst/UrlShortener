package urlshortener

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UrlController)
@Mock([User, Url])
class UrlControllerSpec extends Specification {



    //TODO fix
    def "When a short url is not specified a random url is produced"(){

        given: "A new user"
        def user = new User(username: 'user', password: 'password', email: 'email@mail.com')
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> user
        user.save()

        when: "A new url is created without a specified short url"
        def url = new Url(url: 'https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow', shortUrlName: null, user: user)
        controller.randomService = Mock(RandomService)
        controller.randomService >> 'abc12'
        controller.save(url)


        then: "The short url will be a random string"
        Url.findByUrl('https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow').shortUrlName.count() == RandomService.randomChars
    }
}
