package urlshortener


import spock.lang.*
/**
 * Created by Damon on 12/10/2015.
 */
class UrlIntegrationSpec extends Specification{

    def randomService

    def "Adding an url links url to user"(){

        given: "A user is registered"
        def user = new User(username: 'user', password: 'password', email: 'email@mail.com')
        user.save(failOnError: true)

        when: "Several urls are added"
        user.addToUrls(new Url(url: 'http://rogerdudler.github.io/git-guide/', shortUrlName: randomService.createShort()))
        user.addToUrls(new Url(url: 'https://www.facebook.com/', shortUrlName: 'fb123'))

        then: "The urls link back to the user"
        user.urls.size() == 2
    }
 //TODO fix
    def  "Ensure urls linked to user can be retrieved by their short name"(){

        given: "A user is registered"
        def user = new User(username: 'user', password: 'password', email: 'email@mail.com')

        and: "Several urls are added"
        def random = randomService.createShort()
        user.addToUrls(new Url(url: 'http://rogerdudler.github.io/git-guide/', shortUrlName: random))
        user.addToUrls(new Url(url: 'https://www.facebook.com/', shortUrlName: 'fb123'))
        user.save(failOnError: true)

        when: "The user is retrieved by their id"
        def foundUser = User.findById(user.id)
        def sortedUrls = foundUser.getUrls()
        def shortenedUrls = sortedUrls.collect{
            it.shortUrlName
        }

        then: "The urls are returned"
        shortenedUrls == [random, 'fb123']

    }
//TODO fix
    def "When a short url is not specified a random url is produced"(){

        given: "A new user"
        def user = new User(username: 'user', password: 'password', email: 'email@mail.com')
        user.save()

        when: "A new url is created without a specified short url"
        def url = new Url(url: 'https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow', shortUrlName: null)


        then: "The short url will be a random string"
        Url.findByUrl('https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow').shortUrlName.count() == randomService.randomChars
    }

}
