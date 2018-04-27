package com.tom

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("blogpost")
class BlogPostResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun message(): String {
        val s = "Hello World"
        return s
    }


}