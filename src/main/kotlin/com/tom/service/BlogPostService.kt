package com.tom.service

import com.tom.config.SystemConfig

class BlogPostService(private val systemConfig: SystemConfig) {

    fun getBlogPost(id: String): String {

        return systemConfig.blogPostRepository.getBlogPost(id.toInt())

//        return """
//            <h1>First Blog Post</h1>
//            <p>Hi There! This is the first post. The id is $id<p>
//            <p>Goodbye!<p>
//        """.trimIndent()
    }
}