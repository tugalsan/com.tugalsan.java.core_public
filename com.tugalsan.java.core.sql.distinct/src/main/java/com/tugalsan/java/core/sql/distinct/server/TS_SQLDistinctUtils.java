package com.tugalsan.java.core.sql.distinct.server;

import module com.tugalsan.java.core.sql.conn;

public class TS_SQLDistinctUtils {

    private TS_SQLDistinctUtils() {

    }

    public static TS_SQLDistinct distict(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLDistinct(anchor, tableName);
    }

    public static String columnEmpty() {
        return "''";
    }

//    public static void test() {
//        TS_SQLDistinctUtils.select(null, "tn").columns(columnNames -> {
//            columnNames.add("ali gel");
//        }).whereConditionAnd(conditions -> {
//            conditions.lngEq("", 0);
//        }).groupNone().orderNone().rowIdxOffsetNone().rowSizeLimitNone().walk(null, rs -> {
//        });
//    }
}
