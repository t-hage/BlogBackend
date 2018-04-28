package com.tom

import com.tom.resource.BlogPostResource
import com.tom.service.BlogPostService
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer
import java.util.*
import javax.servlet.DispatcherType
import javax.ws.rs.core.UriBuilder


fun main(args: Array<String>) {
    val baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build()
    val server: Server = JettyHttpContainerFactory.createServer(baseUri, false)

    val servletContext = ServletContextHandler(ServletContextHandler.NO_SESSIONS)

    servletContext.contextPath = "/"
    server.handler = servletContext

    val cors = servletContext.addFilter(CrossOriginFilter::class.java, "/*", EnumSet.of<DispatcherType>(DispatcherType.REQUEST))
    cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:4200")
    cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "http://localhost:4200")
    cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD")
    cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin")

    servletContext.addServlet(ServletHolder(ServletContainer(Application())), "/api/*")

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

private class Application : ResourceConfig() {
    init {
        packages("com.tom.resource")
        register(BlogPostResource(BlogPostService()))
    }
}