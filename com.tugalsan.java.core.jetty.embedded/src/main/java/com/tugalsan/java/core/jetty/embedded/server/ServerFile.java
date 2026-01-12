package com.tugalsan.java.core.jetty.embedded.server;

import com.tugalsan.java.core.function.client.TGS_FuncUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTUUtils;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;

public class ServerFile {

    public static void main(String[] args) throws Exception {
        //config
        var port = 9082;
        var shutdown_token = "shutdown_token";

        //create server
        var server = new Server(new TS_JettyThreadPool());
        var connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        //create handler
        var handler = new ResourceHandler();
        handler.setDirectoriesListed(true);
        handler.setWelcomeFiles(new String[]{"index.html"});
        handler.setResourceBase("src");

        //add handlers
        //http://localhost:9082/shutdown?token=shutdown_token
        var handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{
            new ShutdownHandler(shutdown_token, false, false),
            new DefaultHandler(),
            handler
        });
        server.setHandler(handlers);
//        server.setHandler(handler);

        // Start the server! 🚀
        TGS_FuncMTCUtils.run(() -> {
        });
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
