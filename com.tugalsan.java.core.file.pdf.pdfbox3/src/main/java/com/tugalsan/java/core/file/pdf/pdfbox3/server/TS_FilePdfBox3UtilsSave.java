package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;

public class TS_FilePdfBox3UtilsSave {

    public static void save(PDDocument doc, Path dest, boolean compress) {
        TGS_FuncMTCUtils.run(() -> {
            if (TS_FilePdfBox3UtilsPageCount.count(doc) == 0) {//FIX
                var blankPage = TS_FilePdfBox3UtilsPageCreate.ofA(doc, 4, false);
                TS_FilePdfBox3UtilsPageAdd.add(doc, blankPage);
            }
            doc.save(dest.toFile(), compress ? CompressParameters.DEFAULT_COMPRESSION : CompressParameters.NO_COMPRESSION);
        });
    }
}
