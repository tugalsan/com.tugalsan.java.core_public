package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserFavIconUtils {
    
    private TGC_BrowserFavIconUtils(){
        
    }

    public static native void setPng(CharSequence href) /*-{
        $wnd.setFavicon(href);
    }-*/;
    
    public static native void setTxt(CharSequence text) /*-{
        $wnd.setFavTxt(text);
    }-*/;
}
