package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module java.desktop;
import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsShape {

    public static void rectangleFill(PDPageContentStream pcs, float x, float y, float w, float h, Color color) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setNonStrokingColor(color);
            pcs.addRect(x, y, w, h);
            pcs.fill();
        });
    }

    public static void rectangleDraw(PDPageContentStream pcs, float x, float y, float w, float h, Color color, float lineWidth) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setStrokingColor(color);
            pcs.setLineWidth(lineWidth);
            pcs.addRect(x, y, w, h);
            pcs.stroke();
        });
    }
}
