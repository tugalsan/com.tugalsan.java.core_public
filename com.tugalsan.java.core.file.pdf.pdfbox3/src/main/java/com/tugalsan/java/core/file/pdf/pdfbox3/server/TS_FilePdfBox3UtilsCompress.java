package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;
import module java.desktop;
import java.io.*;
import java.nio.file.*;
import org.apache.pdfbox.Loader;

public class TS_FilePdfBox3UtilsCompress {

    public static TGS_UnionExcuseVoid compress(Path pdfSrcFile, Path pdfDstFile, float compQual_fr0_to1, boolean lossless) {
        return TGS_FuncMTCUtils.call(() -> {
            var compQual = Math.max(0, Math.min(compQual_fr0_to1, 1));
            try (var doc = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfSrcFile.toAbsolutePath().toString()))) {
                var pages = doc.getPages();
                final ImageWriter imgWriter;
                final ImageWriteParam iwp;
                if (lossless) {
                    var tiffWriters = ImageIO.getImageWritersBySuffix("png");
                    imgWriter = tiffWriters.next();
                    iwp = imgWriter.getDefaultWriteParam();
                    //iwp.setCompressionMode(ImageWriteParam.MODE_DISABLED);
                } else {
                    var jpgWriters = ImageIO.getImageWritersByFormatName("jpeg");
                    imgWriter = jpgWriters.next();
                    iwp = imgWriter.getDefaultWriteParam();
                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    iwp.setCompressionQuality(compQual);
                }
                for (var p : pages) {
                    compress_scanResources(p.getResources(), doc, imgWriter, iwp, lossless);
                }
                doc.save(pdfDstFile.toFile());
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    private static void compress_scanResources(
            final PDResources rList,
            final PDDocument doc,
            final ImageWriter imgWriter,
            final ImageWriteParam iwp, boolean lossless)
            throws FileNotFoundException, IOException {
        var xNames = rList.getXObjectNames();
        for (var xName : xNames) {
            final var xObj = rList.getXObject(xName);
            if (!(xObj instanceof PDImageXObject)) {
                continue;
            }
            var o = (PDFormXObject) xObj;
            compress_scanResources(o.getResources(), doc, imgWriter, iwp, lossless);
            var img = (PDImageXObject) xObj;
            System.out.println("Compressing image: " + xName.getName());
            var baos = new ByteArrayOutputStream();
            imgWriter.setOutput(ImageIO.createImageOutputStream(baos));
            var bi = img.getImage();
            IIOImage iioi;
            iioi = switch (bi.getTransparency()) {
                case BufferedImage.OPAQUE ->
                    new IIOImage(bi, null, null);
                case BufferedImage.TRANSLUCENT ->
                    new IIOImage(img.getOpaqueImage(), null, null);
                default ->
                    new IIOImage(img.getOpaqueImage(), null, null);
            };
            imgWriter.write(null, iioi, iwp);
            var bais = new ByteArrayInputStream(baos.toByteArray());
            final PDImageXObject imgNew;
            if (lossless) {
                imgNew = LosslessFactory.createFromImage(doc, img.getImage());
            } else {
                imgNew = JPEGFactory.createFromStream(doc, bais);
            }
            rList.put(xName, imgNew);
        }
    }
}
