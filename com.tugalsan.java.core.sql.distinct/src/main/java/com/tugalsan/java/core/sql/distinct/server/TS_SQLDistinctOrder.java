package com.tugalsan.java.core.sql.distinct.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.order;
import java.util.*;

public class TS_SQLDistinctOrder {

    public TS_SQLDistinctOrder(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    private final TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctRowIdxOffset orderAsc(String[] columns) {
        if (columns == null || columns.length == 0) {
            return orderNone();
        }
        executor.order = TS_SQLOrderUtils.asc();
        Arrays.stream(columns).forEachOrdered(cn -> executor.order.columnNames.add(cn));
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderAsc(CharSequence... columns) {
        if (columns == null || columns.length == 0) {
            return orderNone();
        }
        executor.order = TS_SQLOrderUtils.asc();
        Arrays.stream(columns).forEachOrdered(cn -> executor.order.columnNames.add(cn.toString()));
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderDesc(String[] columns) {
        if (columns == null || columns.length == 0) {
            return orderNone();
        }
        executor.order = TS_SQLOrderUtils.desc();
        Arrays.stream(columns).forEachOrdered(cn -> executor.order.columnNames.add(cn));
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderDesc(CharSequence... columns) {
        if (columns == null || columns.length == 0) {
            return orderNone();
        }
        executor.order = TS_SQLOrderUtils.desc();
        Arrays.stream(columns).forEachOrdered(cn -> executor.order.columnNames.add(cn.toString()));
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderAsc(int colIdx) {
        var colNames = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        if (colIdx < 0 || colIdx >= colNames.size()) {
            return orderNone();
        }
        return orderAsc(colNames.get(colIdx));
    }

    public TS_SQLDistinctRowIdxOffset orderDesc(int colIdx) {
        var colNames = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        if (colIdx < 0 || colIdx >= colNames.size()) {
            return orderNone();
        }
        return orderDesc(colNames.get(colIdx));
    }

    public TS_SQLDistinctRowIdxOffset orderAsc(String columnName) {
        return orderAsc(columnNames -> {
            columnNames.add(columnName);
        });
    }

    public TS_SQLDistinctRowIdxOffset orderDesc(String columnName) {
        return orderDesc(columnNames -> {
            columnNames.add(columnName);
        });
    }

    public TS_SQLDistinctRowIdxOffset orderAsc(TGS_FuncMTU_In1<List<String>> columnNames) {
        executor.order = TS_SQLOrderUtils.asc();
        columnNames.run(executor.order.columnNames);
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderDesc(TGS_FuncMTU_In1<List<String>> columnNames) {
        executor.order = TS_SQLOrderUtils.desc();
        columnNames.run(executor.order.columnNames);
        return new TS_SQLDistinctRowIdxOffset(executor);
    }

    public TS_SQLDistinctRowIdxOffset orderNone() {
        return new TS_SQLDistinctRowIdxOffset(executor);
    }
}
