package com.tom

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.glassfish.jersey.servlet.ServletContainer

fun main(args: Array<String>) {
    val server: Server = Server(8080)
    val ctx: ServletContextHandler = ServletContextHandler(ServletContextHandler.NO_SESSIONS)

    ctx.contextPath = "/"
    server.handler = ctx

    val servletHolder: ServletHolder = ctx.addServlet(ServletContainer::class.java, "/api/*")
    servletHolder.initOrder = 1
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.tom")

    try {
        server.start()
        server.join()
    } catch (e: Exception) {
        println("Exception has occured: $e")
    } finally {
        println("Destroying Server.")
        server.destroy()
    }
}