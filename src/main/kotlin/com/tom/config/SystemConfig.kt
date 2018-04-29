package com.tom.config

import com.tom.repository.BlogPostRepository
import com.tom.resource.BlogPostResource
import com.tom.service.BlogPostService

class SystemConfig {
    val dataBaseConnector = DataBaseConnector()

    val blogPostResource = BlogPostResource(this)

    val blogPostService = BlogPostService(this)

    val blogPostRepository = BlogPostRepository(this)
}