package com.tugalsan.java.core.sql.cellgen.server;

import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.max;
import java.util.*;

public class TS_SQLCellGenLngNextDated<E> extends TS_SQLCellGenAbstract<E, Long> {

    public TS_SQLCellGenLngNextDated(E executor, int colIdx, TS_SQLConnAnchor anchor, CharSequence tableName, List<String> colNames) {
        super(executor, colIdx);
        this.anchor = anchor;
        this.tableName = tableName.toString();
        this.colNames = colNames;
    }
    final private TS_SQLConnAnchor anchor;
    final private String tableName;
    final private List<String> colNames;

    @Override
    public Long val() {
        return TS_SQLMaxUtils.max(anchor, tableName, colNames.get(colIdx)).whereConditionNone().nextIdDated();
    }
}
