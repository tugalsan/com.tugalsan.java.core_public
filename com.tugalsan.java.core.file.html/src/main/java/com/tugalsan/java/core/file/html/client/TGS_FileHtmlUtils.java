package com.tugalsan.java.core.file.html.client;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTUEffectivelyFinal;
import com.tugalsan.java.core.file.common.client.TGS_FileCommonFavIcon;
import com.tugalsan.java.core.string.client.*;
import com.tugalsan.java.core.url.client.TGS_Url;
import com.tugalsan.java.core.url.client.parser.TGS_UrlParser;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;

public class TGS_FileHtmlUtils {

    @Deprecated
    public static String wrapInIFrame(List<String> htmlLines, CharSequence iframeId) {
        var sb = new StringBuilder();
        sb.append("var s =\"\";\n");
        htmlLines.forEach(htmlLine -> {
            htmlLine = htmlLine.trim().replace("\"", "'");
            if (htmlLine.isEmpty()) {
                return;
            }
            sb.append("s+=\"");
            sb.append(htmlLine);
            sb.append("\";\n");
        });

        return TGS_StringUtils.cmn().concat(
                "<script>\n",
                sb,
                "var iframe = document.getElementById(\"", iframeId, "\");\n",
                "iframe.src = ", "\"data:text/html;charset=utf-8,\"", "+s", "\n",
                "</script>\n"
        );
    }

    public static String pageStart(Integer padding) {//papaer-css
        //<!-- "padding-**mm" is optional: you can set 10, 15, 20 or 25 -->
        if (padding == null || padding < 10) {
            //            return "<section class='sheet'>";
            return "<section class='sheet padding-10mm'>";
        }
        if (padding < 15) {
            return "<section class='sheet padding-10mm'>";
        }
        if (padding < 20) {
            return "<section class='sheet padding-15mm'>";
        }
        if (padding < 25) {
            return "<section class='sheet padding-20mm'>";
        }
        return "<section class='sheet padding-25mm'>";
    }

    public static String pageEnd() {
        return "</section>";
    }

    public static String beginLines(CharSequence browserTitle, boolean addBorder, int leftMargin, int topMargin, TGS_FileCommonFavIcon optional_hrefPngIcon, boolean addDivCenter, TGS_Url bootLoaderJs) {
        return beginLines(browserTitle, addBorder, leftMargin, topMargin, optional_hrefPngIcon, addDivCenter, bootLoaderJs, null, null);
    }

    public static String beginLines(CharSequence browserTitle, boolean addBorder, Integer leftMargin, Integer topMargin, TGS_FileCommonFavIcon optional_hrefPngIcon, boolean addDivCenter, TGS_Url bootLoaderJs, Integer pageSizeAX, Boolean landscape) {
        var sj = new StringJoiner("\n");
        //DOCTYPE
        sj.add("<!doctype html>");
        //HTML START
        if (bootLoaderJs != null) {
            sj.add("<html lang=\"tr\" dir=\"ltr\" class=\"Light-Cream\">");
        } else {
            sj.add("<html lang=\"tr\" dir=\"ltr\">");
        }
        //HTML->HEAD START
        sj.add("<head>");
        //HTML->HEAD->FAV ICON
        if (optional_hrefPngIcon != null) {
            sj.add(optional_hrefPngIcon.toString());
        }
        //HTML->HEAD->CHARSET
        sj.add(TGS_StringUtils.cmn().concat("<meta charset='", StandardCharsets.UTF_8.name(), "'>"));
        //HTML->HEAD->TITLE
        sj.add(TGS_StringUtils.cmn().concat("<title>", browserTitle, "</title>"));
        //HTML->HEAD->BORDER STYLE
        if (addBorder) {
            sj.add("<style>");
            sj.add("table, th, td {border: 1px solid black !important}");
            sj.add("</style>");
        }
        if (leftMargin != null || topMargin != null) {
            sj.add("<style>");
            sj.add("body {");
            if (leftMargin != null) {
                sj.add("   margin-left: " + leftMargin + "px !important;");
                sj.add("   margin-right: " + leftMargin + "px !important;");
            }
            if (topMargin != null) {
                sj.add("   margin-top: " + topMargin + "px !important;");
                sj.add("   margin-bottom: " + topMargin + "px !important;");
            }
            sj.add("}");
            sj.add("</style>");
        }
        //HTML->HEAD->CSS
        if (bootLoaderJs != null) {
            sj.add("<script>");
            sj.add("window.boot_loader_main = function () {");
            sj.add("console.log(\"index.jsp: welcome\");");
            sj.add("};");

            var parser = TGS_UrlParser.of(bootLoaderJs);
            sj.add("let protocol = 'https';");
            sj.add("let hostname = '" + parser.host.domain + "';");
            sj.add("let port = " + parser.host.port + ";");

            sj.add("var script = document.createElement('script');");
            System.out.println("bootloaderJs:" + bootLoaderJs);
            sj.add("script.src = '" + bootLoaderJs + "'");
            sj.add("script.type = 'text/javascript';");
            sj.add("document.head.appendChild(script);");
            sj.add("</script>");
        }
        if (pageSizeAX != null) {
            sj.add("<style>");
            addPaperCss(sj);
            sj.add("</style>");
        }
        if (bootLoaderJs == null && pageSizeAX == null) {
            sj.add("<style>");
            sj.add("html * {");
            sj.add("   font-size: 1em !important");
            sj.add("   font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif !important");
            sj.add("}");
            sj.add("</style>");
        }
        var bodyClass = TGS_FuncMTUEffectivelyFinal.ofStr().coronateAs(__ -> {
            var pageSize = pageSizeAX == null ? "" : ("A" + pageSizeAX);
            var pageOri = landscape == null ? "" : (landscape ? "landscape" : "portrait");
            if (!pageSize.isEmpty() && !pageOri.isEmpty()) {
                return pageSize + " " + pageOri;
            }
            if (!pageSize.isEmpty()) {
                return pageSize;
            }
            if (!pageOri.isEmpty()) {
                return pageOri;
            }
            return "";
        });
        if (bodyClass.isEmpty()) {
            //HTML->HEAD END
            sj.add("</head>");
            //HTML->BODY START
            sj.add("<body>");
        } else {
            sj.add("<style>@page { size: " + bodyClass + " }</style>");
            //HTML->HEAD END
            sj.add("</head>");
            //HTML->BODY START
            sj.add("<body class='" + bodyClass + "'>");
        }
        //HTML->BODY->DIV START
        if (addDivCenter) {
            sj.add("<div class=\"AppModule_configLayout\">");
            if (bootLoaderJs != null && pageSizeAX == null) {
                sj.add("<div class=\"theme_container\">");
                sj.add("<div class=\"theme_switch\">");
                sj.add("<label for=\"theme_toggle\">");
                sj.add("<input id=\"theme_toggle\" class=\"theme_toggle-switch\" type=\"checkbox\">");
                sj.add("<div class=\"theme_sun-moon\"><div class=\"theme_dots\"></div></div>");
                sj.add("<div class=\"theme_background\"><div class=\"theme_stars1\"></div><div class=\"theme_stars2\"></div></div>");
                sj.add("</label>");
                sj.add("</div>");
                sj.add("</div>");
                sj.add("<script>");
                sj.add("var theme_el = document.getElementsByTagName('html')[0];");
                sj.add("if (theme_el.classList.contains('Dark-Black')){\n");
                sj.add("	document.getElementById(\"theme_toggle\").checked=false;");
                sj.add("}\n");
                sj.add("if (theme_el.classList.contains('Light-Cream')){");
                sj.add("	document.getElementById(\"theme_toggle\").checked=true;");
                sj.add("}\n");
                sj.add("document.getElementById(\"theme_toggle\").addEventListener(\"click\", function(){");
                sj.add("	if (theme_el.classList.contains('Dark-Black')){");
                sj.add("		theme_el.classList.remove('Dark-Black');");
                sj.add("		theme_el.classList.add('Light-Cream');");
                sj.add("	} else {\n");
                sj.add("		theme_el.classList.add('Dark-Black');");
                sj.add("		theme_el.classList.remove('Light-Cream');");
                sj.add("	}");
                sj.add("});");
                sj.add("</script>");
            }
        }
        return sj.toString();
    }

    public static String endLines(boolean addDiv) {
        var sj = new StringJoiner("\n");
        if (addDiv) {
            sj.add("</div>");
        }
        sj.add("</body>");
        sj.add("</html>");
        return sj.toString();
    }

    private static void addPaperCss(StringJoiner sj) {
        sj.add("/** ref:https://github.com/cognitom/paper-css  **/");
        sj.add("@page { margin: 0 }");
        sj.add("body { margin: 0 }");
        sj.add(".sheet {");
        sj.add("  margin: 0;");
        sj.add("  overflow: hidden;");
        sj.add("  position: relative;");
        sj.add("  box-sizing: border-box;");
        sj.add("  page-break-after: always;");
        sj.add("}");
        sj.add("");
        sj.add("/** Paper sizes **/");
        sj.add("body.A3               .sheet { width: 297mm; min-height: 419mm }");
        sj.add("body.A3.landscape     .sheet { width: 420mm; min-height: 296mm }");
        sj.add("body.A4               .sheet { width: 210mm; min-height: 296mm }");
        sj.add("body.A4.landscape     .sheet { width: 297mm; min-height: 209mm }");
        sj.add("body.A5               .sheet { width: 148mm; min-height: 209mm }");
        sj.add("body.A5.landscape     .sheet { width: 210mm; min-height: 147mm }");
        sj.add("body.letter           .sheet { width: 216mm; min-height: 279mm }");
        sj.add("body.letter.landscape .sheet { width: 280mm; min-height: 215mm }");
        sj.add("body.legal            .sheet { width: 216mm; min-height: 356mm }");
        sj.add("body.legal.landscape  .sheet { width: 357mm; min-height: 215mm }");
        sj.add("");
        sj.add("/** Padding area **/");
        sj.add(".sheet.padding-10mm { padding: 10mm }");
        sj.add(".sheet.padding-15mm { padding: 15mm }");
        sj.add(".sheet.padding-20mm { padding: 20mm }");
        sj.add(".sheet.padding-25mm { padding: 25mm }");
        sj.add("");
        sj.add("/** For screen preview **/");
        sj.add("@media screen {");
        sj.add("  body { background: #e0e0e0 }");
        sj.add("  .sheet {");
        sj.add("    background: white;");
        sj.add("    box-shadow: 0 .5mm 2mm rgba(0,0,0,.3);");
        sj.add("    margin: 5mm auto;");
        sj.add("  }");
        sj.add("}");
        sj.add("");
        sj.add("/** Fix for Chrome issue #273306 **/");
        sj.add("@media print {");
        sj.add("           body.A3.landscape { width: 420mm }");
        sj.add("  body.A3, body.A4.landscape { width: 297mm }");
        sj.add("  body.A4, body.A5.landscape { width: 210mm }");
        sj.add("  body.A5                    { width: 148mm }");
        sj.add("  body.letter, body.legal    { width: 216mm }");
        sj.add("  body.letter.landscape      { width: 280mm }");
        sj.add("  body.legal.landscape       { width: 357mm }");
        sj.add("}");
        sj.add("table {");
        sj.add("  table-layout:fixed;");
        sj.add("}");
    }
}
