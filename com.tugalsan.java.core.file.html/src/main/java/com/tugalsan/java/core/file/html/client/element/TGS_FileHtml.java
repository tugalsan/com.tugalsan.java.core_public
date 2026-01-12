package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import com.tugalsan.java.core.file.common.client.TGS_FileCommonFavIcon;
import com.tugalsan.java.core.file.html.client.*;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.url.client.TGS_Url;
import java.util.*;

public class TGS_FileHtml {

    public CharSequence browserTitle;
    public TGS_FileCommonFavIcon browserIconHrefPng;
    public TGS_Url bootLoaderJs;
    public Integer pageSizeAX;
    public Boolean landscape;

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }
    private final List<TGS_FileHtmlElement> childeren;

    private TGS_FileHtml(CharSequence pageTitle, TGS_FileCommonFavIcon hrefPngIcon, TGS_Url bootLoaderJs, Integer pageSizeAX, Boolean landscape) {
        this.browserTitle = pageTitle;
        this.browserIconHrefPng = hrefPngIcon;
        this.bootLoaderJs = bootLoaderJs;
        this.pageSizeAX = pageSizeAX;
        this.landscape = landscape;
        childeren = TGS_ListUtils.of();
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        sj.add(TGS_FileHtmlUtils.beginLines(browserTitle, addTableBorder, 5, 5, browserIconHrefPng, addDivCenter, bootLoaderJs, pageSizeAX, landscape));
        childeren.stream().forEachOrdered(s -> sj.add(s.toString()));
        sj.add(TGS_FileHtmlUtils.endLines(true));
        return sj.toString();
    }
    public boolean addTableBorder = false;
    public boolean addDivCenter = true;

    public static TGS_FileHtml of(TGS_FuncMTU_OutTyped_In1<String, CharSequence> optional_escapeHTML, TGS_ListTable optional_lstTable, CharSequence title, TGS_FileCommonFavIcon optional_hrefPngIcon, TGS_Url optional_bootLoaderJs) {
        return of(optional_escapeHTML, optional_lstTable, title, optional_hrefPngIcon, optional_bootLoaderJs, null, null);
    }

    public static TGS_FileHtml of(TGS_FuncMTU_OutTyped_In1<String, CharSequence> optional_escapeHTML, TGS_ListTable optional_lstTable, CharSequence title, TGS_FileCommonFavIcon optional_hrefPngIcon, TGS_Url optional_bootLoaderJs, Integer pageSizeAX, Boolean landscape) {
        var html = new TGS_FileHtml(title, optional_hrefPngIcon, optional_bootLoaderJs, pageSizeAX, landscape);
        if (optional_lstTable != null) {
            var htmlTable = TGS_FileHtmlTable.of(optional_escapeHTML, optional_lstTable);
            html.getChilderen().add(htmlTable);
        }
        return html;
    }
}
