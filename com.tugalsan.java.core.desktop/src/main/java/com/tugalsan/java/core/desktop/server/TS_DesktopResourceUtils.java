package com.tugalsan.java.core.desktop.server;

import module java.desktop;
import java.net.*;

public class TS_DesktopResourceUtils {

    private TS_DesktopResourceUtils() {

    }

    public static URL url(String loc) {
        return TS_DesktopResourceUtils.class.getResource(loc);
    }

    public static ImageIcon imageIcon(String loc) {
        var url = url(loc);
        return url == null ? null : new ImageIcon(url);
    }
}
