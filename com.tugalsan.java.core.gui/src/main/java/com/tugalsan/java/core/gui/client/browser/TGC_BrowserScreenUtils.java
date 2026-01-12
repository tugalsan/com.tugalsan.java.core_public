package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserScreenUtils {

    private TGC_BrowserScreenUtils() {

    }

    public static native int depthColor() /*-{
        return screen.colorDepth;
    }-*/;

    public static native int depthPixel() /*-{
        return screen.pixelDepth;
    }-*/;

    public static native double getDevicePixelRatio() /*-{
    return $wnd.devicePixelRatio || 1.0;
  }-*/;
}
