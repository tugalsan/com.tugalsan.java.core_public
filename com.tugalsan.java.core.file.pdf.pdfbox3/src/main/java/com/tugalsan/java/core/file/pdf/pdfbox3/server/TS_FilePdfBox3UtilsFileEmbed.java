package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.random;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.io.*;
import java.nio.charset.*;
import java.util.*;


public class TS_FilePdfBox3UtilsFileEmbed {

    public static PDComplexFileSpecification createEmbeddableFile_TextFile(PDDocument doc, String fileName, byte[] data, Charset charset) {
        return TGS_FuncMTCUtils.call(() -> {
            var cfs = new PDComplexFileSpecification();
            cfs.setFile("Test.txt");
            cfs.setFileUnicode("Test.txt");
            var bais = new ByteArrayInputStream(data);
            var ef = new PDEmbeddedFile(doc, bais);
            ef.setSubtype("text/plain");
            ef.setSize(data.length);
            ef.setCreationDate(new GregorianCalendar());
            cfs.setEmbeddedFile(ef);
            cfs.setEmbeddedFileUnicode(ef);
            return cfs;
        });
    }

    public static void setEmbeddableFiles(PDDocument doc, List<PDComplexFileSpecification> lst) {
        TGS_FuncMTCUtils.run(() -> {
            //PREPARE LIST
            var efntrList = new PDEmbeddedFilesNameTreeNode();
            lst.forEach(item -> {
                efntrList.setNames(Collections.singletonMap(TGS_RandomUtils.nextString(16, true, true, true, false, null), item));
            });
            List<PDEmbeddedFilesNameTreeNode> kids = new ArrayList();
            kids.add(efntrList);

            //EMBED LIST
            var efntrMain = new PDEmbeddedFilesNameTreeNode();
            efntrMain.setKids(kids);
            var names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
            names.setEmbeddedFiles(efntrMain);
            doc.getDocumentCatalog().setNames(names);
            doc.getDocumentCatalog().setPageMode(PageMode.USE_ATTACHMENTS);
        });
    }
}
