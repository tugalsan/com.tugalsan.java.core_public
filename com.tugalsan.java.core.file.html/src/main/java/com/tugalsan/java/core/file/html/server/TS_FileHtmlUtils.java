package com.tugalsan.java.core.file.html.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.file.html;
import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.file.txt;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.os;
import module com.tugalsan.java.core.stream;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.thread;
import module org.jsoup;
import module com.tugalsan.java.core.url;
import module org.apache.commons.text;
//import net.htmlparser.jericho.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;

public class TS_FileHtmlUtils {

    private TS_FileHtmlUtils() {

    }

    final private static TS_Log d = TS_Log.of(TS_FileHtmlUtils.class);

//TODO 
//    public static boolean String embedPlainFiles(CharSequence htmlContent){
//        var htmlContentInit = htmlContent.toString();
//        
//        return 
//    }
    public static boolean browse(TGS_Url url) {
        var edge = Path.of("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        if (!TS_FileUtils.isExistFile(edge)) {
            d.ce("browse", "File not exist", edge);
            return false;
        }
        if (!TS_OsPlatformUtils.isWindows()) {
            d.ce("browse", "os not supported");
            return false;
        }
        var cmd = edge.toAbsolutePath().toString() + " " + url.toString();
        d.ci("browse", "edge", cmd);
        return TS_OsProcess.of(cmd).exitValueOk();
    }

    public static Path write2File(TGS_FileHtml src, Path filePath) {
        var content = src.toString();
        return TS_FileTxtUtils.toFile(content, filePath, false);
    }

    public static String escape(CharSequence html) {//converts chars to html &tags
        return StringEscapeUtils.escapeHtml4(html.toString());
    }

    public static List<TGS_Url> parseLinks_usingRegex(List<TGS_Url> urlSrcs, Duration timeout, boolean removeAnchor, boolean mapBase, TGS_FuncMTU_OutBool_In1<TGS_Url> filter) {
        TS_ThreadSyncLst<TGS_Url> lst = TS_ThreadSyncLst.ofSlowRead();
        urlSrcs.parallelStream().forEach(urlSrc -> {
            var urls = parseLinks_usingRegex(urlSrc, timeout, removeAnchor, mapBase, filter);
            d.cr("parseLinks_usingRegex", "urlSrcs", urlSrc, "urls.size()", urls.size());
            lst.add(urls);
        });
        d.cr("parseLinks_usingRegex", "urlSrcs", "lst.size()", lst.size());
        return lst.toList_modifiable();
    }

    public static List<TGS_Url> parseLinks_usingRegex(TGS_Url urlSrc, Duration timeout, boolean removeAnchor, boolean mapBase, TGS_FuncMTU_OutBool_In1<TGS_Url> filter) {
        List<TGS_Url> urlsProcessed = new ArrayList();
        var html = TS_UrlDownloadUtils.toText(urlSrc, timeout);
        if (html == null) {
            d.cr("parseLinks_usingRegex", urlSrc.toString(), "html == null", urlSrc.toString());
        } else {
            var urlsAll = TS_FileHtmlUtils.parseLinks_usingRegex(html, true);
            d.cr("parseLinks_usingRegex", urlSrc.toString(), "urls.size", urlsAll.size());
            if (d.infoEnable) {
                urlsAll.forEach(u -> {
                    d.ci("parseLinks_usingRegex", urlSrc.toString(), u);
                });
            }
            var parser = TGS_UrlParser.of(urlSrc);
            parser.anchor.clear();
            parser.quary.clear();
            parser.path.clear();
            var strBase = parser.toString();
            urlsProcessed.addAll(
                    urlsAll.stream()
                            .filter(u -> filter.validate(u))
                            .map(u -> {
                                if (!mapBase) {
                                    return u;
                                }
                                if (!TGS_CharSetCast.current().startsWithIgnoreCase(u.toString(), urlSrc.toString())) {
                                    var strChild = u.toString();
                                    var childStartsWithSlash = strChild.startsWith("/");
                                    if (childStartsWithSlash) {
                                        strChild = strChild.substring(1);
                                    }
                                    return TGS_Url.of(strBase + strChild);
                                }
                                return u;
                            })
                            .toList()
            );
        }
        return urlsProcessed;
    }

    public static List<TGS_Url> parseLinks_usingRegex(CharSequence html, boolean removeAnchor) {
        List<TGS_Url> urls = new ArrayList();
        var regex = "<a href\\s?=\\s?\"([^\"]+)\">";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(html);
        var index = 0;
        while (matcher.find(index)) {
            var tag = matcher.group(); // includes "<a href" and ">"
            var link = matcher.group(1); // just the link
            urls.add(TGS_Url.of(removeAnchor ? link : tag));
            index = matcher.end();
        }
        return urls;
    }

    @Deprecated //NOT WORKING, DONNO WHY, USE parseLinks_usingRegex
    public static List<TGS_Url> parseLinks(CharSequence html, boolean removeAnchor) {
        return parseLinks_filter(TGS_Url.of(""), TGS_StreamUtils.toLst(
                Jsoup.parse(html.toString()).select("a").stream().map(a -> TGS_Url.of(a.attr("href")))
        ), removeAnchor, false);
    }

    @Deprecated //NOT WORKING, DONNO WHY, USE parseLinks_usingRegex
    public static List<TGS_Url> parseLinks(TGS_Url url, boolean removeAnchor, boolean fetchOnlyChild) {
        var base = TGS_UrlParser.of(url);
        base.path.fileOrServletName = "";
        return parseLinks_filter(base.toUrl(), TGS_FuncMTCUtils.call(() -> TGS_StreamUtils.toLst(
                Jsoup.connect(url.toString()).get().select("a").stream().map(a -> TGS_Url.of(a.attr("href")))
        )), removeAnchor, fetchOnlyChild);
    }

    private static List<TGS_Url> parseLinks_filter(TGS_Url base, List<TGS_Url> links, boolean removeAnchor, boolean fetchOnlyChild) {
        var processed = TGS_StreamUtils.toLst(
                links.stream()
                        .map(u -> removeAnchor ? TGS_UrlUtils.trimAnchor(u) : u)
                        .map(u -> TGS_UrlUtils.toFull(base, u))
                        .filter(u -> fetchOnlyChild ? u.toString().startsWith(base.toString()) : true)
                        .filter(u -> fetchOnlyChild ? u.toString().contains("..") : false)
                        .filter(u -> !u.equals(base))
                        .filter(u -> !u.toString().equals(base.toString() + "/"))
                        .filter(u -> !(u.toString() + "/").equals(base.toString()))
        );
        if (removeAnchor) {
            processed = TGS_StreamUtils.toLst(
                    TGS_ListDistinctizeUtils.toUniqueString(
                            TGS_StreamUtils.toLst(processed.stream().map(u -> u.toString()))
                    ).stream().map(s -> TGS_Url.of(s))
            );
        }
        return processed;
    }

    public static String toText(TGS_Url url) {
        return TGS_FuncMTCUtils.call(() -> Jsoup.connect(url.toString()).get().wholeText());
    }

    public static String toText(CharSequence html) {//converts html &tags to chars
        return Jsoup.parse(html.toString()).wholeText();
    }

    public static int getIdxBodyStartBefore(CharSequence html) {
        var titleTagStart = "<body";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxBodyStartAfter(CharSequence html) {
        return html.toString().indexOf(">", TS_FileHtmlUtils.getIdxBodyStartBefore(html) + 1) + 1;
    }

    public static int getIdxBodyEndBefore(CharSequence html) {
        var titleTagStart = "</body>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxBodyEndAfter(CharSequence html) {
        var titleTagStart = "</body>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxTitleStartBefore(CharSequence html) {
        var titleTagStart = "<title>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxTitleEndBefore(CharSequence html) {
        var titleTagStart = "</title>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxTitleStartAfter(CharSequence html) {
        var titleTagStart = "<title>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxTitleEndAfter(CharSequence html) {
        var titleTagStart = "</title>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxHeadStartBefore(CharSequence html) {
        var titleTagStart = "<head>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxHeadEndBefore(CharSequence html) {
        var titleTagStart = "</head>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxHeadStartAfter(CharSequence html) {
        var titleTagStart = "<head>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxHeadEndAfter(CharSequence html) {
        var titleTagStart = "</head>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static String updateTitleContent(CharSequence html, CharSequence content) {
        return TGS_StringUtils.cmn().concat(
                html.subSequence(0, getIdxTitleStartAfter(html)),
                content,
                html.subSequence(getIdxTitleEndBefore(html), html.length())
        );
    }

    public static String updateBodyTag(CharSequence html, CharSequence newBodyTag) {
        return TGS_StringUtils.cmn().concat(
                html.subSequence(0, getIdxBodyStartBefore(html)),
                newBodyTag,
                html.subSequence(getIdxBodyStartAfter(html), html.length())
        );
    }

    public static TGS_Tuple2<String, String> ribHeadContentAfterTitle_returnHtmlAndHeadContent(CharSequence html) {
        var idxTitleEndAfter = TS_FileHtmlUtils.getIdxTitleEndAfter(html);
        var idxHeadEndBefore = TS_FileHtmlUtils.getIdxHeadEndBefore(html);
        var ribContent = html.subSequence(idxTitleEndAfter, idxHeadEndBefore).toString();
        var newHtm = TGS_StringUtils.cmn().concat(html.subSequence(0, idxTitleEndAfter), html.subSequence(idxHeadEndBefore, html.length()));
        return new TGS_Tuple2(newHtm, ribContent);
    }

    public static String appendToHead(CharSequence html, CharSequence content) {
        var headEnd = getIdxHeadEndBefore(html);
        return TGS_StringUtils.cmn().concat(html.subSequence(0, headEnd), content, html.subSequence(headEnd, html.length()));
    }

    public static String appendToBodyStartAfter(CharSequence html, CharSequence content) {
        var bodyStartAfter = getIdxBodyStartAfter(html);
        return TGS_StringUtils.cmn().concat(html.subSequence(0, bodyStartAfter), content, html.subSequence(bodyStartAfter, html.length()));
    }

    public static String appendToBodyEndBefore(CharSequence html, CharSequence content) {
        var bodyEndBefore = getIdxBodyEndBefore(html);
        return TGS_StringUtils.cmn().concat(html.subSequence(0, bodyEndBefore), content, html.subSequence(bodyEndBefore, html.length()));
    }

    public static String addLoader(CharSequence htm) {
        var r = TS_FileHtmlUtils.ribHeadContentAfterTitle_returnHtmlAndHeadContent(htm);
        htm = r.value0;
        var heavyContent = r.value1;
        htm = TS_FileHtmlUtils.appendToHead(htm,
                """
        <style>
        /* Center the loader */
        #loader {
          position: absolute;
          left: 50%;
          top: 50%;
          z-index: 1;
          width: 120px;
          height: 120px;
          margin: -76px 0 0 -76px;
          border: 16px solid #f3f3f3;
          border-radius: 50%;
          border-top: 16px solid #3498db;
          -webkit-animation: spin 2s linear infinite;
          animation: spin 2s linear infinite;
        }
        
        @-webkit-keyframes spin {
          0% { -webkit-transform: rotate(0deg); }
          100% { -webkit-transform: rotate(360deg); }
        }
        
        @keyframes spin {
          0% { transform: rotate(0deg); }
          100% { transform: rotate(360deg); }
        }
        
        /* Add animation to "page content" */
        .animate-bottom {
          position: relative;
          -webkit-animation-name: animatebottom;
          -webkit-animation-duration: 1s;
          animation-name: animatebottom;
          animation-duration: 1s
        }
        
        @-webkit-keyframes animatebottom {
          from { bottom:-100px; opacity:0 } 
          to { bottom:0px; opacity:1 }
        }
        
        @keyframes animatebottom { 
          from{ bottom:-100px; opacity:0 } 
          to{ bottom:0; opacity:1 }
        }
        
        #myDiv {
          display: none;
          text-align: center;
        }
        </style>
        <script>
        var myVar;
        
        function filterTable() {
          myVar = setTimeout(showPage, 3000);
        }
        
        function showPage() {
          document.getElementById("loader").style.display = "none";
          document.getElementById("myDiv").style.display = "block";
        }
        </script>"""
        );
        htm = TS_FileHtmlUtils.updateBodyTag(htm, "<body  onload=\"filterTable()\" >");
        htm = TS_FileHtmlUtils.appendToBodyStartAfter(htm, "<div id=\"loader\"></div><div style=\"display:none;\" id=\"myDiv\" class=\"animate-bottom\">");
        htm = TS_FileHtmlUtils.appendToBodyEndBefore(htm, "</div>");
        return TS_FileHtmlUtils.appendToBodyEndBefore(htm, heavyContent);
    }

    public static String IFRAME_VIDEO_VADI_LOCA() {
        return "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/U-BzhUxnEu8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
    }

    public static String HEADER_CONTENT_VADI_LOCA() {
        return """
                                                   <div class='container2'>
                                                        		<div class="left">
                                                        			<img src='index.png' class='iconDetails'>
                                                        		</div>
                                                        	<div class="right" >
                                                        		<h4>VADİ LOCA SİTESİ</h4>
                                                        		<div style="padding-left: 6px;font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;font-size:1em;float:left;color: green;">YAŞAM VADİSİNİN YANI BAŞINDA,</div>
                                                        		<div style="padding-left: 6px;font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;font-size:1em;float:right;color: green;">AKTİF HAYATIN TAM ORTASINDA...</div>
                                                        	</div>
                                                        </div>
                                                        	<style>
                                                        	 .iconDetails {
                                                         margin-left:2%;
                                                        float:left;
                                                        width:65px;
                                                        }
                                                        
                                                        .container2 {
                                                        	height:auto;
                                                        	padding:1%;
                                                            float:left;
                                                        }
                                                        h4{
                                                        margin:0;
                                                        font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;
                                                        font-size:1.5em;
                                                        padding-top: 10px;
                                                        }
                                                        .left {float:left;width:70px;}
                                                        .right {float:left;margin:0 0 0 5px;}
                                                        	</style>""";
    }

    public static String appendResponsiveVideo(CharSequence htm, CharSequence iframe_video) {
        return appendToBodyStartAfter(htm, TGS_StringUtils.cmn().concat(
                "<div class=\"video-container\">",
                iframe_video,
                """
                </div>
                		<style>
                		.video-container {
                    overflow: hidden;
                    position: relative;
                    width:100%;
                }
                
                .video-container::after {
                    padding-top: 56.25%;
                    display: block;
                    content: '';
                }
                
                .video-container iframe {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    max-width: 960px;
                    padding-left: 8px;
                    height: 100%;
                }
                		</style>"""
        ));
    }
}
