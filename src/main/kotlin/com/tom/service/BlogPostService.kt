package com.tom.service

class BlogPostService {

    fun getBlogPost(id: String): String {
        return """
            <h1>First Blog Post</h1>
            <p>Hi There! This is the first post. The id is $id<p>
            <p>Goodbye!<p>
        """.trimIndent()
    }
}