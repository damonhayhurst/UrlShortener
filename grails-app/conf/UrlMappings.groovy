class UrlMappings {

	static mappings = {
        "/url/$action?/$id?" (controller: "url")
        "/$shortUrl" (controller: "redirect")
        "/"(view:"/login")
        "500"(view:'/error')
	}
}
