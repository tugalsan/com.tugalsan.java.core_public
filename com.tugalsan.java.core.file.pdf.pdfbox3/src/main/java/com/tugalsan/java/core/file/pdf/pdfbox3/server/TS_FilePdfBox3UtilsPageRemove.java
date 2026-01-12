package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import java.nio.file.*;

public class TS_FilePdfBox3UtilsPageRemove {

    private static final TS_Log d = TS_Log.of(TS_FilePdfBox3UtilsPageRemove.class);

    public static TGS_UnionExcuseVoid remove(Path pdfSrc, Path pdfDest, boolean compressOnSave, int... pageIdxs_optional) {
        return TS_FilePdfBox3UtilsDocument.run_randomAccess(pdfSrc, doc -> {
            if (pageIdxs_optional == null || pageIdxs_optional.length == 0) {
                TGS_FuncMTUUtils.thrw(d.className(), "remove", "pageIdxs_optional is empty");
            }
            TGS_ListSortUtils.sortPrimativeIntReversed(pageIdxs_optional);
            TS_FilePdfBox3UtilsPageGet.streamPageIdx(doc, pageIdxs_optional).forEachOrdered(pageIdx -> {
                doc.removePage(pageIdx);
            });
            TS_FilePdfBox3UtilsSave.save(doc, pdfDest, compressOnSave);
        });
    }
}
