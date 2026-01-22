package com.tugalsan.java.core.file.html.client;

import com.google.gwt.safehtml.shared.*;

public class TGC_FileHtmlUtils {

    private TGC_FileHtmlUtils() {

    }

//does anyone uses it?
//    private static SafeHtmlBuilder getSafeHtmlBuilder() {
//        return new SafeHtmlBuilder();
//    }
    public static String toSafeHtml(CharSequence html) {
        if (html == null) {
            return null;
        }
        return SafeHtmlUtils.htmlEscape(html.toString());
    }

//use toSafeHtml
//    public static String escape(CharSequence html) {//converts chars to html &tags
//        if (html == null) {
//            return null;
//        }
//        return SafeHtmlUtils.htmlEscape(html.toString());
//    }    
}
