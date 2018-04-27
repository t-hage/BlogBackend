package com.tom

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.glassfish.jersey.servlet.ServletContainer
import java.util.*
import javax.servlet.DispatcherType


fun main(args: Array<String>) {
    val server: Server = Server(8080)
    val ctx: ServletContextHandler = ServletContextHandler(ServletContextHandler.NO_SESSIONS)

    ctx.contextPath = "/"
    server.handler = ctx

    val cors = ctx.addFilter(CrossOriginFilter::class.java, "/*", EnumSet.of<DispatcherType>(DispatcherType.REQUEST))
    cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:4200")
    cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "http://localhost:4200")
    cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD")
    cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin")


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