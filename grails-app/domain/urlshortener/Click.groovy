package urlshortener

import org.bson.types.ObjectId

class Click {

    ObjectId id
    Url url

    Date dateCreated


    static mapWith = "mongo"

//    static belongsTo = [Url]
//    static hasOne = [url : Url]

    static constraints = {
        url nullable: false
    }
}
