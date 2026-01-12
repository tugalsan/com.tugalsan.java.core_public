package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.Path;
import org.apache.pdfbox.Loader;

public class TS_FilePdfBox3UtilsEncrypt {

    public static boolean isEncrypted(PDDocument doc) {
        return doc.isEncrypted();
    }

    public static void encrypt(Path srcPdfFile, Path dstPdfFile, String pass) throws Exception {
        try (var doc = Loader.loadPDF(srcPdfFile.toFile())) {
            var ap = new AccessPermission();
            var spp = new StandardProtectionPolicy(pass, pass, ap);
            spp.setEncryptionKeyLength(128);
            spp.setPermissions(ap);
            doc.protect(spp);
            doc.save(dstPdfFile.toFile());
            doc.close();
        }
    }

    public static void de_encrypt(Path srcPdfFile, Path dstPdfFile, String pass) throws Exception {
        try (var doc = Loader.loadPDF(srcPdfFile.toFile(), pass)) {
            doc.setAllSecurityToBeRemoved(true);
            doc.save(dstPdfFile.toFile());
            doc.close();
        }
    }
}
