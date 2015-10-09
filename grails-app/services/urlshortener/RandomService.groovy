package urlshortener

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class RandomService {

    static final int randomChars = 5 //number of random characters for url string

    String createShort(){
        String randUrl = RandomStringUtils.random(randomChars, true, true)
        if(!Url.findByShortUrlName(randUrl)){
            return randUrl
        }
        else{
            createShort()
        }
    }
}
