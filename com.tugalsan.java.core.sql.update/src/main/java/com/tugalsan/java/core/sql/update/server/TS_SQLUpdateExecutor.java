package com.tugalsan.java.core.sql.update.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.sql.where;
import module java.sql;
import java.util.*;
import java.util.stream.*;

public class TS_SQLUpdateExecutor {

    public static TS_Log d = TS_Log.of(TS_SQLUpdateExecutor.class);

    public TS_SQLUpdateExecutor(TS_SQLConnAnchor anchor, CharSequence tableName) {
        this.anchor = anchor;
        this.tableName = tableName;
    }
    final public TS_SQLConnAnchor anchor;
    final public CharSequence tableName;

    public List<TGS_Tuple2<String, Object>> set = TGS_ListUtils.of();
    public TS_SQLWhere where = null;

    private String set_toString() {
        var sj = new StringJoiner(",");
        set.stream().forEachOrdered(pair -> {
            TS_SQLSanitizeUtils.sanitize(pair.value0);
            sj.add(pair.value0 + " = ?");
        });
        return sj.toString();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("UPDATE ").append(tableName).append(" SET ").append(set_toString());
        if (where != null) {
            sb.append(" ").append(where);
        }
        var str = sb.toString();
        d.ci("toString", "pstmt", str);
        d.ci("toString", "set", set);
        return str;
    }

    private int set_fill(PreparedStatement fillStmt, int offset) {
        TGS_Tuple1<Integer> pack = new TGS_Tuple1(offset);
        IntStream.range(0, set.size()).forEachOrdered(i -> {
            pack.value0 = TS_SQLConnStmtUtils.fill(fillStmt, set.get(i).value0, set.get(i).value1, pack.value0);
        });
        return pack.value0;
    }

    public TS_SQLConnStmtUpdateResult run() {
        return TS_SQLUpdateStmtUtils.update(anchor, toString(), fillStmt -> {
            var idx = set_fill(fillStmt, 0);
            where.fill(fillStmt, idx);
        });
    }
}
