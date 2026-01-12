package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsPageSize {

    public static PDRectangle getSizeFromPage(PDPage page) {
        return page.getMediaBox();
    }

    public static PDRectangle getSizeByA(int from_A0_to_A6, boolean landscape) {
        //landscape: or use page.setRotation(90)
        PDRectangle port;
        if (from_A0_to_A6 <= 0) {
            port = PDRectangle.A0;
        } else if (from_A0_to_A6 == 1) {
            port = PDRectangle.A1;
        } else if (from_A0_to_A6 == 2) {
            port = PDRectangle.A2;
        } else if (from_A0_to_A6 == 3) {
            port = PDRectangle.A3;
        } else if (from_A0_to_A6 == 4) {
            port = PDRectangle.A4;
        } else if (from_A0_to_A6 == 5) {
            port = PDRectangle.A5;
        } else {
            port = PDRectangle.A6;
        }
        return landscape ? new PDRectangle(port.getHeight(), port.getWidth()) : port;
    }

}
