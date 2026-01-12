package com.tugalsan.java.core.url.server;

import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.stream;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.url;
import module javax.servlet.api;
import java.util.*;

public class TS_UrlServletRequestUtils {

    final private static TS_Log d = TS_Log.of(TS_UrlServletRequestUtils.class);

    public static String getParameterValueFrom64(HttpServletRequest rq, CharSequence paramNameAsIs) {
        var val64Safe = getParameterValue(rq, paramNameAsIs, false);
        d.ci("getParameterValueFrom64", paramNameAsIs, "val64Safe", val64Safe);
        var valReadable = TGS_UrlQueryUtils.param64UrlSafe_2_readable(val64Safe);
        d.ci("getParameterValueFrom64", paramNameAsIs, "valReadable", valReadable);
        return valReadable;
    }

    @Deprecated //NOT CHARACTER SAFE, USE getParameterValueFrom64 instead!
    public static String getParameterValue(HttpServletRequest rq, CharSequence paramNameAsIs, boolean dechiperVal2Readable) {
        var paramNameStr = paramNameAsIs.toString();
        var paramVal = rq.getParameter(paramNameStr);
        if (!dechiperVal2Readable) {
            return TGS_StringUtils.cmn().toNullIfEmpty(paramVal);
        }
        var paramValReadable = TS_UrlQueryUtils.toParamValueReadable(paramVal);
//        d.ce("getParameterValue", "paramNameStr/paramVal/paramValReadable", paramNameStr, paramVal, paramValReadable);
        return TGS_StringUtils.cmn().toNullIfEmpty(paramValReadable);
    }

    public static List<String> getParameterNames(HttpServletRequest rq) {
        return TGS_StreamUtils.toLst(TGS_StreamUtils.of(rq.getParameterNames()));
    }

    public static String getURLStringUnsafe(HttpServletRequest rq) {
        var scheme = rq.getScheme();             // http
        var serverName = rq.getServerName();     // hostname.com
        var serverPort = rq.getServerPort();        // 80
        var contextPath = rq.getContextPath();   // /mywebapp
        var servletPath = rq.getServletPath();   // /servlet/MyServlet
        var pathInfo = rq.getPathInfo();         // /a/b;c=123
        var queryString = rq.getQueryString();          // d=789

        // Reconstruct original requesting URL
        var url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);
        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath).append(servletPath);
        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }

    /*
    public static String getFullURL(HttpServletRequest request) {
    StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
    String queryString = request.getQueryString();

    if (queryString == null) {
        return requestURL.toString();
    } else {
        return requestURL.append('?').append(queryString).toString();
    }
}
     */
}
