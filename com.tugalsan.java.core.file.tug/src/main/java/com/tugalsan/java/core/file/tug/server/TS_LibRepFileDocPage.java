package com.tugalsan.java.core.file.tug.server;

import module com.tugalsan.java.core.list;
import java.util.*;

public class TS_LibRepFileDocPage {

    public static int DEFAULT_PAGESIZE() {
        return 4;
    }

    public static boolean DEFAULT_LANDSCAPE() {
        return false;
    }

    public static int DEFAULT_MARGIN_LEFT() {
        return 50;
    }

    public static int DEFAULT_MARGIN_RIGHT() {
        return 50;
    }

    public static int DEFAULT_MARGIN_TOP() {
        return 50;
    }

    public static int DEFAULT_MARGIN_BOTTOM() {
        return 50;
    }

    public int pageSizeAX;
    public boolean landscape;
    public int marginLeft;
    public int marginRight;
    public int marginTop;
    public int marginBottom;

    public TS_LibRepFileDocPage() {
        this(null, null, null, null, null, null);
    }

    public TS_LibRepFileDocPage(Integer pageSizeAX, Boolean landscape) {
        this(pageSizeAX, landscape, null, null, null, null);
    }

    public TS_LibRepFileDocPage(Integer pageSizeAX, Boolean landscape, Integer marginLeft, Integer marginRight, Integer marginTop, Integer marginBottom) {
        this.pageSizeAX = (pageSizeAX == null || pageSizeAX <= 0) ? DEFAULT_PAGESIZE() : pageSizeAX;
        this.landscape = (landscape == null) ? DEFAULT_LANDSCAPE() : landscape;
        this.marginLeft = (marginLeft == null || marginLeft <= 0) ? DEFAULT_MARGIN_LEFT() : marginLeft;
        this.marginRight = (marginRight == null || marginRight <= 0) ? DEFAULT_MARGIN_RIGHT() : marginRight;
        this.marginTop = (marginTop == null || marginTop <= 0) ? DEFAULT_MARGIN_TOP() : marginTop;
        this.marginBottom = (marginBottom == null || marginBottom <= 0) ? DEFAULT_MARGIN_BOTTOM() : marginBottom;
        tables = TGS_ListUtils.of();
    }

    public List<TS_LibRepFileDocTable> tables;

    public TS_LibRepFileDocTable getCurrentTable() {
        if (tables.isEmpty()) {
            tables.add(new TS_LibRepFileDocTable());
        }
        return tables.get(tables.size() - 1);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("<div>");
        tables.stream().forEachOrdered(table -> sb.append(table));
        sb.append("</div>\n");
        return sb.toString();
    }
}
