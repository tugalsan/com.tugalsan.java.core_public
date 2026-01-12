package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserPositionUtils {
    
    private TGC_BrowserPositionUtils(){
        
    }

    public static native void console_print_position() /*-{
      navigator.geolocation.getCurrentPosition(function (position) {
	console.log(position.coords.latitude);//41.07719861844539
	console.log(position.coords.longitude);//28.80039090990448
      });
    }-*/;
}
