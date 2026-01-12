package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import org.apache.pdfbox.io.IOUtils;

public class TS_FilePdfBox3UtilsPageMerge {

    private static final TS_Log d = TS_Log.of(TS_FilePdfBox3UtilsPageMerge.class);

    public static TGS_UnionExcuseVoid merge(List<Path> pdfSrcFiles, Path pdfDstFile, boolean compressOnSave, String title, String creator, String subject) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var cosStream = new COSStream()) {
                TS_FileUtils.deleteFileIfExists(pdfDstFile);
                var pdfMerger = new PDFMergerUtility();
                pdfMerger.setDestinationFileName(pdfDstFile.toAbsolutePath().toString());
                for (var nextPdfSrcFile : pdfSrcFiles) {//not streamable: IO EXCEPTION
                    pdfMerger.addSource(nextPdfSrcFile.toFile());
                }
                TS_FilePdfBox3UtilsInfo.set(pdfMerger, cosStream, title, creator, subject);
                pdfMerger.mergeDocuments(IOUtils.createMemoryOnlyStreamCache(), compressOnSave ? CompressParameters.DEFAULT_COMPRESSION : CompressParameters.NO_COMPRESSION);
                return TGS_UnionExcuseVoid.ofVoid();
            }
        }, e -> {
            return TGS_UnionExcuseVoid.ofExcuse(e);
        });
    }

    public TGS_UnionExcuse<InputStream> merge(List<RandomAccessRead> sources, boolean compressOnSave, String title, String creator, String subject) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var cosStream = new COSStream(); ByteArrayOutputStream mergedPDFOutputStream = new ByteArrayOutputStream()) {
                var pdfMerger = new PDFMergerUtility();
                pdfMerger.addSources(sources);
                pdfMerger.setDestinationStream(mergedPDFOutputStream);
                TS_FilePdfBox3UtilsInfo.set(pdfMerger, cosStream, title, creator, subject);
                pdfMerger.mergeDocuments(IOUtils.createMemoryOnlyStreamCache(), compressOnSave ? CompressParameters.DEFAULT_COMPRESSION : CompressParameters.NO_COMPRESSION);
                return TGS_UnionExcuse.of(new ByteArrayInputStream(mergedPDFOutputStream.toByteArray()));
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e), () -> sources.forEach(IOUtils::closeQuietly));
    }
}
