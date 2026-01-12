package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.stream;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class TS_FilePdfBox3UtilsPageGet {

    public static Optional<PDPage> getPage(PDDocument doc, int pageIdx) {
        var count = TS_FilePdfBox3UtilsPageCount.count(doc);
        if (pageIdx >= 0 && pageIdx < count) {
            return Optional.of(doc.getDocumentCatalog().getPages().get(pageIdx));
        }
        return Optional.empty();
    }

    public static IntStream streamPageIdx(PDDocument doc, int... pageIdx_optional) {
        var count = TS_FilePdfBox3UtilsPageCount.count(doc);
        if (pageIdx_optional == null || pageIdx_optional.length == 0) {
            return IntStream.range(0, count);
        }
        return Arrays.stream(pageIdx_optional).filter(pageIdx -> pageIdx >= 0 && pageIdx < count);
    }

    public static Stream<PDPage> streamPages(PDDocument doc, int... pageIdx_optional) {
        return streamPageIdx(doc).mapToObj(pageIdx -> getPage(doc, pageIdx).orElseThrow());
    }

    public static List<PDPage> getPages(PDDocument doc, int... pageIdx_optional) {
        return TGS_StreamUtils.toLst(streamPages(doc, pageIdx_optional));
    }
}
