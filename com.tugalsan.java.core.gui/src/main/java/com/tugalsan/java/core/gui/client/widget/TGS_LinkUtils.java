package com.tugalsan.java.core.gui.client.widget;

import com.tugalsan.java.core.string.client.*;

public class TGS_LinkUtils {

    private TGS_LinkUtils() {

    }

    public static String html(CharSequence text, CharSequence link) {
        return TGS_StringUtils.cmn().concat("<a class=\"AppModuleTitle\" href=\"", link, "\">", text, "</a>");
    }
}
