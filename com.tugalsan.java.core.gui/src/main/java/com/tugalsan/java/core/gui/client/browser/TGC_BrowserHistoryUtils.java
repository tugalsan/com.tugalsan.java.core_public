package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserHistoryUtils {
    
    private TGC_BrowserHistoryUtils(){
        
    }

    public static native int native_history_length() /*-{
        return history.length;
    }-*/;
}
