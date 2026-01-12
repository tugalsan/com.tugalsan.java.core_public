package com.tugalsan.java.core.sql.insert.server;

import module com.tugalsan.java.core.sql.conn;

public class TS_SQLInsertUtils {

//    final private static TS_Log d = TS_Log.of(TS_SQLInsertUtils.class);

    public static TS_SQLInsert insert(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLInsert(anchor, tableName);
    }

//    public static void test() {
//        TS_SQLInsertUtils.insert(null, "tn").valObj("12");
//    }
}
