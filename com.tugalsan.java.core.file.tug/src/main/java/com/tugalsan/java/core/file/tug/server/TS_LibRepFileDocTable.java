package com.tugalsan.java.core.file.tug.server;

import module com.tugalsan.java.core.list;
import java.util.*;

public class TS_LibRepFileDocTable {

    public static int DEFAULT_COLUMNSIZE() {
        return 1;
    }

    public int[] relColSizes;
    public List<TS_LibRepFileDocRow> rows;

    private static int[] fillRelColSizes(int columnCount) {
        var relColSizes = new int[columnCount];
        Arrays.fill(relColSizes, 1);
        return relColSizes;
    }

    public TS_LibRepFileDocTable() {
        this(DEFAULT_COLUMNSIZE());
    }

    public TS_LibRepFileDocTable(int columnCount) {
        this(fillRelColSizes(columnCount));
    }

    public TS_LibRepFileDocTable(int[] relColSizes) {
        this.relColSizes = relColSizes;
        rows = TGS_ListUtils.of();
    }

    public TS_LibRepFileDocRow getCurrentRow() {
        if (rows.isEmpty()) {
            rows.add(new TS_LibRepFileDocRow());
        }
        return rows.get(rows.size() - 1);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("<rows>\n");
        rows.stream().forEachOrdered(row -> sb.append(row));
        sb.append("</rows>\n");
        return sb.toString();
    }
}
