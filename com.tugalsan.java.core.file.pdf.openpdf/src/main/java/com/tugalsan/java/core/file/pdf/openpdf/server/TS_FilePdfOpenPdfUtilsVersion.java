package com.tugalsan.java.core.file.pdf.openpdf.server;

import module com.github.librepdf.openpdf;

public class TS_FilePdfOpenPdfUtilsVersion {

    private TS_FilePdfOpenPdfUtilsVersion() {

    }

    public static String isVersion(PdfReader reader) {
        return reader.getPdfVersion();
    }
}
