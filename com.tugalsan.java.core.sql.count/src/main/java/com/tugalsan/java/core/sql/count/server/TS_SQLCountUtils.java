package com.tugalsan.java.core.sql.count.server;

import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.where;

public class TS_SQLCountUtils {

    public static TS_SQLCount count(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLCount(anchor, tableName);
    }

    public static void setInfo(boolean enabled) {
        TS_SQLCountExecutor.d.infoEnable = enabled;
        TS_SQLWhereUtils.setInfo(enabled);
    }

//    public static void test() {
//        var count = TS_SQLCountUtils
//                .count(null, "ya")
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//    }
}
