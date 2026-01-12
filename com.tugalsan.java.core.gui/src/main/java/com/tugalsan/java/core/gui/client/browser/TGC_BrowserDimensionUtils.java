package com.tugalsan.java.core.gui.client.browser;

import com.tugalsan.java.core.shape.client.TGS_ShapeDimension;
import com.google.gwt.user.client.Window;

public class TGC_BrowserDimensionUtils {
    
    private TGC_BrowserDimensionUtils(){
        
    }

    public static TGS_ShapeDimension<Integer> getDimension() {
        return new TGS_ShapeDimension(width(), height());
    }

    public static int height() {
        return Window.getClientHeight();
    }

    public static int width() {
        return Window.getClientWidth();
    }

    public static native String widthDocument() /*-{
        return "" + document.width;
    }-*/;

    public static native String heightDocument() /*-{
        return "" + document.height;
    }-*/;

    public static native int widthScreen() /*-{
        return screen.width;
    }-*/;

    public static native int heightScreen() /*-{
        return screen.height;
    }-*/;

    public static native int widthInner() /*-{
        return innerWidth;
    }-*/;

    public static native int heightInner() /*-{
        return innerHeight;
    }-*/;

    public static native int widthAvailable() /*-{
        return screen.availWidth;
    }-*/;

    public static native int heightAvailable() /*-{
        return screen.availHeight;
    }-*/;
}
