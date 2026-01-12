package com.tugalsan.java.core.js.client;

import com.google.gwt.dom.client.Element;/*DONT TOUCH ME*/
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public class TGC_JSUtils {

    public static native String removeEmoji(String text) /*-{
        return text.replace(/([\u2700-\u27BF]|[\uE000-\uF8FF]|\uD83C[\uDC00-\uDFFF]|\uD83D[\uDC00-\uDFFF]|[\u2011-\u26FF]|\uD83E[\uDD10-\uDDFF])/g, '');
}-*/;

    public static native NodeList<Element> querySelectorAll(String tag_dot_className) /*-{
        return $doc.querySelectorAll(tag_dot_className)
 }-*/;

    public static void injectJSFromURL(CharSequence url) {
        ScriptInjector.fromUrl(url.toString()).setRemoveTag(false).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }

    public static void injectJSFromString(CharSequence text) {
        ScriptInjector.fromString(text.toString()).setRemoveTag(false).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }

    public static native void evalJSCode(Element parent, CharSequence jsCode);

    public static void evalJSCode(Widget parent, CharSequence jsCode) {
        evalJSCode(parent.getElement(), jsCode);
    }

    public static native String includeJS(CharSequence filename) /*-{
        return $wnd.includeJS(filename);
}-*/;

    public static native boolean isBrowserUpToDate() /*-{
        return $wnd.isBrowserUpToDate();
}-*/;

    public static native String includeJM(CharSequence filename) /*-{
        return $wnd.includeJM(filename);
}-*/;

    public static native String onMyButtonClick() /*-{//EXAMPLE
        return $wnd.myJSfunction();
}-*/;

    public static native String addJSElement(CharSequence parentId, CharSequence childId) /*-{
        console.log('parentId:' + parentId);
        console.log('childId:' + childId);
        var p = document.getElementById(parentId);
        console.log('p:' + p);
        var c = document.getElementById(childId);
        console.log('c:' + c);
        document.getElementById(parentId).appendChild(document.getElementById(childId));
}-*/;
}
