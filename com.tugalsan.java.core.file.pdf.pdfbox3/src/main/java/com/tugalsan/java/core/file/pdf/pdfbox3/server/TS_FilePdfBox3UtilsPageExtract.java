package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.union;
import java.nio.file.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class TS_FilePdfBox3UtilsPageExtract {

    private static final TS_Log d = TS_Log.of(true, TS_FilePdfBox3UtilsPageExtract.class);

    public static int[] pageIdxRange(int beginIdx, int endIdx) {
        if (beginIdx > endIdx) {
            return pageIdxRange(endIdx, beginIdx);
        }
        return IntStream.rangeClosed(beginIdx, endIdx).toArray();
    }

    public static TGS_UnionExcuseVoid extract(Path pdfSrc, Path pdfDst, boolean compressOnSave, int... pageIdxs) {
        if (d.infoEnable) {
            Arrays.stream(pageIdxs).forEachOrdered(pageIdx -> {
                d.ci("extract", "inputed", "pageIdx", pageIdx);
            });
        }
        return TS_FilePdfBox3UtilsDocument.run_randomAccess(pdfSrc, docIn -> {
            var u_out = TS_FilePdfBox3UtilsDocument.run_new(docOut -> {
                TS_FilePdfBox3UtilsPageGet.streamPageIdx(docIn, pageIdxs).forEachOrdered(pageIdx -> {
//                    var fromPage = pageNr;
//                    var toPage = pageNr;
//                    var splitter = new Splitter();
//                    splitter.setStartPage(fromPage);
//                    splitter.setEndPage(toPage);
//                    splitter.setSplitAtPage(toPage - fromPage + 1);
//                    var lst = splitter.split(doc);
//                    var pdfDocPartial = lst.get(0);
                    docOut.addPage(docIn.getPage(pageIdx));
                    d.ci("extract", "processed", "pageIdx", pageIdx);
                });
                TS_FilePdfBox3UtilsSave.save(docOut, pdfDst, compressOnSave);
            });
            if (u_out.isExcuse()) {
                TGS_FuncMTUUtils.thrw(u_out.excuse());
            }
        });
    }
}
