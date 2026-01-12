package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;
import org.apache.pdfbox.Loader;

public class TS_FilePdfBox3UtilsDocument {

    public static TGS_UnionExcuseVoid run_new(TGS_FuncMTU_In1<PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = new PDDocument()) {
                doc.run(_doc);
                return TGS_UnionExcuseVoid.ofVoid();
            }
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    public static TGS_UnionExcuseVoid run_basic(Path pdfSrcFile, TGS_FuncMTU_In1<PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfSrcFile.toFile()))) {
                doc.run(_doc);
                return TGS_UnionExcuseVoid.ofVoid();
            }
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    public static TGS_UnionExcuseVoid run_randomAccess(Path pdfSrcFile, TGS_FuncMTU_In1<PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = Loader.loadPDF(pdfSrcFile.toFile())) {
                doc.run(_doc);
                return TGS_UnionExcuseVoid.ofVoid();
            }
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    public static <R> TGS_UnionExcuse<R> call_new(TGS_FuncMTU_OutTyped_In1<TGS_UnionExcuse<R>, PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = new PDDocument()) {
                return doc.call(_doc);
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static <R> TGS_UnionExcuse<R> call_basic(Path pdfSrcFile, TGS_FuncMTU_OutTyped_In1<TGS_UnionExcuse<R>, PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfSrcFile.toFile()))) {
                return doc.call(_doc);
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static <R> TGS_UnionExcuse<R> call_randomAccess(Path pdfSrcFile, TGS_FuncMTU_OutTyped_In1<TGS_UnionExcuse<R>, PDDocument> doc) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var _doc = Loader.loadPDF(pdfSrcFile.toFile())) {
                return doc.call(_doc);
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
