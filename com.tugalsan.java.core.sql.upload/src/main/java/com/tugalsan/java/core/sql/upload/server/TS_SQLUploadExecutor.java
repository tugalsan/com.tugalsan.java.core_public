package com.tugalsan.java.core.sql.upload.server;

import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.sql.order;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.sql.update;
import module com.tugalsan.java.core.sql.where;
import module com.tugalsan.java.core.function;
import module java.sql;
import java.io.*;

public class TS_SQLUploadExecutor {

    public TS_SQLUploadExecutor(TS_SQLConnAnchor anchor, CharSequence tableName) {
        this.anchor = anchor;
        this.tableName = tableName;
    }
    final public TS_SQLConnAnchor anchor;
    final public CharSequence tableName;

    public TGS_Tuple3<String, InputStream, Long> set = new TGS_Tuple3();
    public TS_SQLWhere where = null;
    public TS_SQLOrder order = null;

    private String set_toString() {
        TS_SQLSanitizeUtils.sanitize(set.value0);
        return set.value0.concat(" = ?");
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("UPDATE ").append(tableName).append(" SET ").append(set_toString());
        if (where != null) {
            sb.append(" ").append(where);
        }
        if (order != null) {
            sb.append(" ").append(order);
        }
        return sb.toString();
    }

    private int set_fill(PreparedStatement stmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            var newOffset = offset;
            try (var is = set.value1) {
                if (set.value2 == null || set.value2 == 0L) {
                    stmt.setBinaryStream(newOffset++, is);
                } else {
                    stmt.setBinaryStream(newOffset++, is, set.value2);
                }
            }
            return newOffset;
        });
    }

    public TS_SQLConnStmtUpdateResult run() {
        return TGS_FuncMTCUtils.call(() -> {
            try (var is = set.value1) {
                return TS_SQLUpdateStmtUtils.update(anchor, toString(), fillStmt -> {
                    var idx = set_fill(fillStmt, 0);
                    where.fill(fillStmt, idx);
                });
            }
        });
    }
}
