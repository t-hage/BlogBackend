package com.tom.config

import com.tom.Application
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.servlet.ServletContainer
import java.util.*
import javax.servlet.DispatcherType
import javax.ws.rs.core.UriBuilder

class JettyServer {

    private val baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build()
    private val server: Server = JettyHttpContainerFactory.createServer(baseUri, false)

    fun setup(application: Application): JettyServer {
        val servletContext = ServletContextHandler(ServletContextHandler.NO_SESSIONS)

        servletContext.contextPath = "/"
        server.handler = servletContext

        this.setCORS(servletContext, "http://localhost:4200")

        servletContext.addServlet(ServletHolder(ServletContainer(application)), "/api/*")
        return this
    }

    fun start(){
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

    private fun setCORS(servletContext: ServletContextHandler, host: String) {
        val cors = servletContext.addFilter(CrossOriginFilter::class.java, "/*", EnumSet.of<DispatcherType>(DispatcherType.REQUEST))
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, host)
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, host)
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD")
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin")
    }


}