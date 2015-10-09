package urlshortener

import grails.transaction.Transactional

@Transactional
class MessageService {

    static rabbitSubscribe = 'clicks'

    void handleMessage(message){
        List<String> mlist = Arrays.asList(message.split(","))
        def requested = Url.findByShortUrlName(mlist.get(0))
        println requested
        new Click(url: requested).save()
    }
}
