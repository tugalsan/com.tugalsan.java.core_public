package com.tugalsan.java.core.file.common.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.file.common;
import module com.tugalsan.java.core.font;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.time;
import module com.tugalsan.java.core.url;
import module java.desktop;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class TS_FileCommonConfig {

    public static int QR_WIDTH = 640;
    public static int QR_HEIGHT = 640;
    public static float QR_MAGNIFY = 0.9f;

    final private static TS_Log d = TS_Log.of(false, TS_FileCommonConfig.class);
    final public boolean PARALLEL;
    public boolean copyPageRequested = false;

    final public List<TGS_FontFamily<Path>> fontFamilyPaths;
    final public List<TGS_FontFamily<Font>> fontFamilyFonts;
    public volatile int fontFamilyIdx = 0;

    public String fontColor;
    public float fontHeightK;

    //FILE NAMES
    public String prefferedFileNameLabel = "";

    //AUTOMATION
    public List<String> requestedFileTypes;
    public String fileNameLabel;

    //MACRO BASE
    final public TGS_Time now;
    public String doFind_gotoLabel = null;
    public String macroLine;
    public String macroLineUpperCase;
    public List<String> macroLineTokens;
    public List<String> mapVars;
    public boolean insertPageTriggeredBefore = false;

    //FONT
    public int fontHeight;
    public boolean fontBold, fontItalic, fontUnderlined;
    public boolean enableTableCellBorder = true;

    //IMAGE WEB
    public long imageCounter = 0L;

    //GLOBALS
    public boolean runReport;
    final public List<String> macroLines;
    final public String username;
    final public String tablename;
    final public Long selectedId;
    final public String funcName;
    public Integer cellHeight;
    final public String userDotTablename;

    final public TGS_Url url;
    final public Path dirDat;
    final public Path dirDatTbl;
    final public Path dirDatPub;
    final public Path dirDatUsr;
    final public Path dirDatUsrTmp;

    final public TGS_FileCommonFavIcon favIconPng;

    final public TGS_FuncMTU_OutTyped_In1<TGS_Url, TGS_Url> manipulateInjectCode;
    final public TGS_FuncMTU_OutTyped_In5<List<String>, String, String, Long, String, Boolean> libTableFileList_getFileNames_DataIn;
    final public TGS_FuncMTU_OutTyped_In2<Path, String, String> libTableFileDir_datTblTblnameColname;
    final public TGS_FuncMTU_OutTyped_In2<TGS_Url, String, Boolean> libTableFileGetUtils_urlUsrTmp;
    final public TGS_FuncMTU_OutTyped_In1<String, CharSequence> libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE;
    final public TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_PUBLIC;
    final public TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_USER;

    final public TGS_Url bootloaderJs;
    final public String domainName;

    @Override
    public String toString() {
        return TS_FileCommonConfig.class.getSimpleName() + "{" + "runReport=" + runReport + ", username=" + username + ", tablename=" + tablename + ", selectedId=" + selectedId + ", funcName=" + funcName + ", userDotTablename=" + userDotTablename + ", url=" + url + ", dirDat=" + dirDat + '}';
    }

    public static TS_FileCommonConfig of(boolean PARALLEL,
            List<String> macroLines, String username,
            String tablename, Long selectedId,
            String funcName, String fileNameLabel, TGS_Url url,
            List<String> requestedFileTypes, Path dirDat,
            List<TGS_FontFamily<Path>> fontFamilyPaths,
            TGS_FileCommonFavIcon favIconPng,
            Path dirDatTbl, Path dirDatPub, Path dirDatUsr, Path dirDatUsrTmp,
            TGS_Url bootloaderJs
    ) {
        TGS_FuncMTU_OutTyped_In1<TGS_Url, TGS_Url> manipulateInjectCode = _url -> _url;//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In5<List<String>, String, String, Long, String, Boolean> libTableFileList_getFileNames_DataIn = (a, b, c, d, e) -> TGS_ListUtils.of();//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In2<Path, String, String> libTableFileDir_datTblTblnameColname = (a, b) -> dirDatUsrTmp;//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In2<TGS_Url, String, Boolean> libTableFileGetUtils_urlUsrTmp = (a, b) -> bootloaderJs;//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In1<String, CharSequence> libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE = a -> "";//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_PUBLIC = a -> "";//SKIP LEGACY CODE
        TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_USER = a -> "";//SKIP LEGACY CODE
        return of(PARALLEL,
                macroLines, username,
                tablename, selectedId,
                funcName, fileNameLabel, url,
                requestedFileTypes, dirDat,
                fontFamilyPaths,
                favIconPng,
                manipulateInjectCode,
                dirDatTbl, dirDatPub, dirDatUsr, dirDatUsrTmp,
                libTableFileList_getFileNames_DataIn,
                libTableFileDir_datTblTblnameColname,
                libTableFileGetUtils_urlUsrTmp,
                libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE,
                libFileServletUtils_URL_SERVLET_FETCH_PUBLIC,
                libFileServletUtils_URL_SERVLET_FETCH_USER,
                bootloaderJs
        );
    }

    public static TS_FileCommonConfig of(boolean PARALLEL,
            List<String> macroLines, String username,
            String tablename, Long selectedId,
            String funcName, String fileNameLabel, TGS_Url url,
            List<String> requestedFileTypes, Path dirDat,
            List<TGS_FontFamily<Path>> fontFamilyPaths,
            TGS_FileCommonFavIcon favIconPng,
            TGS_FuncMTU_OutTyped_In1<TGS_Url, TGS_Url> manipulateInjectCode,
            Path dirDatTbl, Path dirDatPub, Path dirDatUsr, Path dirDatUsrTmp,
            TGS_FuncMTU_OutTyped_In5<List<String>, String, String, Long, String, Boolean> libTableFileList_getFileNames_DataIn,
            TGS_FuncMTU_OutTyped_In2<Path, String, String> libTableFileDir_datTblTblnameColname,
            TGS_FuncMTU_OutTyped_In2<TGS_Url, String, Boolean> libTableFileGetUtils_urlUsrTmp,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_PUBLIC,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_USER,
            TGS_Url bootloaderJs
    ) {
        return new TS_FileCommonConfig(PARALLEL,
                macroLines, username,
                tablename, selectedId,
                funcName, fileNameLabel, url,
                requestedFileTypes, dirDat,
                fontFamilyPaths,
                favIconPng,
                manipulateInjectCode,
                dirDatTbl, dirDatPub, dirDatUsr, dirDatUsrTmp,
                libTableFileList_getFileNames_DataIn,
                libTableFileDir_datTblTblnameColname,
                libTableFileGetUtils_urlUsrTmp,
                libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE,
                libFileServletUtils_URL_SERVLET_FETCH_PUBLIC,
                libFileServletUtils_URL_SERVLET_FETCH_USER,
                bootloaderJs
        );
    }

    private TS_FileCommonConfig(boolean PARALLEL,
            List<String> macroLines, String username,
            String tablename, Long selectedId,
            String funcName, String fileNameLabel, TGS_Url url,
            List<String> requestedFileTypes, Path dirDat,
            List<TGS_FontFamily<Path>> fontFamilyPaths, TGS_FileCommonFavIcon favIconPng,
            TGS_FuncMTU_OutTyped_In1<TGS_Url, TGS_Url> manipulateInjectCode,
            Path dirDatTbl, Path dirDatPub, Path dirDatUsr, Path dirDatUsrTmp,
            TGS_FuncMTU_OutTyped_In5<List<String>, String, String, Long, String, Boolean> libTableFileList_getFileNames_DataIn,
            TGS_FuncMTU_OutTyped_In2<Path, String, String> libTableFileDir_datTblTblnameColname,
            TGS_FuncMTU_OutTyped_In2<TGS_Url, String, Boolean> libTableFileGetUtils_urlUsrTmp,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_PUBLIC,
            TGS_FuncMTU_OutTyped_In1<String, CharSequence> libFileServletUtils_URL_SERVLET_FETCH_USER,
            TGS_Url bootloaderJs
    ) {
        this.PARALLEL = PARALLEL;
        if (d.infoEnable) {
            IntStream.range(0, macroLines.size()).forEachOrdered(i -> {
                d.ci("macroLines", i, macroLines.get(i));
            });
            d.ci("username", username);
            d.ci("tablename", tablename);
            d.ci("selectedId", selectedId);
            d.ci("funcName", funcName);
            d.ci("fileNameLabel", fileNameLabel);
            d.ci("url", url);
            IntStream.range(0, requestedFileTypes.size()).forEachOrdered(i -> {
                d.ci("requestedFileTypes", i, requestedFileTypes.get(i));
            });
            d.ci("dirDat", dirDat);
            d.ci("fontFamilyPaths", fontFamilyPaths);
            d.ci("favIconPng", favIconPng);
            d.ci("manipulateInjectCode", manipulateInjectCode != null);
            d.ci("dirDatTbl", dirDatTbl);
            d.ci("dirDatPub", dirDatPub);
            d.ci("dirDatUsr", dirDatUsr);
            d.ci("dirDatUsrTmp", dirDatUsrTmp);
            d.ci("dirDatTbl", dirDatTbl);
            d.ci("dirDatTbl", dirDatTbl);
            d.ci("libTableFileList_getFileNames_DataIn", libTableFileList_getFileNames_DataIn != null);
            d.ci("libTableFileDir_datTblTblnameColname", libTableFileDir_datTblTblnameColname != null);
            d.ci("libTableFileGetUtils_urlUsrTmp", libTableFileGetUtils_urlUsrTmp != null);
            d.ci("libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE", libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE != null);
            d.ci("libFileServletUtils_URL_SERVLET_FETCH_PUBLIC", libFileServletUtils_URL_SERVLET_FETCH_PUBLIC != null);
            d.ci("libFileServletUtils_URL_SERVLET_FETCH_USER", libFileServletUtils_URL_SERVLET_FETCH_USER != null);
        }
        this.macroLines = macroLines;
        this.username = username;
        this.selectedId = selectedId;
        this.funcName = funcName;
        this.fileNameLabel = fileNameLabel;
        this.url = url;
        this.requestedFileTypes = requestedFileTypes;
        this.dirDat = dirDat;
        this.fontFamilyPaths = fontFamilyPaths;
//        this.customDomain = customDomain;
        this.favIconPng = favIconPng;
        this.manipulateInjectCode = manipulateInjectCode;
        this.dirDatTbl = dirDatTbl;
        this.dirDatPub = dirDatPub;
        this.dirDatUsr = dirDatUsr;
        this.dirDatUsrTmp = dirDatUsrTmp;
        this.libTableFileList_getFileNames_DataIn = libTableFileList_getFileNames_DataIn;
        this.libTableFileDir_datTblTblnameColname = libTableFileDir_datTblTblnameColname;
        this.libTableFileGetUtils_urlUsrTmp = libTableFileGetUtils_urlUsrTmp;
        this.libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE = libTableServletUtils_URL_SERVLET_FETCH_TBL_FILE;
        this.libFileServletUtils_URL_SERVLET_FETCH_PUBLIC = libFileServletUtils_URL_SERVLET_FETCH_PUBLIC;
        this.libFileServletUtils_URL_SERVLET_FETCH_USER = libFileServletUtils_URL_SERVLET_FETCH_USER;
        this.bootloaderJs = bootloaderJs;

        this.fontItalic = false;
        this.fontHeight = 12;
        this.fontColor = TS_FileCommonFontTags.CODE_TOKEN_FONT_COLOR_BLACK();
        this.runReport = false;
        this.tablename = tablename;
        this.now = TGS_Time.of();
        this.mapVars = TGS_ListUtils.of();
        this.cellHeight = null;
        this.userDotTablename = this.username + "." + this.tablename;
        this.domainName = bootloaderJs == null ? "localhost" : TGS_UrlParser.of(bootloaderJs).host.domain;
        this.fontFamilyFonts = TS_FontUtils.toFont(fontFamilyPaths, fontHeight);
    }
}
