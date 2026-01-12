package com.tugalsan.java.core.tomcat.embedded.gwt.server.listeners;

import javax.servlet.*;
import javax.servlet.annotation.*;
import com.tugalsan.java.core.log.server.*;
import com.tugalsan.java.core.time.client.*;

@WebListener
public class TS_TomcatListenerByAnnotation implements ServletContextListener {

    final private static TS_Log d = TS_Log.of(TS_TomcatListenerByAnnotation.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        d.cr("contextInitialized", TGS_Time.toString_now());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        d.cr("contextDestroyed", TGS_Time.toString_now());
    }
}
