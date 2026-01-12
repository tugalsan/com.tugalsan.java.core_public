package com.tugalsan.java.core.storage.cookie.server;

import module com.tugalsan.java.core.cast;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.stream;
import module javax.servlet.api;
import java.nio.charset.*;
import java.util.*;

public class TS_StorageCookieUtils {

    public static List<String> names(HttpServletRequest req) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return TGS_ListUtils.of();
        }
        return TGS_StreamUtils.toLst(Arrays.stream(cookies).map(c -> c.getName()));
    }

    public static Integer valInt(HttpServletRequest req, String name) {
        return TGS_CastUtils.toInteger(valStr(req, name)).orElse(null);
    }

    public static String valStr(HttpServletRequest req, String name) {
        var cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        var r = Arrays.stream(cookies).filter(c -> Objects.equals(c.getName(), name)).findAny().orElse(null);
        return r == null ? null : java.net.URLDecoder.decode(r.getValue(), StandardCharsets.UTF_8);
    }
}
