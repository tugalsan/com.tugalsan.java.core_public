package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.file.common;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.url;
import module org.apache.pdfbox;
import module java.desktop;
import java.nio.file.*;

//SKIP: I donno gradle: https://www.ulfdittmer.com/view?PdfboxLayout
//STUDY: closabe -> https://github.com/dhorions/boxable/wiki
@Deprecated //TODO Migration from TS_FilePdfItext to here
public class TS_FilePdfBox3FileCommon extends TS_FileCommonAbstract {

    final private static TS_Log d = TS_Log.of(TS_FilePdfBox3FileCommon.class);

    @Override
    public String getSuperClassName() {
        return d.className();
    }

    public TS_FilePdfBox3FileCommonUtils pdf;
//    public PdfPTable pdfTable = null;
//    public PdfPCell pdfCell = null;
//    public Paragraph pdfParag = null;
//    public Font pdfFont;
//    public Font pdfFont_half;
//    public List<TGS_FontFamily<Font>> fontFamilyFonts_pdf;
//    public List<TGS_FontFamily<Font>> fontFamilyFonts_pdfHalf;
    public Color pdfFontColor = Color.BLACK;

    private TS_FileCommonConfig fileCommonConfig;

    private TS_FilePdfBox3FileCommon(boolean enabled, Path localFile, TGS_Url remoteFile) {
        super(enabled, localFile, remoteFile);
    }

    public static void use(boolean enabled, TS_FileCommonConfig fileCommonConfig, Path localFile, TGS_Url remoteFile, TGS_FuncMTU_In1<TS_FilePdfBox3FileCommon> pdf) {
        var instance = new TS_FilePdfBox3FileCommon(enabled, localFile, remoteFile);
        try {
            instance.use_init(fileCommonConfig);
            pdf.run(instance);
        } catch (Exception e) {
            TGS_FuncUtils.throwIfInterruptedException(e);
            instance.saveFile(e.getMessage());
            throw e;
        } finally {
            instance.saveFile(null);
        }
    }

    private void use_init(TS_FileCommonConfig fileCommonConfig) {
        this.fileCommonConfig = fileCommonConfig;
        if (isClosed()) {
            return;
        }
//        fontFamilyFonts_pdf = TGS_StreamUtils.toLst(
//                IntStream.range(0, fileCommonConfig.fontFamilyPaths.size())
//                        .mapToObj(fontIdx -> new TGS_FontFamily<Font>(
//                        getFontFrom(1, fontIdx, false, false),
//                        getFontFrom(1, fontIdx, true, false),
//                        getFontFrom(1, fontIdx, false, true),
//                        getFontFrom(1, fontIdx, true, true)
//                ))
//        );
//        fontFamilyFonts_pdfHalf = TGS_StreamUtils.toLst(
//                IntStream.range(0, fileCommonConfig.fontFamilyPaths.size())
//                        .mapToObj(fontIdx -> new TGS_FontFamily<Font>(
//                        getFontFrom(0.8f, fontIdx, false, false),
//                        getFontFrom(0.8f, fontIdx, true, false),
//                        getFontFrom(0.8f, fontIdx, false, true),
//                        getFontFrom(0.8f, fontIdx, true, true)
//                ))
//        );
        pdf = new TS_FilePdfBox3FileCommonUtils(localFile);
        setFontStyle();
    }

    public PDDocument getWriter() {
        return document;
    }

    public PDDocument getDocument() {
        return document;
    }
    private PDDocument document;

    @Override
    public boolean createNewPage(int pageSizeAX, boolean landscape, Integer marginLeft0, Integer marginRight0, Integer marginTop0, Integer marginBottom0) {
        return TGS_FuncMTCUtils.call(() -> {
            if (isClosed()) {
                return true;
            }
            d.ci("createNewPage");
            if (document == null) {
                document = new PDDocument();
            }
//            var marginLeft = marginLeft0 == null ? 50 : marginLeft0;
//            var marginRight = marginRight0 == null ? 10 : marginRight0;
//            var marginTop = marginTop0 == null ? 10 : marginTop0;
//            var marginBottom = marginBottom0 == null ? 10 : marginBottom0;
            var page = TGS_FuncMTUEffectivelyFinal.of(PDPage.class)
                    .anoint(val -> new PDPage(PDRectangle.A4))
                    .anointIf(val -> pageSizeAX == 0, val -> new PDPage(PDRectangle.A0))
                    .anointIf(val -> pageSizeAX == 1, val -> new PDPage(PDRectangle.A1))
                    .anointIf(val -> pageSizeAX == 2, val -> new PDPage(PDRectangle.A2))
                    .anointIf(val -> pageSizeAX == 3, val -> new PDPage(PDRectangle.A3))
                    .anointIf(val -> pageSizeAX == 4, val -> new PDPage(PDRectangle.A4))
                    .anointIf(val -> pageSizeAX == 5, val -> new PDPage(PDRectangle.A5))
                    .anointIf(val -> pageSizeAX == 6, val -> new PDPage(PDRectangle.A6))
                    .coronate();
            if (landscape) {
                page.setRotation(90);
            }
            document.addPage(page);
            return true;
        }, e -> {
            d.ct("createNewPage", e);
            return false;
        });
    }

    //TODO http://www.java2s.com/example/java-api/org/apache/pdfbox/pdmodel/pdpage/getmediabox-0-2.html
    //TODO http://www.java2s.com/example/java-api/org/apache/pdfbox/pdmodel/pdpage/getmediabox-0-2.html
    //TODO https://stackoverflow.com/questions/14686013/pdfbox-wrap-text
    @Override
    public boolean saveFile(String errorSource) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addImage(BufferedImage pstImage, Path pstImageLoc, boolean textWrap, int left0_center1_right2, long imageCounter) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beginTableCell(int rowSpan, int colSpan, Integer cellHeight) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean endTableCell(int rotationInDegrees_0_90_180_270) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beginTable(int[] relColSizes) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean endTable() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beginText(int allign_Left0_center1_right2_just3) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean endText() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addText(String text) {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addLineBreak() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setFontStyle() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setFontHeight() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setFontColor() {
        if (isClosed()) {
            return true;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
