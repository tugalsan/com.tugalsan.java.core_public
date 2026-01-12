package com.tugalsan.java.core.jetty.embedded.server;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.EnumSet;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

//https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
public class TS_JettyServer {

    public static void main(int port, Path keyPath, String keyPass) throws Exception {
        var server = TS_JettyUtilSer.createServer(port, keyPath, keyPass);

        var contextCollection = new ContextHandlerCollection();
        contextCollection.addHandler(new GzipHandler());
        server.setHandler(contextCollection);

        var contextHandler1 = new ContextHandler("/u");
        contextHandler1.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request reqJ, HttpServletRequest req, HttpServletResponse res) throws IOException {
                reqJ.setHandled(true);// Mark the request as handled so that it will not be processed by other handlers.
                res.setStatus(200);
                res.setContentType("text/html; charset=UTF-8");
                res.getWriter().print(""
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "  <title>Jetty Hello World Handler</title>"
                        + "</head>"
                        + "<body>"
                        + "  <p>Hello World</p>"
                        + "</body>"
                        + "</html>"
                        + "");
            }
        });
        contextCollection.addHandler(contextHandler1);

        var contextHandler2 = new ServletContextHandler();
        contextHandler2.setContextPath("/shop");
        var servletHolderHello = contextHandler2.addServlet(ServletHello.class, "/cart/*");
        servletHolderHello.setInitParameter("maxItems", "128");
        var filterHolderCS = contextHandler2.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        filterHolderCS.setAsyncSupported(true);
        contextCollection.addHandler(contextHandler2);

        var contextHandler3 = new WebAppContext();
        contextHandler3.setWar("/path/to/webapp.war");
        contextHandler3.setContextPath("/app");
        contextCollection.addHandler(contextHandler2);

        var resourceHandler1 = new ResourceHandler();
        var resourceDirectories = new ResourceCollection();
        resourceDirectories.addPath("/path/to/static/resources/");
        resourceDirectories.addPath("/another/path/to/static/resources/");
        resourceHandler1.setBaseResource(resourceDirectories);
        resourceHandler1.setDirectoriesListed(false);
        resourceHandler1.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler1.setAcceptRanges(true);
        contextCollection.addHandler(resourceHandler1);

        var resourceHandler2 = new ServletContextHandler();
        resourceHandler2.setContextPath("/app");
        var servletHolderRes = resourceHandler2.addServlet(DefaultServlet.class, "/");
        servletHolderRes.setInitParameter("resourceBase", "/path/to/static/resources/");
        servletHolderRes.setAsyncSupported(true);
        contextCollection.addHandler(resourceHandler2);

        var filterHandler = new HandlerWrapper() {
            @Override
            public void handle(String target, Request reqJ, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
                var path = req.getRequestURI();
                if (path.startsWith("/old_path/")) {
                    var uri = reqJ.getHttpURI();
                    var newPath = "/new_path/" + path.substring("/old_path/".length());
                    var newURI = HttpURI.build(uri).path(newPath);
                    reqJ.setHttpURI(newURI);
                }
                super.handle(target, reqJ, req, res);//not jettyRequest.setHandled(true).
            }
        };
        filterHandler.setHandler(contextHandler2);
        contextCollection.addHandler(filterHandler);

        contextCollection.addHandler(new DefaultHandler());
        server.start();
        server.join();
    }

}
