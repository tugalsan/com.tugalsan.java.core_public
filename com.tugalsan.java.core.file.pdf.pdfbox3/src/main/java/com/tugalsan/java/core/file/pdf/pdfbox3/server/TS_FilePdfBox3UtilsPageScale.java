package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.union;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;

public class TS_FilePdfBox3UtilsPageScale {

    public static TGS_UnionExcuseVoid scale(Path pdfSrcFile, Path pdfDstFile, boolean compressOnSave, boolean compressOnStream, TGS_FuncMTU_OutTyped_In1<Float, PDPage> scaleX, TGS_FuncMTU_OutTyped_In1<Float, PDPage> scaleY, int... pageIdxs_optional) {
        return TS_FilePdfBox3UtilsDocument.run_randomAccess(pdfSrcFile, doc -> {
            TS_FilePdfBox3UtilsPageGet.getPages(doc, pageIdxs_optional).forEach(page -> {
                TGS_FuncMTCUtils.run(() -> {
                    var matrix = new Matrix();
                    matrix.scale(scaleX.call(page), scaleY.call(page));
                    try (var cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.PREPEND, compressOnStream);) {
                        cs.transform(matrix);
                    }
                });
            });
            TS_FilePdfBox3UtilsSave.save(doc, pdfDstFile, compressOnSave);
        });
    }

    public static TGS_UnionExcuseVoid scale(Path pdfSrcFile, Path pdfDstFile, boolean compressOnSave, boolean compressOnStream, float scaleX, float scaleY, int... pageIdxs_optional) {
        return scale(pdfSrcFile, pdfDstFile, compressOnSave, compressOnStream, page -> scaleX, page -> scaleY, pageIdxs_optional);
    }

    public static TGS_UnionExcuseVoid scale(Path pdfSrcFile, Path pdfDstFile, boolean compressOnSave, boolean compressOnStream, float scaleXY, int... pageIdxs_optional) {
        return scale(pdfSrcFile, pdfDstFile, compressOnSave, compressOnStream, scaleXY, scaleXY, pageIdxs_optional);
    }

    public static TGS_UnionExcuseVoid scale(Path pdfSrcFile, Path pdfDstFile, boolean compressOnSave, boolean compressOnStream, PDRectangle pageSize, boolean respectPageSize, int... pageIdxs_optional) {
        TGS_FuncMTU_OutTyped_In1<Float, PDPage> scaleX = page -> {
            var xScale = pageSize.getWidth() / page.getMediaBox().getWidth();
            if (respectPageSize) {
                var yScale = pageSize.getHeight() / page.getMediaBox().getHeight();
                return Math.min(xScale, yScale);
            }
            return xScale;
        };
        TGS_FuncMTU_OutTyped_In1<Float, PDPage> scaleY = page -> {
            var yScale = pageSize.getHeight() / page.getMediaBox().getHeight();
            if (respectPageSize) {
                var xScale = pageSize.getWidth() / page.getMediaBox().getWidth();
                return Math.min(xScale, yScale);
            }
            return yScale;
        };
        return scale(pdfSrcFile, pdfDstFile, compressOnSave, compressOnStream, scaleX, scaleY, pageIdxs_optional);
    }
}
