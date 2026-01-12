package com.tugalsan.java.core.file.tug.server;

import module com.tugalsan.java.core.list;
import java.util.*;

public class TS_LibRepFileDocCell {

    public List<TS_LibRepFileDocParag> parags;

    public static int DEFAULT_ROWSPAN() {
        return 1;
    }

    public static int DEFAULT_COLSPAN() {
        return 1;
    }

    public static int DEFAULT_MAXCELLWIDTH() {
        return 500;
    }

    public static int DEFAULT_CELLHEIGHT() {
        return 20;
    }

    public Integer rowSpan;
    public Integer colSpan;
    public Integer cellHeight;

    public TS_LibRepFileDocCell() {
        this(null, null, null);
    }

    public TS_LibRepFileDocCell(Integer rowSpan, Integer colSpan) {
        this(rowSpan, colSpan, null);
    }

    public TS_LibRepFileDocCell(Integer rowSpan, Integer colSpan, Integer cellHeight) {
        parags = TGS_ListUtils.of();
        this.rowSpan = (rowSpan == null || rowSpan <= 0) ? DEFAULT_ROWSPAN() : rowSpan;
        this.colSpan = (colSpan == null || colSpan <= 0) ? DEFAULT_COLSPAN() : colSpan;
        this.cellHeight = (cellHeight == null || cellHeight <= 0) ? DEFAULT_CELLHEIGHT() : cellHeight;
    }

    public TS_LibRepFileDocParag getCurrentParag() {
        if (parags.isEmpty()) {
            parags.add(new TS_LibRepFileDocParag());
        }
        return parags.get(parags.size() - 1);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("<td>\n");
        parags.stream().forEachOrdered(parag -> sb.append(parag));
        sb.append("</td>\n");
        return sb.toString();
    }
}
