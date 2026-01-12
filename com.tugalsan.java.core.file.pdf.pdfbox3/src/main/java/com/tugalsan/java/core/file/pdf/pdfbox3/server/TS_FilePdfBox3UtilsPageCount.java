package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.union;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;

public class TS_FilePdfBox3UtilsPageCount {

    public static TGS_UnionExcuse<Integer> count(Path pdfSrc) {
        return TS_FilePdfBox3UtilsDocument.call_randomAccess(pdfSrc, doc -> {
            return TGS_UnionExcuse.of(doc.getNumberOfPages());
        });
    }

    public static int count(PDDocument doc) {
        return doc.getDocumentCatalog().getPages().getCount();
    }

}
