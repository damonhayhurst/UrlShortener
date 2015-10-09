class UrlMappings {

	static mappings = {
        "/url/$action?/$id?" (controller: "url")
        "/$shortUrl" (controller: "redirect")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
