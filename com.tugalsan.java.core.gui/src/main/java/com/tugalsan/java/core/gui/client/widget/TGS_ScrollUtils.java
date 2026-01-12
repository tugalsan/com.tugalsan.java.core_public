package com.tugalsan.java.core.gui.client.widget;

import java.util.*;

public class TGS_ScrollUtils {

    private TGS_ScrollUtils() {

    }

    public static StringJoiner htmlScroll2Top() {
        return htmlScroll2Top(true, "Yukarı");
    }

    public static StringJoiner htmlScroll2Top(boolean addicon, CharSequence text) {
        var classStyleNameAndId = TGS_ScrollUtils.class.getSimpleName() + "_btn";
        var iconSpan = addicon ? "<span class=\"icon icon-arrow-up\">&nbsp;</span>" : "";
        var sb = new StringJoiner("\n");
        sb.add("<button id=\"" + classStyleNameAndId + "\" onclick=\"window.scrollTop()\" class=\"gwt-PushButton " + classStyleNameAndId + "\">" + iconSpan + text + "</button>");
        sb.add("<script>");
        sb.add(classStyleNameAndId + " = document.getElementById(\"" + classStyleNameAndId + "\");");
        sb.add("window.onscroll = function() {scrollVisibility()};");
        sb.add("function scrollVisibility() {");
        sb.add("if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {");
        sb.add(classStyleNameAndId + ".style.display = \"block\";");
        sb.add(" } else {");
        sb.add(classStyleNameAndId + ".style.display = \"none\";");
        sb.add("}}");
        sb.add("window.scrollTop = function () {");
        sb.add("document.body.scrollTop = 0; // For Safari");
        sb.add("document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera");
        sb.add("};");
        sb.add("</script>");
        return sb;
    }
}
