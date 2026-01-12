package com.tugalsan.java.core.servlet.upload.server;

import module com.tugalsan.java.core.function;
import module javax.servlet.api;
import java.time.*;

abstract public class TS_SUploadExecutor implements TGS_FuncMTU_In3<HttpServlet, HttpServletRequest, HttpServletResponse> {

    public Duration timeout() {
        return Duration.ofMinutes(1);
    }
}
