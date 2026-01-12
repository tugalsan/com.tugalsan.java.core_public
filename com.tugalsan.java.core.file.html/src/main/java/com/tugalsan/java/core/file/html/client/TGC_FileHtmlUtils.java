package com.tugalsan.java.core.file.html.client;

import com.google.gwt.safehtml.shared.*;

public class TGC_FileHtmlUtils {

    public static SafeHtmlBuilder getSafeHtmlBuilder() {
        return new SafeHtmlBuilder();
    }

    public static String toSafeHtml(CharSequence string) {
        return SafeHtmlUtils.htmlEscape(string.toString());
    }

    public static String escape(CharSequence html) {//converts chars to html &tags
        if (html == null) {
            return null;
        }
        return SafeHtmlUtils.htmlEscape(html.toString());
    }
}
