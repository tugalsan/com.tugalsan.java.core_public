package com.tugalsan.java.core.tomcat.embedded.gwt.server.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.tugalsan.java.core.tomcat.embedded.gwt.server.*;

public class TS_ServletAliveByMapping extends TS_ServletAbstract {

    @Override
    public String name() {
        return TS_ServletAliveByMapping.class.getSimpleName();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(TS_ServletAliveByMapping.class.getName());
        resp.flushBuffer();
    }
}
