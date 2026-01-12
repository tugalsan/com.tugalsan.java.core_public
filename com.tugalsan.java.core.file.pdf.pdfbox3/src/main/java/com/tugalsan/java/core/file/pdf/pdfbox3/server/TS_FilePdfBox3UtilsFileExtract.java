package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TS_FilePdfBox3UtilsFileExtract {

    public static TGS_UnionExcuseVoid extractEmbeddedFiles(Path pdfSrcFile) {
        return TS_FilePdfBox3UtilsDocument.run_randomAccess(pdfSrcFile, doc -> {
            var namesDictionary = new PDDocumentNameDictionary(doc.getDocumentCatalog());
            var efTree = namesDictionary.getEmbeddedFiles();
            if (efTree != null) {
                extractFiles_fromTree(efTree, pdfSrcFile.getParent());
            }
            for (var page : doc.getPages()) {
                extractFiles_fromPage(page, pdfSrcFile.getParent());
            }
        });
    }

    private static void extractFiles_fromPage(PDPage page, Path targetFolder) {
        TGS_FuncMTCUtils.run(() -> {
            for (var annotation : page.getAnnotations()) {
                if (annotation instanceof PDAnnotationFileAttachment annotationFileAttachment) {
                    var fileSpec = annotationFileAttachment.getFile();
                    if (fileSpec instanceof PDComplexFileSpecification complexFileSpec) {
                        var embeddedFile = getEmbeddedFile_fromFileSpec(complexFileSpec);
                        if (embeddedFile != null) {
                            extractFile_byFile(targetFolder, complexFileSpec.getFilename(), embeddedFile);
                        }
                    }
                }
            }
        });
    }

    private static void extractFiles_fromTree(PDEmbeddedFilesNameTreeNode efTree, Path targetFolder) {
        TGS_FuncMTCUtils.run(() -> {
            var names = efTree.getNames();
            if (names == null) {
                var kids = efTree.getKids();
                for (var node : kids) {
                    extractFiles_fromMap(node.getNames(), targetFolder);
                }
            } else {
                extractFiles_fromMap(names, targetFolder);
            }
        });
    }

    private static void extractFiles_fromMap(Map<String, PDComplexFileSpecification> names, Path targetFolder) {
        names.entrySet().stream()
                .map(entry -> entry.getValue())
                .forEachOrdered(fileSpec -> {
                    var embeddedFile = getEmbeddedFile_fromFileSpec(fileSpec);
                    if (embeddedFile != null) {
                        extractFile_byFile(targetFolder, fileSpec.getFilename(), embeddedFile);
                    }
                });
    }

    private static void extractFile_byFile(Path targetFolder, String filename, PDEmbeddedFile embeddedFile) {
        TGS_FuncMTCUtils.run(() -> {
            var file = targetFolder.resolve(filename);
            try (var fos = new FileOutputStream(file.toFile())) {
                fos.write(embeddedFile.toByteArray());
            }
        });
    }

    private static PDEmbeddedFile getEmbeddedFile_fromFileSpec(PDComplexFileSpecification fileSpec) {
        PDEmbeddedFile embeddedFile = null;
        if (fileSpec != null) {
            embeddedFile = fileSpec.getEmbeddedFileUnicode();
            if (embeddedFile == null) {
                embeddedFile = fileSpec.getEmbeddedFileDos();
            }
            if (embeddedFile == null) {
                embeddedFile = fileSpec.getEmbeddedFileMac();
            }
            if (embeddedFile == null) {
                embeddedFile = fileSpec.getEmbeddedFileUnix();
            }
            if (embeddedFile == null) {
                embeddedFile = fileSpec.getEmbeddedFile();
            }
        }
        return embeddedFile;
    }

}
