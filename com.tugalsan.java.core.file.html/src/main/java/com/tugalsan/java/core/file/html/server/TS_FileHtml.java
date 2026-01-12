package com.tugalsan.java.core.file.html.server;

import module java.desktop;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.file.common;
import module com.tugalsan.java.core.file.html;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.url;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.Objects;

public class TS_FileHtml extends TS_FileCommonAbstract {

    final private static TS_Log d = TS_Log.of(TS_FileHtml.class);

    @Override
    public String getSuperClassName() {
        return d.className();
    }

    private static int FONT_HEIGHT_OFFSET() {
        return 12;
    }

    public TGS_FileHtml webWriter;

    public TS_FileCommonConfig fileCommonConfig;
    private final int fontHeightScalePercent;
    private final int widthScalePercent;
    private final TGS_FuncMTU_OutTyped_In2<TGS_Url, TS_FileHtml, String> convertLocalLocationToRemote;

    public boolean base64() {
        return isBase64;
    }
    private final boolean isBase64;

    private TS_FileHtml(boolean enabled, Path localFile, TGS_Url remoteFile, boolean isBase64, int widthScalePercent, int fontHeightScalePercent, TGS_FuncMTU_OutTyped_In2<TGS_Url, TS_FileHtml, String> convertLocalLocationToRemote) {
        super(enabled, localFile, remoteFile);
        this.isBase64 = isBase64;
        this.fontHeightScalePercent = fontHeightScalePercent;
        this.widthScalePercent = widthScalePercent;
        this.convertLocalLocationToRemote = convertLocalLocationToRemote;
    }
    private final String customCssForBlackText = TGS_FileHtmlText.getDefaultCustomCssForBlackText();

    public static void use(boolean enabled, TS_FileCommonConfig fileCommonConfig, Path localFile, TGS_Url remoteFile, boolean isBase64, int widthScalePercent, int fontHeightScalePercent, TGS_FuncMTU_OutTyped_In2<TGS_Url, TS_FileHtml, String> convertLocalLocationToRemote, TGS_FuncMTU_In1<TS_FileHtml> web) {
        var instance = new TS_FileHtml(enabled, localFile, remoteFile, isBase64, widthScalePercent, fontHeightScalePercent, convertLocalLocationToRemote);
        try {
            instance.use_init(fileCommonConfig);
            web.run(instance);
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
        webWriter = TGS_FileHtml.of(
                null, null,
                fileCommonConfig.funcName,
                fileCommonConfig.favIconPng,
                fileCommonConfig.bootloaderJs,
                pageSizeAX, landscape
        );
    }

    @Override
    public boolean saveFile(String errorSource) {
        if (isClosed()) {
            return true;
        }
        setClosed();
        d.ci("saveFile.Web->");
        if (webWriter == null) {
            d.ci("Web File is null");
        } else {
            var span = new TGS_FileHtmlSpan(null, "", TGS_FileHtmlUtils.pageEnd(), "");
            span.pureCode = true;
            webWriter.getChilderen().add(span);
            TS_FileHtmlUtils.write2File(webWriter, getLocalFileName());
            if (TS_FileUtils.isExistFile(getLocalFileName())) {
                d.ci("saveFile.FIX: Web File save", getLocalFileName(), "successfull");
            } else {
                d.ce("saveFile.FIX: Web File save", getLocalFileName(), "failed");
            }
        }
        return errorSource == null;
    }

    @Override
    public boolean createNewPage(int pageSizeAX, boolean landscape, Integer marginLeft, Integer marginRight, Integer marginTop, Integer marginBottom) {
        if (isClosed()) {
            return true;
        }
        this.landscape = landscape;
        if (pageSizeAX < 0) {
            this.pageSizeAX = null;
        } else if (pageSizeAX > pageSizeMaxWidth.length) {
            this.pageSizeAX = pageSizeMaxWidth.length - 1;
        } else {
            this.pageSizeAX = pageSizeAX;
        }
        webWriter.pageSizeAX = pageSizeAX;
        webWriter.landscape = landscape;
        if (table != null) {
            d.ce("createNewPage.ERROR: MIFWeb.createNewPage -> why table exists!");
            return false;
        }
        var paddingFix = 0;
        if (isFirstPageTriggered) {
            var span = new TGS_FileHtmlSpan(null, "", TGS_FileHtmlUtils.pageEnd(), "");
            span.pureCode = true;
            webWriter.getChilderen().add(span);
            span = new TGS_FileHtmlSpan(null, "", TGS_FileHtmlUtils.pageStart(paddingFix), "");
            span.pureCode = true;
            webWriter.getChilderen().add(span);
        } else {
            isFirstPageTriggered = true;
            var span = new TGS_FileHtmlSpan(null, "", TGS_FileHtmlUtils.pageStart(paddingFix), "");
            span.pureCode = true;
            webWriter.getChilderen().add(span);
        }
        return true;
    }
    private boolean isFirstPageTriggered = false;
    private Integer pageSizeAX;
    private boolean landscape;

    private boolean addImageWeb(String imageLoc, int width, int heights, int rotationInDegrees_0_90_180_270, long imageCounter) {
        if (isClosed()) {
            return true;
        }
        return TGS_FuncMTCUtils.call(() -> {
            if (isBase64) {
                d.ci("addImageWeb", "imageLoc", imageLoc);
            }
            var mWidth = width;
            var mHeight = heights;
            if (rotationInDegrees_0_90_180_270 == 90 || rotationInDegrees_0_90_180_270 == 270) {
//                var tmp = mWidth;
                mWidth = mHeight;
//                mHeight = tmp;
            }
//            d.ci("addImageWeb", "w", mWidth, "h", mHeight, "r", rotationInDegrees_0_90_180_270);
            var mImageLoc = convertLocalLocationToRemote.call(this, imageLoc);
//            mImageLoc = TS_LibFileTmcrFileConverter.convertLocalLocationToRemote(
//                    fileCommonConfig.username,
//                    fileCommonConfig.url,
//                    mImageLoc
//            );
            if (mImageLoc == null) {
                d.ce("addImageWeb", "Cannot convertLocalLocationToRemote", mImageLoc);
                return false;
            }

            var sw = mWidth + "px";
//            var sh = height + "px";
            if (pageSizeAX != null) {
                var maxWidth = pageSizeMaxWidth[pageSizeAX];
                var maxPermissableWidth = (int) (Math.round(maxWidth * widthScalePercent / 100f));
                var selectedWidth = mWidth > maxPermissableWidth ? maxPermissableWidth : mWidth;
                sw = selectedWidth + "px";
//                sh = ((int) (selectedWidth * height / width) + "px");
            }
            var image = isBase64
                    ? new TS_FileHtmlImage64(TS_FileHtmlImage64.class.getSimpleName() + "_" + imageCounter, mImageLoc.toString().startsWith("http") ? mImageLoc.toString() : imageLoc, sw, "auto", String.valueOf(rotationInDegrees_0_90_180_270))
                    : new TS_FileHtmlImage(TS_FileHtmlImage.class.getSimpleName() + "_" + imageCounter, mImageLoc.toString(), sw, "auto", String.valueOf(rotationInDegrees_0_90_180_270));
            if (tableRowCell == null) {
                webWriter.getChilderen().add(image);
            } else {
                tableRowCell.getChilderen().add(image);
            }
            return true;
        }, e -> {
            d.ce("addImageWeb", e);
            return true;
        });
    }

    @Override
    public boolean addImage(BufferedImage pstImage, Path pstImageLoc, boolean textWrap, int left0_center1_right2, long imageCounter) {
        if (isClosed()) {
            return true;
        }
        boolean result;
        d.ci("addImage", "init", "imageLoc", pstImageLoc);
        result = addImageWeb(pstImageLoc.toAbsolutePath().toString(), pstImage.getWidth(), pstImage.getHeight(), 0, imageCounter);
        d.ci("addImage", "fin");
        return result;
    }

    @Override
    public boolean beginTable(int[] table_relColSizes) {
        if (isClosed()) {
            return true;
        }
        this.table_relColSizes = table_relColSizes;
        if (table != null) {
            d.ce("beginTable.ERROR: MIFWeb.beginTable -> table already exists");
            return false;
        }
        if (tableRow != null) {
            d.ce("beginTable.ERROR: MIFWeb.beginTable -> tableRow already exists");
            return false;
        }

        //ADD TABLE
        if (pageSizeAX == null || pageSizeAX >= pageSizeMaxWidth.length) {
            d.ce("beginTable", "pageSizeAX.fixing...", pageSizeAX);
            pageSizeAX = pageSizeMaxWidth.length - 1;
            d.ce("beginTable", "pageSizeAX.fixed", pageSizeAX);
        }
        var pageSizeFix = "";//"max-width:" + (pageSizeAX == null ? "null" : ((int) (Math.round(pageSizeMaxWidth[pageSizeAX] * widthScalePercent / 100f)) + "px")) + ";";
        table = new TGS_FileHtmlTable("TK_POJOHTMLTable_" + TGS_FileHtmlTable.counter, pageSizeFix + "border-spacing:0;border-collapse:collapse;border:1px solid black; width:100%;");
        webWriter.getChilderen().add(table);

        //ADD ROW
        currentRowIndex = 0;
        tableRow = new TGS_FileHtmlTableRow("TK_POJOHTMLTableRow_" + TGS_FileHtmlTableRow.counter);
        var escape = new TS_FileHtmlEscape();
        Arrays.stream(table_relColSizes).forEachOrdered(rcs -> {
            tableRow.getChilderen().add(new TGS_FileHtmlTableRowCellVacant(tableRow.IsHeader(), escape));
        });
        table.getChilderen().add(tableRow);
        return true;
    }
    private int currentRowIndex = 0;
    private TGS_FileHtmlTable table = null;
    private int[] table_relColSizes = null;
    private TGS_FileHtmlTableRow tableRow = null;
    final private int[] pageSizeMaxWidth = new int[]{1330, 1330, 1330, 1330, 1330};//TODO PAGE SIZE CALCULATIONS is a mess

    @Override
    public boolean endTable() {
        if (isClosed()) {
            return true;
        }
        if (table == null) {
            d.ce("endTable.ERROR: MIFWeb.endTable -> table not exists");
            return false;
        }
        if (tableRow == null) {
            d.ce("endTable.ERROR: MIFWeb.endTable -> tableRow not exists");
            return false;
        }
        table = null;
        tableRow = null;
        currentRowIndex = 0;
        return true;
    }

    @Override
    public boolean beginTableCell(int rowSpan, int colSpan, Integer cellHeight) {
        if (isClosed()) {
            return true;
        }
        if (tableRowCell != null) {
            d.ce("beginTableCell.ERROR: MIFWeb.beginTableCell -> why tableRowCell exists!");
            return false;
        }
        if (tableRow == null) {
            d.ce("beginTableCell.ERROR: MIFWeb.beginTableCell -> why tableRow not exists!");
            return false;
        }
        if (table == null) {
            d.ce("beginTableCell.ERROR: MIFWeb.beginTableCell -> why table not exists!");
            return false;
        }

        //CALCULATE rowCellColSpanOffset
        var rowCellColSpanOffset = calcultaeRowCellColSpanOffset();
        if (rowCellColSpanOffset == -1) {
            return false;
        }
        while (checkMaxColumnSize(rowCellColSpanOffset)) {
            rowCellColSpanOffset = calcultaeRowCellColSpanOffset();
            if (rowCellColSpanOffset == -1) {
                return false;
            }
        }

        //SET CELL
        {
            if (table_relColSizes.length <= rowCellColSpanOffset) {
                d.ce("beginTableCell.ERROR: MIFWeb.beginTableCell -> FAILED(table_relColSizes.length <= cellColCounter)");
                return false;
            }
            var escape = new TS_FileHtmlEscape();
            tableRowCell = new TGS_FileHtmlTableRowCell(tableRow.IsHeader(), escape, "TK_POJOHTMLTableRowCell_" + TGS_FileHtmlTableRowCell.counter, String.valueOf(rowSpan), String.valueOf(colSpan), "");
            tableRow.getChilderen().set(rowCellColSpanOffset, tableRowCell);
        }

        var sumWidth = 0;//SET STYLE
        for (var c = 0; c < colSpan; c++) {
            if (rowCellColSpanOffset + c <= table_relColSizes.length - 1) {
                sumWidth += table_relColSizes[rowCellColSpanOffset + c];
            } else {
                d.ci("beginTableCell.ERROR: MIFWeb.beginTableCell -> sumWidth WHY CANOT ADD COLSPANWIDTH: rowCellColSpanOffset + c <= table_relColSizes.length - 1", "rowCellColSpanOffset", rowCellColSpanOffset, "table_relColSizes.length", table_relColSizes.length);
            }
        }
        var pageSizeFix = "";//max-width:" + (pageSizeAX == null ? "null" : ((int) (Math.round(pageSizeMaxWidth[pageSizeAX] * widthScalePercent / 100f)) + "px")) + ";";
        var styleWidth = TGS_StringUtils.cmn().concat("width:", String.valueOf(sumWidth), "%;");
        var styleHeight = cellHeight == null ? "" : TGS_StringUtils.cmn().concat("height:", String.valueOf(cellHeight), "px;");
        tableRowCell.setStyle_Properties2(TGS_StringUtils.cmn().concat(pageSizeFix, "vertical-align:top;border:1px solid black;", styleWidth, styleHeight));

        var fRowCellColSpanOffset = rowCellColSpanOffset;
        var escape = new TS_FileHtmlEscape();
        IntStream.range(1, colSpan).forEach(ci -> {//ADD COLSPAN FILL TODO
            if (fRowCellColSpanOffset + ci <= table_relColSizes.length - 1) {
                tableRow.getChilderen().set(fRowCellColSpanOffset + ci,
                        new TGS_FileHtmlTableRowCellOccupied(tableRow.IsHeader(), escape)
                );
            } else {
                d.ci("beginTableCell.ERROR: MIFWeb.beginTableCell -> eColSpan WHY CANOT ADD COLSPANFULL: rowCellColSpanOffset + c <= table_relColSizes.length - 1", "rowCellColSpanOffset", fRowCellColSpanOffset, "table_relColSizes.length", table_relColSizes.length);
            }
        });
        //ADD ROWSPAN FILL
        IntStream.range(1, rowSpan).forEachOrdered(ri -> {
            final TGS_FileHtmlTableRow nextRow;
            if (table.getChilderen().size() <= currentRowIndex + ri) {
                nextRow = new TGS_FileHtmlTableRow("TK_POJOHTMLTableRow_" + TGS_FileHtmlTableRow.counter);
                Arrays.stream(table_relColSizes).forEachOrdered(rcs -> {
                    nextRow.getChilderen().add(new TGS_FileHtmlTableRowCellVacant(nextRow.IsHeader(), escape));
                });
                table.getChilderen().add(nextRow);
            } else {
                nextRow = (TGS_FileHtmlTableRow) table.getChilderen().get(currentRowIndex + ri);
            }
            IntStream.range(0, colSpan).forEach(ci -> {
                nextRow.getChilderen().set(fRowCellColSpanOffset + ci,
                        new TGS_FileHtmlTableRowCellOccupied(nextRow.IsHeader(), escape)
                );
            });
        });
        return true;
    }
    private TGS_FileHtmlTableRowCell tableRowCell = null;

    private int calcultaeRowCellColSpanOffset() {
        var rowCellColSpanOffset = 0;
        if (isClosed()) {
            return rowCellColSpanOffset;
        }
        for (var eRowCell : tableRow.getChilderen()) {//INC
            if (eRowCell instanceof TGS_FileHtmlTableRowCell) {
                if (eRowCell instanceof TGS_FileHtmlTableRowCellVacant) {
                    break;
                } else if (eRowCell instanceof TGS_FileHtmlTableRowCellOccupied) {
                    rowCellColSpanOffset += 1; //from rowspan
                } else {
                    rowCellColSpanOffset += 1; //full already adds 1
                }
            } else {
                d.ce("calcultaeRowCellColSpanOffset.ERROR: MIFWeb.beginTableCell -> e NOT instanceof TK_POJOHTMLTableRowCell: " + eRowCell);
                return -1;
            }
        }
        d.ci("calcultaeRowCellColSpanOffset.MIFWeb.calcultaeRowCellColSpanOffset", "rowCellColSpanMax: " + table_relColSizes.length, "rowCellColSpanOffset", rowCellColSpanOffset);
        return rowCellColSpanOffset;
    }

    private boolean checkMaxColumnSize(int rowCellColSpanOffset) {
        if (isClosed()) {
            return true;
        }
        var escape = new TS_FileHtmlEscape();
        var rowAdded = false;
        if (table_relColSizes.length <= rowCellColSpanOffset) {
            rowAdded = true;
            if (table.getChilderen().size() - 1 == currentRowIndex) {
                currentRowIndex++;
                tableRow = new TGS_FileHtmlTableRow("TK_POJOHTMLTableRow_" + TGS_FileHtmlTableRow.counter);
                Arrays.stream(table_relColSizes).forEachOrdered(rcs -> {
                    tableRow.getChilderen().add(new TGS_FileHtmlTableRowCellVacant(tableRow.IsHeader(), escape));
                });
                table.getChilderen().add(tableRow);
                d.ci("checkMaxColumnSize.MIFWeb.beginTableCell.checkMaxColumnSize.DECISION: NEWROW_ADDED");
            } else {
                currentRowIndex++;
                tableRow = (TGS_FileHtmlTableRow) table.getChilderen().get(currentRowIndex);
                d.ci("checkMaxColumnSize.MIFWeb.beginTableCell.checkMaxColumnSize.DECISION: ROW_ALREADY_EXISTS");
            }
        } else {
            d.ci("checkMaxColumnSize.MIFWeb.beginTableCell.checkMaxColumnSize.DECISION: PASS");
        }
        return rowAdded;
    }

    @Override
    public boolean endTableCell(int rotationInDegrees_0_90_180_270) {
        if (isClosed()) {
            return true;
        }
        if (tableRow == null) {
            d.ce("endTableCell.ERROR: MIFWeb.endTableCell -> why tableRow not exists!");
            return false;
        }
        if (tableRowCell == null) {
            d.ce("endTableCell.ERROR: MIFWeb.endTableCell -> why tableRowCell not exists!");
            return false;
        }
        tableRowCell = null;
        return true;
    }

    @Override
    public boolean beginText(int allign_Left0_center1_right2_just3) {
        if (isClosed()) {
            return true;
        }
        if (parag != null) {
            d.ce("beginText.ERROR: MIFWeb.beginText -> why parag exists!");
            return false;
        }
        String allignText;
        allignText = switch (allign_Left0_center1_right2_just3) {
            case 1 ->
                "center";
            case 2 ->
                "right";
            default ->
                "left";
        };
        var escape = new TS_FileHtmlEscape();
        parag = new TGS_FileHtmlParagraph(escape, "TK_POJOHTMLParagraph_" + TGS_FileHtmlParagraph.counter, "padding:0px; margin:0px;text-align:" + allignText + ";");
        return true;
    }
    private TGS_FileHtmlParagraph parag = null;

    @Override
    public boolean endText() {
        if (isClosed()) {
            return true;
        }
        if (parag == null) {
            d.ce("endText.ERROR: MIFWeb.endText -> why not exists!");
            return false;
        }
        if (tableRowCell == null) {
            webWriter.getChilderen().add(parag);
        } else {
            tableRowCell.getChilderen().add(parag);
        }
        parag = null;
        return true;
    }

    @Override
    public boolean addText(String text) {
        if (isClosed()) {
            return true;
        }
        if (parag == null) {
            d.ce("addText.ERROR: MIFWeb.addText -> why parag not exists!");
            return false;
        }
        var lines = TGS_StringUtils.jre().toList(text, "\n");
        var escape = new TS_FileHtmlEscape();
        IntStream.range(0, lines.size()).forEachOrdered(i -> {
            var line = lines.get(i);
            if (!line.isEmpty()) {
                if (!TGS_StringDouble.may(text)) {
                    var span = new TGS_FileHtmlSpan(escape, "TK_POJOHTMLSpan_" + TGS_FileHtmlSpan.counter, line, getFont());
                    parag.getChilderen().add(span);
                } else {
                    var tags = TGS_StringUtils.jre().toList_spc(line);
                    IntStream.range(0, tags.size()).forEachOrdered(j -> {
                        var tag = tags.get(j);
                        var dbl = TGS_StringDouble.of(text);
                        if (dbl.isExcuse()) {
                            var span = new TGS_FileHtmlSpan(escape, "TK_POJOHTMLSpan_" + TGS_FileHtmlSpan.counter, tag, getFont());
                            parag.getChilderen().add(span);
                        } else {
                            var htmlText = TGS_StringUtils.cmn().concat(String.valueOf(dbl.value().left), "<sub>", String.valueOf(dbl.value().dim()), String.valueOf(dbl.value().right), "</sub>");
                            var span = new TGS_FileHtmlSpan(escape, "TK_POJOHTMLSpan_" + TGS_FileHtmlSpan.counter, htmlText, getFont());
                            span.pureCode = true;
                            parag.getChilderen().add(span);
                        }
                        if (tags.size() - 1 != j) {
                            var span = new TGS_FileHtmlSpan(escape, "TK_POJOHTMLSpan_spc" + TGS_FileHtmlSpan.counter, " ", getFont());
                            parag.getChilderen().add(span);
                        }
                    });
                }
            }
            if (i != lines.size() - 1 || text.endsWith("\n")) {
                addLineBreak();
            }
        });
        return true;
    }

    @Override
    public boolean addLineBreak() {
        if (isClosed()) {
            return true;
        }
        if (parag == null) {
            d.ce("addLineBreak.ERROR: MIFWeb.addLineBreak -> why not exists!");
            return false;
        }
        parag.getChilderen().add(new TGS_FileHtmlBR());
        return true;
    }

    @Override
    public boolean setFontStyle() {
        if (isClosed()) {
            return true;
        }
        styleIterator = null;
        getFont();
        return true;
    }

    @Override
    public boolean setFontHeight() {
        if (isClosed()) {
            return true;
        }
        styleIterator = null;
        getFont();
        return true;
    }

    @Override
    public boolean setFontColor() {
        if (isClosed()) {
            return true;
        }
        styleIterator = null;
        getFont();
        return true;
    }

    private String getFont() {
        if (styleIterator == null) {
            var calculatedfontHeight = (int) (Math.round((fileCommonConfig.fontHeight + FONT_HEIGHT_OFFSET()) * fontHeightScalePercent / 100f));
            while (calculatedfontHeight < 1) {
                calculatedfontHeight++;
            }
            styleIterator = "font-family:fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;color:" + getFontColor() + ";font-size:" + calculatedfontHeight + "px;font-style:" + (fileCommonConfig.fontItalic ? "italic" : "normal") + ";font-weight:" + (fileCommonConfig.fontBold ? "bold" : "normal") + ";";
        }
        return styleIterator;
    }
    private String styleIterator = null;

    private String getFontColor() {
        if (isClosed()) {
            return "#000000";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_WHITE())) {
            return "#FFFFFF";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_BLUE())) {
            return "#0000FF";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_CYAN())) {
            return "#00FFFF";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_DARK_GRAY())) {
            return "#505050";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_GRAY())) {
            return "#808080";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_GREEN())) {
            return "#008000";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_LIGHT_GRAY())) {
            return "#D3D3D3";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_MAGENTA())) {
            return "#FF00FF";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_ORANGE())) {
            return "#FFA500";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_PINK())) {
            return "#FFC0CB";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_RED())) {
            return "#FF0000";
        }
        if (Objects.equals(fileCommonConfig.fontColor, TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_YELLOW())) {
            return "#FFFF00";
        }
        return customCssForBlackText == null ? "#000000" : customCssForBlackText;
    }
}
