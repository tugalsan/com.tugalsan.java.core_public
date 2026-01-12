package com.tugalsan.java.core.jetty.embedded.server;

import com.tugalsan.java.core.function.client.TGS_FuncUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTUUtils;
import java.nio.file.Path;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServerWeb {

    public static void main(String[] args) throws Exception {

        //config
        var embedded = true;
        var port = 8080;
        var shutdown_token = "shutdown_token";
        var resource = Path.of("C:\\me\\codes\\com.tugalsan\\tst\\hello-world-embedded-jetty-maven\\static");

        //create server
        var server = new Server(new TS_JettyThreadPool());
        var connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        //create handler
        var handler = new WebAppContext();
        System.err.println("resource: " + resource);
//        handler.setBaseResource(Resource.newResource(resource));
        handler.setContextPath("/static");
        handler.setWelcomeFiles(new String[]{"index.html"});
        handler.setParentLoaderPriority(embedded);

//        handler.setResourceBase(ServerMain.class.getClassLoader().getResource(
//                "META-INF/resources"// Load static content from inside the jar file.
//        ).toURI().toString());
//        handler.setAttribute(// Look for annotations in the classes directory (dev server) and in the jar file (live server)
//                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
//                ".*/target/classes/|.*\\.jar"
//        );
        ////        handler.addServlet(HelloWorldServlet.class, "/hello");
        //add handlers
        //http://localhost:8080/shutdown?token=shutdown_token
        var handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{
            new ShutdownHandler(shutdown_token, false, false),
            new DefaultHandler(),
            handler
        });
//        server.setHandler(handlers);
        server.setHandler(handler);

        // Start the server! 🚀
        try {
            server.start();
            System.out.println("Server started @ " + port);
        } catch (Exception e) {
            TGS_FuncUtils.throwIfInterruptedException(e);
            server.stop();
            System.out.println("Server failed @ " + port);
//            TGS_FuncUtils.throwIfInterruptedException(e);
            TGS_FuncMTUUtils.thrw(e);
        }
        server.join();
    }
}
