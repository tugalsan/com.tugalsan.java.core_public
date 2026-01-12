package com.tugalsan.java.core.file.tug.server;

//import module com.tugalsan.java.core.file.common;
//import module com.tugalsan.java.core.file.txt;
//import module com.tugalsan.java.core.file.html;
//import module com.tugalsan.java.core.log;
//import module com.tugalsan.java.core.file;
//import module com.tugalsan.java.core.list;
//import module com.tugalsan.java.core.url;
//import module com.tugalsan.lib.domain;
//import module com.tugalsan.lib.report;
//import module java.desktop;
//import java.nio.file.*;
//import java.util.*;

//TODO: This whole com.tugalsan.java.core.file.tug project is just in branstorming stage
public class TS_LibRepFileDoc { //extends TS_FileCommonInterface {

//    final private static TS_Log d = TS_Log.of(TS_LibRepFileDoc.class);
//
//    private final TS_FileCommonBall macroGlobals;
//    public List<TS_LibRepFileDocPage> pages;
//
//    public boolean isTableMode = false;
//
//    public TS_LibRepFileDocPage getCurrentPage() {
//        if (pages.isEmpty()) {
//            pages.add(new TS_LibRepFileDocPage());
//        }
//        return pages.get(pages.size() - 1);
//    }
//
//    @Override
//    public String toString() {
//        var domainCard = TS_LibDomainCardUtils.get();
//        var browserTitle = (domainCard == null ? d.className() : domainCard.firmaNameShort) + " > " + macroGlobals.funcName;
//        var sb = new StringBuilder();
//        sb.append(TGS_FileHtmlUtils.beginLines(browserTitle, true, false, 5, 5, null, true, macroGlobals.customDomain));
//        pages.stream().forEachOrdered(page -> sb.append(page));
//        sb.append(TGS_FileHtmlUtils.endLines(true));
//        return sb.toString();
//    }
//
//    public TS_LibRepFileDoc(TS_LibRepParser_Globals macroGlobals, Path localFile, TGS_Url remoteFile) {
//        super(localFile, remoteFile);
//        this.macroGlobals = macroGlobals;
//        init(macroGlobals);
//    }
//
//    @Override
//    public final void init(TS_FileCommonBall macroGlobals) {
//        pages = TGS_ListUtils.of();
//    }
//
//    @Override
//    public boolean saveFile(String errorSource) {
//        close();
//        if (TS_FileUtils.isExistFile(getLocalFileName())) {
//            d.ci("saveFile.FIX: DOC File save", getLocalFileName(), "successfull");
//        } else {
//            d.ce("saveFile.FIX: DOC File save", getLocalFileName(), "failed");
//        }
//        return errorSource == null;
//    }
//
//    @Override
//    public boolean setFontStyle() {
//        return true;
//    }
//
//    @Override
//    public boolean setFontHeight() {
//        return true;
//    }
//
//    @Override
//    public boolean setFontColor() {
//        return true;
//    }
//
//    @Override
//    public boolean createNewPage(int pageSizeAX, boolean landscape, Integer marginLeft, Integer marginRight, Integer marginTop, Integer marginBottom) {
//        pages.add(new TS_LibRepFileDocPage(pageSizeAX, landscape, marginLeft, marginRight, marginTop, marginBottom));
//        return true;
//    }
//
//    @Override
//    public boolean beginTable(int[] relColSizes) {
//        getCurrentPage().tables.add(new TS_LibRepFileDocTable(relColSizes));
//        isTableMode = true;
//        return true;
//    }
//
//    @Override
//    public boolean endTable() {
//        isTableMode = false;
//        return true;
//    }
//
//    @Override
//    public boolean beginTableCell(int rowSpan, int colSpan, Integer cellHeight) {
//        getCurrentPage().getCurrentTable().getCurrentRow().cells.add(new TS_LibRepFileDocCell(rowSpan, colSpan, cellHeight));
//        return true;
//    }
//
//    @Override
//    public boolean endTableCell(int rotationInDegrees_0_90_180_270) {
//        return true;
//    }
//
//    @Override
//    public boolean addImage(BufferedImage pstImage, Path pstImageLoc, boolean textWrap, int left0_center1_right2, long imageCounter) {
//        d.ci("addImage", "init", pstImageLoc);
//        var imageLoc = TS_LibRepFileConverter.convertLocalLocationToRemote(macroGlobals, pstImageLoc.toAbsolutePath().toString());
//        if (imageLoc == null) {
//            d.ce("addImageWeb", "Cannot convertLocalLocationToRemote", imageLoc);
//            return false;
//        }
//        getCurrentPage().getCurrentTable().getCurrentRow().getCurrentCell().getCurrentParag().data.add(new TS_LibRepFileDocData(false, imageLoc));
//        d.ci("addImage", "fin");
//        return true;
//    }
//
//    @Override
//    public boolean beginText(int allign_Left0_center1_right2_just3) {
//        if (!isTableMode) {
//            getCurrentPage().tables.add(new TS_LibRepFileDocTable());
//        }
//        getCurrentPage().getCurrentTable().getCurrentRow().getCurrentCell().parags.add(new TS_LibRepFileDocParag(allign_Left0_center1_right2_just3));
//        return true;
//    }
//
//    @Override
//    public boolean endText() {
//        return true;
//    }
//
//    @Override
//    public boolean addText(String text) {
//        getCurrentPage().getCurrentTable().getCurrentRow().getCurrentCell().getCurrentParag().data.add(new TS_LibRepFileDocData(text));
//        return true;
//    }
//
//    @Override
//    public boolean addLineBreak() {
//        getCurrentPage().getCurrentTable().getCurrentRow().getCurrentCell().getCurrentParag().data.add(new TS_LibRepFileDocData(TS_LibRepFileDocData.LINEBREAK()));
//        return true;
//    }
//
//    public void close() {
//        if (isClosed) {
//            return;
//        }
//        isClosed = true;
//        TS_FileTxtUtils.toFile(toString(), getLocalFileName(), false);
//    }
//    private boolean isClosed = false;
}
