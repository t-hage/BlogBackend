package com.tom

import com.tom.config.DataBaseConnector
import com.tom.config.JettyServer
import com.tom.config.SystemConfig
import org.glassfish.jersey.server.ResourceConfig

fun main(args: Array<String>) {
    Application()
}

class Application : ResourceConfig() {
    init {
        val systemConfig = SystemConfig()

        packages("com.tom.resource")
        register(systemConfig.blogPostResource)
        DataBaseConnector()

        //Setup and join server thread
        JettyServer().setup(this).start()
    }
}