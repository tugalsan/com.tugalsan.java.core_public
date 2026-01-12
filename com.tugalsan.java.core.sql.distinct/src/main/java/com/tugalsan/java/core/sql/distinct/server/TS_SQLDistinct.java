package com.tugalsan.java.core.sql.distinct.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.string;
import java.util.*;

public class TS_SQLDistinct {

    final private static TS_Log d = TS_Log.of(TS_SQLDistinct.class);

    public TS_SQLDistinct(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLDistinctExecutor(anchor, tableName);
    }
    final private TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctWhere columns(TGS_FuncMTU_In1<List<String>> columnNames) {
        columnNames.run(executor.columnNames);
        return new TS_SQLDistinctWhere(executor);
    }

    public TS_SQLDistinctWhere columns(List<String> columns) {
        if (columns == null || columns.isEmpty()) {
            return columnsAll();
        }
        columns.stream().forEachOrdered(cn -> executor.columnNames.add(TGS_StringUtils.cmn().isNullOrEmpty(cn) ? TS_SQLDistinctUtils.columnEmpty() : cn));
        return new TS_SQLDistinctWhere(executor);
    }

    public TS_SQLDistinctWhere columns(int... colIdxes) {
        if (colIdxes == null || colIdxes.length == 0) {
            return columnsAll();
        }
        var cnsAll = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        Arrays.stream(colIdxes).forEachOrdered(colIdx -> {
            if (colIdx == -1) {
                executor.columnNames.add(TS_SQLDistinctUtils.columnEmpty());
            } else {
                executor.columnNames.add(cnsAll.get(colIdx));
            }
        });
        return new TS_SQLDistinctWhere(executor);
    }

    public TS_SQLDistinctWhere columns(CharSequence... columns) {
        if (columns == null || columns.length == 0) {
            return columnsAll();
        }
        Arrays.stream(columns).forEachOrdered(cn -> {
            var input = TGS_StringUtils.cmn().isNullOrEmpty(cn) ? TS_SQLDistinctUtils.columnEmpty() : cn.toString();
            d.ci("columns(CharSequence... columns)", input);
            executor.columnNames.add(input);
        });
        return new TS_SQLDistinctWhere(executor);
    }

    public TS_SQLDistinctWhere columns(String[] columns) {
        if (columns == null || columns.length == 0) {
            return columnsAll();
        }
        Arrays.stream(columns).forEachOrdered(cn -> executor.columnNames.add(TGS_StringUtils.cmn().isNullOrEmpty(cn) ? TS_SQLDistinctUtils.columnEmpty() : cn));
        return new TS_SQLDistinctWhere(executor);
    }

    public TS_SQLDistinctWhere columnsAll() {
        return new TS_SQLDistinctWhere(executor);
    }
}
