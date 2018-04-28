package com.tom.resource

import com.tom.service.BlogPostService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("blogpost")
class BlogPostResource(private val blogPostService: BlogPostService) {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("{id}")
    fun get(@PathParam("id") id: String): String {
        return  blogPostService.getBlogPost(id)
    }
}