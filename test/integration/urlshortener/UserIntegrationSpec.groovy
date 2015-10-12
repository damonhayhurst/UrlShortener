package urlshortener
/**
 * Created by Damon on 12/10/2015.
 */

import spock.lang.*
class UserIntegrationSpec extends Specification {

    def randomService

    def "Save a user to the database"() {

        given: "A brand new user"
        def user = new User(username: 'user', password: 'password')

        when: "The user is saved"
        user.save()

        then: "It saved successfully and can be added to the database"
        user.errors.errorCount == 0
        user.id != null
        User.get(user.id).username == user.username

    }



}
