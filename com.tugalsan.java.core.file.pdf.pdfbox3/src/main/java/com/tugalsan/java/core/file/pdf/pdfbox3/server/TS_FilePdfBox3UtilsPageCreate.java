package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.log;
import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsPageCreate {

    final private static TS_Log d = TS_Log.of(TS_FilePdfBox3UtilsPageCreate.class);

    public static PDPage ofSize(PDDocument doc, PDRectangle pageSize) {
        var p = new PDPage(pageSize);
        doc.addPage(p);
        return p;
    }

    public static PDPage ofA(PDDocument doc, int from_A0_to_A6, boolean landscape) {
        var p = new PDPage(TS_FilePdfBox3UtilsPageSize.getSizeByA(from_A0_to_A6, landscape));
        doc.addPage(p);
        return p;
    }

}
