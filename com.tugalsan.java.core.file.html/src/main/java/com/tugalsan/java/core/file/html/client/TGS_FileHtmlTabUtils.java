package com.tugalsan.java.core.file.html.client;

import com.tugalsan.java.core.tuple.client.*;
import java.util.*;

public class TGS_FileHtmlTabUtils {

    final public static String css() {
        return new StringJoiner("\n")
                .add("/* Style the tab */")
                .add(".tab {")
                .add("  overflow: hidden;")
                .add("  border: 1px solid #ccc;")
                .add("  background-color: #f1f1f1;")
                .add("}")
                .add("")
                .add("/* Style the buttons that are used to open the tab content */")
                .add(".tab button {")
                .add("background-color: inherit;")
                .add("  float: left;")
                .add("  border: none;")
                .add("  outline: none;")
                .add("  cursor: pointer;")
                .add("  padding: 14px 16px;")
                .add("  transition: 0.3s;")
                .add("}")
                .add("")
                .add("/* Change background color of buttons on hover */")
                .add(".tab button:hover {")
                .add("  background-color: #ddd;")
                .add("}")
                .add("")
                .add("/* Create an active/current tablink class */")
                .add(".tab button.active {")
                .add("  background-color: #ccc;")
                .add("}")
                .add("")
                .add("/* Style the tab content */")
                .add(".tabcontent {")
                .add("  display: none;")
                .add("  padding: 6px 12px;")
                .add("  border: 1px solid #ccc;")
                .add("  border-top: none;")
                .add("}")
                .toString();
    }

    final public static String js() {
        return new StringJoiner("\n")
                .add("function openTab(evt, cityName) {")
                .add("// Declare all variables")
                .add("var i, tabcontent, tablinks;")
                .add("")
                .add("// Get all elements with class=\"tabcontent\" and hide them")
                .add("tabcontent = document.getElementsByClassName(\"tabcontent\");")
                .add("for (i = 0; i < tabcontent.length; i++) {")
                .add("  tabcontent[i].style.display = \"none\";")
                .add("}")
                .add("")
                .add("// Get all elements with class=\"tablinks\" and remove the class \"active\"")
                .add("tablinks = document.getElementsByClassName(\"tablinks\");")
                .add("for (i = 0; i < tablinks.length; i++) {")
                .add("  tablinks[i].className = tablinks[i].className.replace(\" active\", \"\");")
                .add("}")
                .add("")
                .add("// Show the current tab, and add an \"active\" class to the button that opened the tab")
                .add("document.getElementById(cityName).style.display = \"block\";")
                .add("  evt.currentTarget.className += \" active\";")
                .add("}")
                .toString();
    }

    final public static String buttons(TGS_Tuple2<String, String>... btnNamesAndIds) {
        var sb = new StringBuilder();
        sb.append("<!-- Tab links -->\n");
        sb.append("<div class=\"tab\">\n");
        Arrays.stream(btnNamesAndIds).forEachOrdered(btn -> {
            sb.append("  <button class=\"tablinks\" onclick=\"openTab(event, '").append(btn.value1).append("')\">");
            sb.append(btn.value0);
            sb.append("</button>\n");
        });
        sb.append("</div>\n");
        return sb.toString();
    }

    final public static String contentStart(CharSequence id) {
        return "<div id=\"" + id + "\" class=\"tabcontent\">";
    }

    final public static String contentEnd() {
        return "</div>";
    }
}
