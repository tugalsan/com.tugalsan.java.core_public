package com.tugalsan.java.core.gui.client.browser;
//import elemental2.dom.MessageEvent;

public class TGC_BrowserTabUtils {

    private TGC_BrowserTabUtils() {

    }

    public static native boolean tab_visible() /*-{
      return !$doc.hidden;
    }-*/;

    /* solution: https://stackoverflow.com/questions/79270394/how-to-implement-browser-tab-visibility-in-gwt/79277077?noredirect=1#comment139800438_79277077
DomGlobal.document.addEventListener("visibilitychange", event -> {
  var tabIsVisible = !DomGlobal.document.hidden;
  DomGlobal.console.log("tabIsVisible: " + tabIsVisible);   
});    
     */
//    public static native void addEventListener_visibilitychange() /*-{
//        $doc.addEventListener("visibilitychange", () => {
//            var tabIsVisible = !document.hidden;
//            console.log("tabIsVisible:" + tabIsVisible);
//        });   
//    }-*/;
//    /*
//    Compiling module com.tugalsan.app.table.App
//   Tracing compile failure path for type 'com.tugalsan.java.core.gui.client.browser.TGC_BrowserTabUtils'
//      [ERROR] Errors in 'jar:file:/C:/Users/me/.m2/repository/com/tugalsan/com.tugalsan.java.core.gui/1.0-SNAPSHOT/com.tugalsan.java.core.gui-1.0-SNAPSHOT.jar!/com/tugalsan/api/gui/client/browser/TGC_BrowserTabUtils.java'
//         [ERROR] Line 8: syntax error
//>         $doc.addEventListener("visibilitychange", () => {
//    */
//@Deprecated //TODO BroadCast NOT WORKING, no need add another dep for it
//    final private static TGC_Log d = TGC_Log.of(TGC_BrowserTabUtils.class);
//    public static BroadcastChannel addBroadCastChannel(CharSequence channelName, TGS_FuncMTU_In1<MessageEvent<Object>> exe) {
//        var bc = new BroadcastChannel(channelName.toString());
//        d.cr("onModuleLoad", "bc.name", bc.name);
//        bc.onmessage = (MessageEvent<Object> e) -> {
//            var msg = String.valueOf(e.data);
//            d.cr("addBroadCastChannel.onmessage.onInvoke", "msg", msg);
//            return msg;
//        };
//        return bc;
//    }
//
//    public static Object broadCast(BroadcastChannel bc, Object msg) {
//        return bc.postMessage(msg);
//    }
}
