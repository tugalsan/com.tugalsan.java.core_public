package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module org.apache.pdfbox;//import module pdfbox.examples;
import module java.desktop;
import java.nio.file.*;

//    private static String GOOGLEPDF () "https://docs.google.com/gview?url=";
public class TS_FilePdfBox3FileCommonUtils {

    final private static TS_Log d = TS_Log.of(TS_FilePdfBox3FileCommonUtils.class);
    final private static boolean COMPRESS_ON_STREAM = true;
    final private static boolean COMPRESS_ON_SAVE = true;

    public static Color getFONT_COLOR_BLACK() {
        return Color.BLACK;
    }

    public static Color getFONT_COLOR_BLUE() {
        return Color.BLUE;
    }

    public static Color getFONT_COLOR_CYAN() {
        return Color.CYAN;
    }

    public static Color getFONT_COLOR_DARK_GRAY() {
        return Color.DARK_GRAY;
    }

    public static Color getFONT_COLOR_GRAY() {
        return Color.GRAY;
    }

    public static Color getFONT_COLOR_GREEN() {
        return Color.GREEN;
    }

    public static Color getFONT_COLOR_LIGHT_GRAY() {
        return Color.LIGHT_GRAY;
    }

    public static Color getFONT_COLOR_MAGENTA() {
        return Color.MAGENTA;
    }

    public static Color getFONT_COLOR_ORANGE() {
        return Color.ORANGE;
    }

    public static Color getFONT_COLOR_PINK() {
        return Color.PINK;
    }

    public static Color getFONT_COLOR_RED() {
        return Color.RED;
    }

    public static Color getFONT_COLOR_YELLOW() {
        return Color.YELLOW;
    }

    public static PDRectangle getPAGE_SIZE_A0_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(0, true);
    }

    public static PDRectangle getPAGE_SIZE_A0_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(0, false);
    }

    public static PDRectangle getPAGE_SIZE_A1_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(1, true);
    }

    public static PDRectangle getPAGE_SIZE_A1_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(1, false);
    }

    public static PDRectangle getPAGE_SIZE_A2_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(2, true);
    }

    public static PDRectangle getPAGE_SIZE_A2_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(2, true);
    }

    public static PDRectangle getPAGE_SIZE_A3_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(3, true);
    }

    public static PDRectangle getPAGE_SIZE_A3_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(3, true);
    }

    public static PDRectangle getPAGE_SIZE_A4_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(4, true);
    }

    public static PDRectangle getPAGE_SIZE_A4_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(4, false);
    }

    public static PDRectangle getPAGE_SIZE_A5_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(5, true);
    }

    public static PDRectangle getPAGE_SIZE_A5_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(5, false);
    }

    public static PDRectangle getPAGE_SIZE_A6_LAND() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(5, true);
    }

    public static PDRectangle getPAGE_SIZE_A6_PORT() {
        return TS_FilePdfBox3UtilsPageSize.getSizeByA(6, false);
    }

//    public Writer getWriter() {
//        return writer;
//    }
//    private Writer writer;
    public PDDocument getDocument() {
        return document;
    }
    private PDDocument document;

    public static record Page(PDPage page, PDRectangle size, PDPageContentStream stream, int marginLeft, int marginRight, int marginTop, int marginBottom) {

    }

    public Page getPage() {
        return page;
    }
    private Page page;

    public Path getFile() {
        return file;
    }
    private final Path file;

    public TS_FilePdfBox3FileCommonUtils(Path file) {
        this.file = file;
    }

    public void createNewPage(int pageSizeAX0, boolean landscape, Integer marginLeft0, Integer marginRight0, Integer marginTop0, Integer marginBottom0) {
        TGS_FuncMTCUtils.run(() -> {
            d.ci("createNewPage");
            var marginLeft = marginLeft0 == null ? 50 : marginLeft0;
            var marginRight = marginRight0 == null ? 10 : marginRight0;
            var marginTop = marginTop0 == null ? 10 : marginTop0;
            var marginBottom = marginBottom0 == null ? 10 : marginBottom0;
            var pageSizeAX = TGS_FuncMTUEffectivelyFinal.of(pageSizeAX0)
                    .anointIf(val -> val < 0, val -> 0)
                    .anointIf(val -> val > 6, val -> 6)
                    .coronate();
            var pageSize = TGS_FuncMTUEffectivelyFinal.of(getPAGE_SIZE_A4_PORT())
                    .anointIf(val -> pageSizeAX == 0, val -> landscape ? getPAGE_SIZE_A0_LAND() : getPAGE_SIZE_A0_PORT())
                    .anointIf(val -> pageSizeAX == 1, val -> landscape ? getPAGE_SIZE_A1_LAND() : getPAGE_SIZE_A1_PORT())
                    .anointIf(val -> pageSizeAX == 2, val -> landscape ? getPAGE_SIZE_A2_LAND() : getPAGE_SIZE_A2_PORT())
                    .anointIf(val -> pageSizeAX == 3, val -> landscape ? getPAGE_SIZE_A3_LAND() : getPAGE_SIZE_A3_PORT())
                    .anointIf(val -> pageSizeAX == 4, val -> landscape ? getPAGE_SIZE_A4_LAND() : getPAGE_SIZE_A4_PORT())
                    .anointIf(val -> pageSizeAX == 5, val -> landscape ? getPAGE_SIZE_A5_LAND() : getPAGE_SIZE_A5_PORT())
                    .anointIf(val -> pageSizeAX == 6, val -> landscape ? getPAGE_SIZE_A6_LAND() : getPAGE_SIZE_A6_PORT())
                    .coronate();
            if (document == null) {
                document = new PDDocument();
            }
            var pdPage = TS_FilePdfBox3UtilsPageCreate.ofSize(document, pageSize);
            document.addPage(pdPage);
            page = new Page(pdPage, pageSize,
                    new PDPageContentStream(document, pdPage, PDPageContentStream.AppendMode.OVERWRITE, COMPRESS_ON_STREAM, false),
                    marginLeft, marginRight, marginTop, marginBottom
            );
        });
    }

    public static enum Allign {
        LEFT, RIGHT, CENTER, JUSTIFIED
    }
    private Allign allign = Allign.LEFT;

    public void setAlignLeft() {
        allign = Allign.LEFT;
    }

    public void setAlignRight() {
        allign = Allign.RIGHT;
    }

    public void setAlignCenter() {
        allign = Allign.CENTER;
    }

    public void setAlignJustified() {
        allign = Allign.JUSTIFIED;
    }

//    public Paragraph createParagraph() {
//        return new Paragraph();
//    }
//
//    public Paragraph createParagraph(Font font) {
//        var p = new Paragraph();
//        p.setFont(font);
//        return p;
//    }
//
//    private Chunk createChunkText(CharSequence text) {
//        return new Chunk(text.toString());
//    }
//
    ////    private Chunk createChunkLineSeperator(LineSeparator ls) {
////        return new Chunk(ls);
////    }
//    private Chunk createChunkText(CharSequence text, Font font) {
//        var c = new Chunk(text.toString());
//        c.setFont(font);
//        return c;
//    }
//
//    private Chunk createChunkNewLine() {
//        return Chunk.NEWLINE;
//    }
//
//    public Image createImage(java.awt.Image imageFile, Color color) {
//        return TGS_FuncMTCUtils.call(() -> Image.getInstance(imageFile, color));
//    }
//
//    public Image createImage(CharSequence filePath) {
//        return TGS_FuncMTCUtils.call(() -> Image.getInstance(filePath.toString()));
//    }
//
//    private static void addImage2Document(Document document, Image image) throws DocumentException {
//        var c = new Chunk(image, 0, -image.getHeight() / 2, true);
//        document.add(c);
//    }
//
//    private static void addImage2Cell(PdfPCell cell, Image image) {
//        var h = image.getHeight();
//        var offsetX = -1;
//        var offsetY = 0;
//        var c = new Chunk(image, offsetX + 0, offsetY + -h / 2, true);
//        cell.setMinimumHeight(h);
//        d.ci("addImage2Cell", "image.getHeight()", h);
//        cell.addElement(c);
//    }
//
//    public void addImageToPageLeft(java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        addImageToPageLeft(createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK), textWrap, transperancyAsWhite);
//    }
//
//    public void addImageToPageLeft(Image image, boolean textWrap, boolean transperancyAsWhite) {
//        TGS_FuncMTCUtils.run(() -> {
//            if (image == null) {
//                TGS_FuncMTUUtils.thrw(d.className(), "addImageToPageLeft", "image == null");
//                return;
//            }
//            if (textWrap) {
//                image.setAlignment(/*Image.ALIGN_LEFT | */Image.TEXTWRAP);//allign left is 0 already
//            } else {
//                image.setAlignment(Image.ALIGN_LEFT);
//            }
//            addImage2Document(document, image);
//        });
//    }
//
//    public void addImageToPageRight(java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        addImageToPageRight(createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK), textWrap, transperancyAsWhite);
//    }
//
//    public void addImageToPageRight(Image image, boolean textWrap, boolean transperancyAsWhite) {
//        TGS_FuncMTCUtils.run(() -> {
//            if (image == null) {
//                TGS_FuncMTUUtils.thrw(d.className(), "addImageToPageRight", "image == null");
//                return;
//            }
//            if (textWrap) {
//                image.setAlignment(Image.ALIGN_RIGHT | Image.TEXTWRAP);
//            } else {
//                image.setAlignment(Image.ALIGN_RIGHT);
//            }
//            addImage2Document(document, image);
//        });
//    }
//
//    public void addImageToPageCenter(java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        addImageToPageCenter(createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK), textWrap, transperancyAsWhite);
//    }
//
//    public void addImageToPageCenter(Image image, boolean textWrap, boolean transperancyAsWhite) {
//        TGS_FuncMTCUtils.run(() -> {
//            if (image == null) {
//                TGS_FuncMTUUtils.thrw(d.className(), "addImageToPageCenter", "image == null");
//                return;
//            }
//            if (textWrap) {
//                image.setAlignment(Image.ALIGN_CENTER | Image.TEXTWRAP);
//            } else {
//                image.setAlignment(Image.ALIGN_CENTER);
//            }
//            addImage2Document(document, image);
//        });
//    }
//
//    public void addImageToCellLeft(PdfPCell cell, java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        if (image == null) {
//            d.ce("addImageToCellLeft.ERROR: TKPDFDocument.addImageToCellLeft.imageAWT == null");
//            return;
//        }
//        var i = createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK);
//        if (textWrap) {
//            i.setAlignment(/*Image.ALIGN_LEFT | */Image.TEXTWRAP);//allign left is 0 already
//        } else {
//            i.setAlignment(Image.ALIGN_LEFT);
//        }
//        addImage2Cell(cell, i);
//    }
//
//    public void addImageToCellRight(PdfPCell cell, java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        if (image == null) {
//            d.ce("addImageToCellRight.ERROR: TKPDFDocument.addImageToCellRight.imageAWT == null");
//            return;
//        }
//        var i = createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK);
//        if (textWrap) {
//            i.setAlignment(Image.ALIGN_RIGHT | Image.TEXTWRAP);
//        } else {
//            i.setAlignment(Image.ALIGN_RIGHT);
//        }
//        addImage2Cell(cell, i);
//    }
//
//    public void addImageToCellCenter(PdfPCell cell, java.awt.Image image, boolean textWrap, boolean transperancyAsWhite) {
//        if (image == null) {
//            d.ce("addImageToCellCenter.ERROR: TKPDFDocument.addImageToCellCenter.imageAWT == null");
//            return;
//        }
//        var i = createImage(image, transperancyAsWhite ? Color.WHITE : Color.BLACK);
//        if (textWrap) {
//            i.setAlignment(Image.ALIGN_CENTER | Image.TEXTWRAP);
//        } else {
//            i.setAlignment(Image.ALIGN_CENTER);
//        }
//        addImage2Cell(cell, i);
//    }
//
//    private void addChunkToParagraph(Chunk c, Paragraph p) {
//        p.add(c);
//    }
//
//    public void addTextToParagraph(CharSequence text, Paragraph p) {
//        addChunkToParagraph(createChunkText(text), p);
//    }
//
//    public void addTextToParagraph(CharSequence text, Paragraph p, Font font) {
//        addChunkToParagraph(createChunkText(text, font), p);
//    }
//
//    public void addLineSeperatorParagraph(Paragraph p) {
//        addChunkToParagraph(Chunk.NEWLINE, p);
//    }
//
//    public void addNewLineToParagraph(Paragraph p) {
//        p.add(createChunkNewLine());
//    }
//
//    public void addParagraphToPage(Paragraph p) {
//        TGS_FuncMTCUtils.run(() -> document.add(p));
//    }
//
//    public void addTableToPage(PdfPTable table) {
//        TGS_FuncMTCUtils.run(() -> document.add(table));
//    }
//
//    public void addCellToTable(PdfPTable table, PdfPCell cell, int rotation_0_90_180_270) {
//        cell.setRotation(rotation_0_90_180_270);
//        table.addCell(cell);
//    }
//
    final private record FontBufferItem(Path path, int height, boolean bold, boolean italic, Color fontColor, float fontSizeCorrectionForFontFile, PDFont pdfFont) {

    }
//    final private static TS_ThreadSyncLst<FontBufferItem> fontBuffer = TS_ThreadSyncLst.ofSlowWrite();
//    public static Font getFontFrom(int height, boolean bold, boolean italic, Color fontColor,
//            Path path, float fontSizeCorrectionForFontFile) {
//        var style = TGS_FuncEffectivelyFinal.ofInt().coronateAs(__ -> {
//            if (bold && italic) {
//                return Font.BOLDITALIC;
//            }
//            if (bold) {
//                return Font.BOLD;
//            }
//            if (italic) {
//                return Font.ITALIC;
//            }
//            return Font.NORMAL;
//        });
////        var fontAlreadyExists = fontBuffer.stream()
////                .filter(t -> t.path.equals(path))
////                .filter(t -> t.height == height)
////                .filter(t -> t.bold == bold)
////                .filter(t -> t.italic == italic)
////                .filter(t -> t.fontColor.equals(fontColor))
////                .filter(t -> t.fontSizeCorrectionForFontFile == fontSizeCorrectionForFontFile)
////                .map(t -> t.pdfFont)
////                .findAny().orElse(null);
////        if (fontAlreadyExists != null) {
////            return fontAlreadyExists;
////        }
//        if (!TS_FileUtils.isExistFile(path)) {
//            d.ce("getFontFrom", "UTF8 font bold not find!", path);
//            return getFontInternal(height, bold, italic, fontColor);
//        }
//        var newPdfFont = new Font(
//                TGS_FuncMTCUtils.call(() -> {
//                    return BaseFont.createFont(
//                            path.toAbsolutePath().normalize().toString(),
//                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED
//                    );
//                }),
//                height * fontSizeCorrectionForFontFile, style, fontColor
//        );
////        fontBuffer.add(new FontBufferItem(path, height, bold, italic, fontColor, fontSizeCorrectionForFontFile, newPdfFont));
//        return newPdfFont;
//    }
//
//    public static Font getFontInternal(int fontSize, boolean bold, boolean italic, Color fontColor) {
//        var fontStyle = TGS_FuncEffectivelyFinal.ofInt().coronateAs(__ -> {
//            if (bold && italic) {
//                return Font.BOLDITALIC;
//            }
//            if (bold) {
//                return Font.BOLD;
//            }
//            if (italic) {
//                return Font.ITALIC;
//            }
//            return Font.NORMAL;
//        });
//        return FontFactory.getFont(BaseFont.TIMES_ROMAN, TGS_CharSet.cmn().getCharset_IBM_TURKISH(), true, fontSize, fontStyle, fontColor);
//        //return FontFactory.getFont("arialuni", "Cp857", true, fontSize, (bold & italic ? Font.BOLDITALIC : (bold && !italic ? Font.BOLD : ((!bold && italic ? Font.ITALIC : Font.NORMAL)))), fontColor);
//        //return FontFactory.getFont("arialuni", "Identity-H", fontSize, (bold & italic ? Font.BOLDITALIC : (bold && !italic ? Font.BOLD : ((!bold && italic ? Font.ITALIC : Font.NORMAL)))), fontColor);
//        //return FontFactory.getFont(FontFactory.HELVETICA, TK_GWTCharacterSets.DEFAULT, fontSize, (bold & italic ? Font.BOLDITALIC : (bold && !italic ? Font.BOLD : ((!bold && italic ? Font.ITALIC : Font.NORMAL)))), fontColor);
//    }
//
//    public void setFont(Font font, Paragraph p) {
//        p.setFont(font);
//    }
//
//    public void setFont(Font font, Chunk chunk) {
//        chunk.setFont(font);
//    }
//
//    public PdfPCell createCell(Paragraph paragraph) {
//        return new PdfPCell(paragraph);
//    }
//
//    public void setCellColSpan(PdfPCell cell, int colSpan) {
//        cell.setColspan(colSpan);
//    }
//
//    public PdfPTable createTable(int colCount) {
//        return new PdfPTable(colCount);
//    }
//
//    public PdfPTable createTable(int[] relColWidths) {
//        var frelColWidths = new float[relColWidths.length];
//        IntStream.range(0, relColWidths.length).parallel().forEach(i -> frelColWidths[i] = relColWidths[i]);
//        return new PdfPTable(frelColWidths);
//    }
//
//    public void setCellPadding(PdfPCell cell, float padding) {
//        cell.setPadding(padding);//10.0f
//    }
//
//    public void setCellBackground(PdfPCell cell, Color color) {
//        cell.setBackgroundColor(color);
//    }
//
//    public void setCellAlignLeft(PdfPCell cell) {
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//    }
//
//    public void setCellAlignRight(PdfPCell cell) {
//        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//    }
//
//    public void setCellAlignCenter(PdfPCell cell) {
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//    }

    public void close() {
        TGS_FuncMTCUtils.run(() -> {
            if (page != null) {
                page.stream.close();
            }
        }, e -> d.ct("close.stream", e));
        TGS_FuncMTCUtils.run(() -> {
            if (document != null) {
                document.close();
            }
        }, e -> d.ct("close.document", e));
        TGS_FuncMTCUtils.run(() -> {
            TS_FilePdfBox3UtilsSave.save(document, file, COMPRESS_ON_SAVE);
        }, e -> d.ct("close.save", e));
    }
}
