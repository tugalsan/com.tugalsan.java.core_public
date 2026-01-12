package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.file.img;
import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.shape;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module java.desktop;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;
import java.util.List;

public class TS_FilePdfBox3UtilsImage {

    final private static TS_Log d = TS_Log.of(false, TS_FilePdfBox3UtilsImage.class);

    public static TGS_UnionExcuse<BufferedImage> toBufferedImage(Path pdfSrc, int pageIdx, float scale) {
        return TS_FilePdfBox3UtilsDocument.call_randomAccess(pdfSrc, docIn -> {
            return TGS_FuncMTCUtils.call(() -> {
                var pdfRenderer = new PDFRenderer(docIn);
                var bi = pdfRenderer.renderImage(pageIdx, scale);
                return TGS_UnionExcuse.of(bi);
            });
        });
    }

    public static PDImageXObject toPDImageXObject(PDDocument doc, Path imgFile) {
        return TGS_FuncMTCUtils.call(() -> {
            return PDImageXObject.createFromFile(imgFile.toAbsolutePath().toString(), doc);
        });
    }

    public static PDImageXObject toPDImageXObject(PDDocument doc, BufferedImage bi) {
        return TGS_FuncMTCUtils.call(() -> {
            return LosslessFactory.createFromImage(doc, bi);
        });
    }

    public static TGS_UnionExcuse<BufferedImage> toBufferedImage(Path pdfSrcFile, int pageIndex, Integer optionalDPI_DefaultIs300) {
        return TS_FilePdfBox3UtilsDocument.call_randomAccess(pdfSrcFile, doc -> {
            return TGS_FuncMTCUtils.call(() -> {
                var bi = optionalDPI_DefaultIs300 == null
                        ? new PDFRenderer(doc).renderImage(pageIndex)
                        : new PDFRenderer(doc).renderImageWithDPI(pageIndex, 300);
                return TGS_UnionExcuse.of(bi);
            }, e -> TGS_UnionExcuse.ofExcuse(e));
        });
    }

    public static void insertImage(PDDocument doc, PDPage page, PDImageXObject pdImage, int offsetX, int offsetY, float scale, boolean compress) {
        TGS_FuncMTCUtils.run(() -> {
            try (var contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, compress, true)) {
                contentStream.drawImage(pdImage, offsetX, offsetY, pdImage.getWidth() * scale, pdImage.getHeight() * scale);
            }
        });
    }

    public static List<TGS_UnionExcuse<Path>> toPdf_fromImageFolder_A4PORT(Path directory, boolean skipIfExists, boolean deleteIMGAfterConversion, float quality, boolean compressOnInsertImage, boolean compressOnSave, boolean castToRGB) {
        d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#10");
        var subFiles = TS_DirectoryUtils.subFiles(directory, null, false, false);
        d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#20");
        List<TGS_UnionExcuse<Path>> convertedFiles = TGS_ListUtils.of();
        d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#30");
        subFiles.stream().filter(subFile -> isSupported(subFile)).forEach(imgFile -> {
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":1", imgFile);
            var pdfFile = imgFile.resolveSibling(TS_FileUtils.getNameLabel(imgFile) + ".pdf");
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":2", imgFile);
            if (TS_FileUtils.isExistFile(pdfFile)) {
                if (skipIfExists) {
                    d.ci("ofPdf_fromImageFolder_A4PORT", "directory", directory, "#100", "skipIfExists", imgFile);
                    return;
                } else {
                    TS_FileUtils.deleteFileIfExists(pdfFile);
                }
            }
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":3", imgFile);
            var u_file = toPdf_fromImageFile_A4PORT(imgFile, pdfFile, quality, compressOnInsertImage, compressOnSave, castToRGB);
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":4", imgFile);
            if (u_file.isExcuse()) {
                d.ce("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", "isExcuse", imgFile, u_file.excuse().getMessage());
                convertedFiles.add(u_file.toExcuse());
            } else {
                d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":5", imgFile);
                convertedFiles.add(TGS_UnionExcuse.of(pdfFile));
            }
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":6", imgFile);
            if (deleteIMGAfterConversion) {
                TS_FileUtils.deleteFileIfExists(imgFile);
            }
            d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#100", ":7", imgFile);
        });
        d.ci("toPdf_fromImageFolder_A4PORT", "directory", directory, "#200");
        return convertedFiles;
    }

    public static TGS_UnionExcuseVoid toPdf_fromImage(BufferedImage srcIMG, Path dstPDF, float quality, boolean compressOnInsertImage, boolean compressOnSave, boolean castToRGB) {
        var a4ImageWidth = 612;
        var a4ImageHeight = 792;
        var offsetX = 0;
        var offsetY = 0;
        var scale = 1f;
        TS_FileUtils.deleteFileIfExists(dstPDF);
        var bi = TS_FileImageUtils.autoSizeRespectfully(
                srcIMG,
                new TGS_ShapeDimension(a4ImageWidth, a4ImageHeight),
                quality
        );
        return TS_FilePdfBox3UtilsDocument.run_new(doc -> {
            var page = new PDPage();
            doc.addPage(page);
            var pdImageXObject = toPDImageXObject(doc, bi);
            insertImage(doc, page, pdImageXObject, offsetX, offsetY, scale, compressOnInsertImage);
            TS_FilePdfBox3UtilsSave.save(doc, dstPDF, compressOnSave);
        });
    }

    public static TGS_UnionExcuseVoid toPdf_fromImageFile_A4PORT(Path srcIMGFile, Path dstPDF, float quality, boolean compressOnInsertImage, boolean compressOnSave, boolean castToRGB) {
        var srcIMG = TS_FileImageUtils.readImageFromFile(srcIMGFile, castToRGB);
        return toPdf_fromImage(srcIMG, dstPDF, quality, compressOnInsertImage, compressOnSave, castToRGB);
    }

    public static boolean isSupported(Path imgFile) {
        var fn = TGS_CharSetCast.current().toLowerCase(imgFile.getFileName().toString());
        return fn.endsWith(".jpg") || fn.endsWith(".jpeg") || fn.endsWith(".tif") || fn.endsWith(".tiff") || fn.endsWith(".gif") || fn.endsWith(".bmp") || fn.endsWith(".png");
    }
}
