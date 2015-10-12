package urlshortener



class Url {

    static transients = ['clicks', 'clicksCount']

    static mapping = {sort dateCreated: 'desc'}

    String url
    String shortUrlName //Short url addresses are http://localhost:8080/$shortUrlName
    Date dateCreated

//    static hasMany = [clicks: Click]

    static hasOne = [user : User]
    static belongsTo = [User]
    
    static constraints = {
        url blank: false, url: true
        shortUrlName nullable: true, unique: true, size: 1..9//up to 9 custom chars
    }
    
    /*
    *If the short url name value is left blank, a random set of characters is selected
    *and used for the short urlname value
    */

    public List<Click> getClicks(){
        Click.findAllByUrl(this)
    }

    public Number getClicksCount(){
        Click.countByUrl(this)
    }

    @Override
    String toString() {
        return shortUrlName
    }
}