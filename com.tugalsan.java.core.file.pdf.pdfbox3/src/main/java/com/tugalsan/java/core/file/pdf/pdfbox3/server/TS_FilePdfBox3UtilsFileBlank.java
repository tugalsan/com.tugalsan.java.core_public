package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.union;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;

public class TS_FilePdfBox3UtilsFileBlank {

    public static TGS_UnionExcuseVoid createBlankFileOfA(Path pdfDest, int from_A0_to_A6, boolean landscape, boolean compress) {
        return TS_FilePdfBox3UtilsDocument.run_new(doc -> {
            TS_FilePdfBox3UtilsPageCreate.ofA(doc, from_A0_to_A6, landscape);
            TS_FilePdfBox3UtilsSave.save(doc, pdfDest, compress);
        });
    }

    public static TGS_UnionExcuseVoid createBlankFileOfSize(Path pdfDest, PDRectangle pageSize, boolean compress) {
        return TS_FilePdfBox3UtilsDocument.run_new(doc -> {
            TS_FilePdfBox3UtilsPageCreate.ofSize(doc, pageSize);
            TS_FilePdfBox3UtilsSave.save(doc, pdfDest, compress);
        });
    }

}
