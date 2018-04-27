package com.tom

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
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

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("{id}")
    fun get(@PathParam("id") id: Int): String {

        val defaultBlogpost = """
            <h1>First Blog Post</h1>
            <p>Hi There! This is the first post. The id is $id<p>
            <p>Goodbye!<p>
        """.trimIndent()

        return defaultBlogpost
    }


}