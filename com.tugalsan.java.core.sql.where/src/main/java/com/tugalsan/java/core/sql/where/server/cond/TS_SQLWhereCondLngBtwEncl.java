package com.tugalsan.java.core.sql.where.server.cond;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.string;
import module java.sql;

public class TS_SQLWhereCondLngBtwEncl extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondLngBtwEncl.class);

    public TS_SQLWhereCondLngBtwEncl(CharSequence columnName, long min, long max) {
        super(columnName);
        this.min = min;
        this.max = max;
    }

    final public long min;
    final public long max;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " BETWEEN ? AND ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            d.ci("fill", "processed", offset, min, max);
            var newOffset = offset + 1;
            fillStmt.setLong(newOffset, min);
            newOffset = newOffset + 1;
            fillStmt.setLong(newOffset, max);
            return newOffset;
        });
    }
}
