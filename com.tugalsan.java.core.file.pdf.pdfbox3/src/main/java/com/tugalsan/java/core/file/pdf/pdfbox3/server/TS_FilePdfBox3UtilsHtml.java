package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.url;
import module org.jsoup;
import module com.tugalsan.java.core.file.pdf.pdfbox3.pdf2dom;
import module com.tugalsan.java.core.file.pdf.pdfbox3.openhtmltopdf.pdfbox;
import com.tugalsan.java.core.file.pdf.pdfbox3.server.openhtmtopdf.AutoFont;
import org.apache.pdfbox.Loader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TS_FilePdfBox3UtilsHtml {

    final private static TS_Log d = TS_Log.of(true, TS_FilePdfBox3UtilsHtml.class);

    @Deprecated //UTF8 Fonts not working, READ: https://github.com/danfickle/openhtmltopdf/wiki/Fonts, How about com.tugalsan.java.core.html.selenium
    public static TGS_UnionExcuseVoid toPdf(String html, Path pathDstPDF, Path pathFont_canBeDir_orFile) {
        return TGS_FuncMTCUtils.call(() -> {
            String baseUri = null;
            //doc_jsoup
            org.jsoup.nodes.Document doc_jsoup = Jsoup.parse(html, StandardCharsets.UTF_8.toString());
            doc_jsoup.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.html);
            doc_jsoup.outputSettings().prettyPrint(true);
            //doc_w3c
            var doc_w3c = new W3CDom().fromJsoup(doc_jsoup);
            //font
            var isDir = TS_DirectoryUtils.isExistDirectory(pathFont_canBeDir_orFile);
            var pathFontDirectory = isDir ? pathFont_canBeDir_orFile : pathFont_canBeDir_orFile.getParent();
            var u_lstFont = AutoFont.findFontsInDirectory(pathFontDirectory);
            if (u_lstFont.isExcuse()) {
                TGS_FuncMTUUtils.thrw(u_lstFont.excuse());
            }
            TGS_FuncMTU_OutBool_In1<Path> filter_isDir = pathFontFile -> true;
            TGS_FuncMTU_OutBool_In1<Path> filter_isFile = pathFontFile -> pathFontFile.equals(pathFont_canBeDir_orFile);
            //skip below line, I donno what to do with that
            var cssEscapedFontFamily = AutoFont.toCSSEscapedFontFamily(u_lstFont.value());
            d.cr("toPdf", "cssEscapedFontFamily", cssEscapedFontFamily);
            //from doc_w3c to pdf
            try (var os = Files.newOutputStream(pathDstPDF)) {
                var rendererBuilder = new PdfRendererBuilder();
                AutoFont.toBuilder(rendererBuilder, u_lstFont.value(), isDir ? filter_isDir : filter_isFile);
                rendererBuilder.withW3cDocument(doc_w3c, baseUri);
                rendererBuilder.toStream(os);
                rendererBuilder.run();
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    @Deprecated //UTF8 Fonts not working, READ: https://github.com/danfickle/openhtmltopdf/wiki/Fonts, How about com.tugalsan.java.core.html.selenium
    public static TGS_UnionExcuseVoid toPdf(Path pathSrcHTM, Path pathDstPDF, Path pathFont_canBeDir_orFile) {
        return TGS_FuncMTCUtils.call(() -> {
            var url = TGS_Url.of(pathSrcHTM.toUri().toURL().toExternalForm());
            return toPdf(url, pathDstPDF, pathFont_canBeDir_orFile);
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    @Deprecated //UTF8 Fonts not working, READ: https://github.com/danfickle/openhtmltopdf/wiki/Fonts, How about com.tugalsan.java.core.html.selenium
    public static TGS_UnionExcuseVoid toPdf(TGS_Url urlSrcHTM, Path pathDstPDF, Path pathFont_canBeDir_orFile) {
        return TGS_FuncMTCUtils.call(() -> {
            //URL
            var url = new URI(urlSrcHTM.toString()).toURL();
            //doc_jsoup
            org.jsoup.nodes.Document doc_jsoup;
            if (url.getProtocol().equalsIgnoreCase("file")) {
                d.ci("toPdf", "fileMode");
                var pathStr = url.getPath();
                d.ci("toPdf", "pathStr", pathStr);
                if (pathStr.startsWith("/")) {
                    pathStr = pathStr.substring(1);
                    d.ci("toPdf", "pathStr", "fixed", pathStr);
                }
                doc_jsoup = Jsoup.parse(Path.of(pathStr), StandardCharsets.UTF_8.toString());
            } else {
                d.ci("toPdf", "urlMode");
                var urlStr = url.toExternalForm();
                d.ci("toPdf", "urlStr", urlStr);
                doc_jsoup = Jsoup.parse(url.openStream(), StandardCharsets.UTF_8.toString(), urlSrcHTM.toString());
            }
            doc_jsoup.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.html);
            doc_jsoup.outputSettings().prettyPrint(true);
            //doc_w3c
            var doc_w3c = new W3CDom().fromJsoup(doc_jsoup);
            //font
            var isDir = TS_DirectoryUtils.isExistDirectory(pathFont_canBeDir_orFile);
            var pathFontDirectory = isDir ? pathFont_canBeDir_orFile : pathFont_canBeDir_orFile.getParent();
            var u_lstFont = AutoFont.findFontsInDirectory(pathFontDirectory);
            if (u_lstFont.isExcuse()) {
                TGS_FuncMTUUtils.thrw(u_lstFont.excuse());
            }
            TGS_FuncMTU_OutBool_In1<Path> filter_isDir = pathFontFile -> true;
            TGS_FuncMTU_OutBool_In1<Path> filter_isFile = pathFontFile -> pathFontFile.equals(pathFont_canBeDir_orFile);
            //skip below line, I donno what to do with that
            var cssEscapedFontFamily = AutoFont.toCSSEscapedFontFamily(u_lstFont.value());
            d.cr("toPdf", "cssEscapedFontFamily", cssEscapedFontFamily);
            //from doc_w3c to pdf
            try (var os = Files.newOutputStream(pathDstPDF)) {
                var rendererBuilder = new PdfRendererBuilder();
                AutoFont.toBuilder(rendererBuilder, u_lstFont.value(), isDir ? filter_isDir : filter_isFile);
                rendererBuilder.withW3cDocument(doc_w3c, url.toExternalForm());
                rendererBuilder.toStream(os);
                rendererBuilder.run();
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    public static TGS_UnionExcuseVoid toHtml(Path srcPDF, Path dstHTM) {
        return TGS_FuncMTCUtils.call(() -> {
            TS_FileUtils.deleteFileIfExists(dstHTM);
            if (TS_FileUtils.isExistFile(dstHTM)) {
                return TGS_UnionExcuseVoid.ofExcuse(d.className(), "toHtml", "ERROR canot delete outputFile " + dstHTM);
            }
            d.cr("toHtml", "begin", srcPDF, dstHTM);
            try (var pdf = Loader.loadPDF(srcPDF.toFile())) {
                try (var output = new PrintWriter(dstHTM.toFile(), StandardCharsets.UTF_8)) {
                    new PDFDomTree().writeText(pdf, output);
                }
            }
            d.cr("toHtml", "end", srcPDF, dstHTM);
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }
}
