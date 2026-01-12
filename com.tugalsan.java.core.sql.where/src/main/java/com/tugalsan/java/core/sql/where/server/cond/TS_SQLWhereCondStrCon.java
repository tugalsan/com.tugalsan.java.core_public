package com.tugalsan.java.core.sql.where.server.cond;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.string;
import module java.sql;

public class TS_SQLWhereCondStrCon extends TS_SQLWhereCondAbstract {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereCondStrCon.class);

    public TS_SQLWhereCondStrCon(CharSequence columnName, CharSequence val) {
        super(columnName);
        this.val = val == null ? null : val.toString();
    }
    final public String val;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        return TGS_StringUtils.cmn().concat(columnName, " LIKE ?");
    }

    @Override
    public int fill(PreparedStatement fillStmt, int offset) {
        return TGS_FuncMTCUtils.call(() -> {
            d.ci("fill", "processed", offset, val);
            var newOffset = offset + 1;
            if (val != null) {
                fillStmt.setString(newOffset, TGS_StringUtils.cmn().concat("%", val, "%"));
            } else {
                fillStmt.setString(newOffset, TGS_StringUtils.cmn().concat("null"));
            }
            return newOffset;
        });
    }
}
