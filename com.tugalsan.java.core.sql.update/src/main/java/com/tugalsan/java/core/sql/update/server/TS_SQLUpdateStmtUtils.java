package com.tugalsan.java.core.sql.update.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.sql.conn;
import module java.sql;
import java.util.*;

public class TS_SQLUpdateStmtUtils {

    final private static TS_Log d = TS_Log.of(TS_SQLUpdateStmtUtils.class);

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, List<String> colNames, List params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, String[] colNames, Object[] params) {
        return update(anchor, sqlStmt, fillStmt -> TS_SQLConnStmtUtils.fill(fillStmt, colNames, params, 0));
    }

    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt, TGS_FuncMTU_In1<PreparedStatement> fillStmt) {
        return TS_SQLConnWalkUtils.update(anchor, sqlStmt, fillStmt);
    }

    //WARNING: IS SQL SAFE!
    @Deprecated
    public static TS_SQLConnStmtUpdateResult update(TS_SQLConnAnchor anchor, CharSequence sqlStmt) {
        d.ci("update", sqlStmt);
        return update(anchor, sqlStmt, fill -> {
        });
    }
}
