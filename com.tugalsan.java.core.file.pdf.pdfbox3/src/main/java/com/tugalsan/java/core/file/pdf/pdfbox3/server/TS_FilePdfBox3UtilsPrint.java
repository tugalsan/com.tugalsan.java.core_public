package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.union;
import module java.desktop;
import module org.apache.pdfbox;//import module pdfbox.examples;
import java.nio.file.*;

public final class TS_FilePdfBox3UtilsPrint {

    public static TGS_UnionExcuseVoid print(Path pdfSrcFile) {
        return print(pdfSrcFile, false, false, null, null);
    }

    public static TGS_UnionExcuseVoid print(Path pdfSrcFile, boolean showDialog) {
        return print(pdfSrcFile, showDialog, false, null, null);
    }

    public static record PageIdxRange(int begin, int end) {

    }

    public static record CustomPaperSize(int width, int height) {

    }

    public static TGS_UnionExcuseVoid print(Path pdfSrcFile, boolean showDialog, boolean doubleSidedIfPossible,
            PageIdxRange optional_pageIdxRange, CustomPaperSize optional_customPaperSize) {
        return TS_FilePdfBox3UtilsDocument.run_randomAccess(pdfSrcFile, doc -> {
            TGS_FuncMTCUtils.call(() -> {
                var job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(doc));
                if (optional_customPaperSize != null) {
                    var paper = new Paper();
                    paper.setSize(optional_customPaperSize.width(), optional_customPaperSize.height());
                    paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                    var pageFormat = new PageFormat();
                    pageFormat.setPaper(paper);
                    var book = new Book();
                    book.append(new PDFPrintable(doc), pageFormat, doc.getNumberOfPages());
                    job.setPageable(book);
                }
                var attr = new HashPrintRequestAttributeSet();
                if (optional_pageIdxRange != null) {
                    attr.add(new PageRanges(optional_pageIdxRange.begin(), optional_pageIdxRange.end()));
                }
                var vp = doc.getDocumentCatalog().getViewerPreferences();
                if (vp != null && vp.getDuplex() != null) {
                    var dp = vp.getDuplex();
                    if (PDViewerPreferences.DUPLEX.DuplexFlipLongEdge.toString().equals(dp)) {
                        attr.add(Sides.TWO_SIDED_LONG_EDGE);
                    } else if (PDViewerPreferences.DUPLEX.DuplexFlipShortEdge.toString().equals(dp)) {
                        attr.add(Sides.TWO_SIDED_SHORT_EDGE);
                    } else if (PDViewerPreferences.DUPLEX.Simplex.toString().equals(dp)) {
                        attr.add(Sides.ONE_SIDED);
                    }
                }
                if (showDialog) {
                    if (job.printDialog()) {
                        job.print();
                    }
                } else {
                    job.print();
                }
                return TGS_UnionExcuseVoid.ofVoid();
            });
        });
    }
}
