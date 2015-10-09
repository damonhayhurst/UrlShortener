package urlshortener
import com.rabbitmq.*

class RedirectController {

    /*
    *Redirects to long url when http://localhost:8080/$shorturlname is entered
    *as a HTTP request
    */


    def index(String shortUrl) {
        def requestedUrl = Url.findByShortUrlName(shortUrl)
//        new Click(url: requestedUrl).save()
        message(shortUrl)
        redirect url:requestedUrl?.url
    }

    def message(String shortUrl){
        rabbitSend 'clicks', "$shortUrl,now"
    }


}
