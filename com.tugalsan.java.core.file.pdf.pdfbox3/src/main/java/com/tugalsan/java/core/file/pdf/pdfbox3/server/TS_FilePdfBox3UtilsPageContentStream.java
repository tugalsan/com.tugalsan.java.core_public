package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsPageContentStream {

    public static void run_pageContentStream(PDDocument doc, PDPage page, boolean apppend, boolean compress, boolean reset, TGS_FuncMTU_In1<PDPageContentStream> pageContentStream) {
        TGS_FuncMTCUtils.run(() -> {
            try (var _contentStream = new PDPageContentStream(doc, page, apppend ? PDPageContentStream.AppendMode.APPEND : PDPageContentStream.AppendMode.PREPEND, compress, true)) {
                pageContentStream.run(_contentStream);
            }
        });
    }

    public static <R> R call_pageContentStream(PDDocument doc, PDPage page, boolean apppend, boolean compress, boolean reset, TGS_FuncMTU_OutTyped_In1<R, PDPageContentStream> pageContentStream) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _contentStream = new PDPageContentStream(doc, page, apppend ? PDPageContentStream.AppendMode.APPEND : PDPageContentStream.AppendMode.PREPEND, compress, true)) {
                return pageContentStream.call(_contentStream);
            }
        });
    }

}
