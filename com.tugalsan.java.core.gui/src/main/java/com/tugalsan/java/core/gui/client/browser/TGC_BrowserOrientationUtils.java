package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserOrientationUtils {

    private TGC_BrowserOrientationUtils() {

    }

    public static native boolean isOrientationUndefined() /*-{
        return typeof window.orientation == 'undefined';
    }-*/;
}
