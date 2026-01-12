package com.tugalsan.java.core.storage.cookie.client;

import com.google.gwt.user.client.*;
import java.util.*;

import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.thread.client.*;
import com.tugalsan.java.core.time.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_StorageCookieUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_StorageCookieUtils.class);

    public static boolean supported() {
        return Cookies.isCookieEnabled();
    }

    public static int size() {
        return Cookies.getCookieNames().size();
    }

    public static List<String> names() {
        List<String> names = TGS_ListUtils.of();
        names.addAll(Cookies.getCookieNames());
        return names;
    }

    public static void clear() {
        Cookies.getCookieNames().forEach(param -> del(param));
    }

    public static void clear(CharSequence startsWith) {
        var str = startsWith.toString();
        names().forEach(param -> {
            if (param.startsWith(str)) {
                del(param);
            }
        });
    }

    public static void del(CharSequence param) {
        Cookies.removeCookie(param.toString());
    }

    public static String get(CharSequence param) {
        var str = param.toString();
        return Cookies.getCookie(str);
    }

    @Deprecated //WARNING!: TIME ZONE OFFSET SHOULD BE TAKEN INTO ACCOUNT ON CLIENTSIDE!
    public static void set_durationSeconds(boolean shared, CharSequence param, CharSequence value, long secs) {
        var expTime = new Date(System.currentTimeMillis() + (TGC_TimeUtils.getOffsetHours() * 60 * 60 + secs) * 1000L);
        if (shared) {
            Cookies.setCookie(param.toString(), value.toString(), expTime, null, "/", false);
        } else {
            Cookies.setCookie(param.toString(), value.toString(), expTime);
        }
    }

    public static void set_durationDays(boolean shared, CharSequence param, CharSequence value, int days) {
        set_durationSeconds(shared, param, value, TGS_TimeUtils.SECS_TIMEOUT_DAY() * days);
    }

    public static void set_durationHoursWork(boolean shared, CharSequence param, CharSequence value) {
        set_durationHours(shared, param, value, TGS_TimeUtils.SECS_TIMEOUT_HOURS_WORK());
    }

    public static void set_durationHours(boolean shared, CharSequence param, CharSequence value, int hours) {
        set_durationSeconds(shared, param, value, TGS_TimeUtils.SECS_TIMEOUT_HOUR() * hours);
    }

    public static void set_durationMinutes(boolean shared, CharSequence param, CharSequence value, int minutes) {
        set_durationSeconds(shared, param, value, TGS_TimeUtils.SECS_TIMEOUT_MINUTE() * minutes);
    }

    public static void afterSet(CharSequence param, TGS_FuncMTU exe) {
        var duration = 1;
        TGC_ThreadUtils.run_afterSeconds_afterGUIUpdate(t -> {
            var val = get(param);
            if (val == null) {
                t.run_afterSeconds(duration);
                return;
            }
            d.ci("afterSet", param, val);
            exe.run();
        }, duration);
    }

    public static native String native_document_cookie() /*-{
        return "" + document.cookie();
    }-*/;

    public static native String native_document_cookie_readable() /*-{
        return "" + decodeURIComponent(document.cookie.split(";"));
    }-*/;
}
