package com.tugalsan.java.core.sql.min.server;

import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.sql.conn;

public class TS_SQLMinUtils {

    final private static TS_Log d = TS_Log.of(TS_SQLMinUtils.class);

    public static TS_SQLMin min(TS_SQLConnAnchor anchor, CharSequence tableName, int colIdx) {
        var columnName = TS_SQLConnColUtils.names(anchor, tableName).get(colIdx);
        d.ci("min", "columnName", columnName);
        return new TS_SQLMin(anchor, tableName, columnName);
    }

    public static TS_SQLMin min(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        return new TS_SQLMin(anchor, tableName, columnName);
    }

//    public static void test() {
//        var min = TS_SQLMinUtils
//                .min(null, "tn", "cn")
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//    }
}
