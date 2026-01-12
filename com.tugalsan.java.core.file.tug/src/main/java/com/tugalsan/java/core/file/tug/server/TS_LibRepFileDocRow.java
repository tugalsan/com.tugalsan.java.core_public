package com.tugalsan.java.core.file.tug.server;

import module com.tugalsan.java.core.list;
import java.util.*;

public class TS_LibRepFileDocRow {

    public List<TS_LibRepFileDocCell> cells;

    public TS_LibRepFileDocRow() {
        cells = TGS_ListUtils.of();
    }

    public TS_LibRepFileDocCell getCurrentCell() {
        if (cells.isEmpty()) {
            cells.add(new TS_LibRepFileDocCell());
        }
        return cells.get(cells.size() - 1);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("<tr>\n");
        cells.stream().forEachOrdered(cell -> sb.append(cell));
        sb.append("</tr>\n");
        return sb.toString();
    }

}
