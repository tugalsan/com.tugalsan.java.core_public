package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsPageAdd {

    public static void add(PDDocument doc, PDPage page) {
        TGS_FuncMTCUtils.run(() -> {
            doc.addPage(page);
        });
    }
}
