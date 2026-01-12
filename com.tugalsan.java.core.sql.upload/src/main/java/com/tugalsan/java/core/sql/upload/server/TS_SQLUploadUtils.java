package com.tugalsan.java.core.sql.upload.server;

import module com.tugalsan.java.core.sql.conn;

public class TS_SQLUploadUtils {

    public static TS_SQLUpload upload(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLUpload(anchor, tableName);
    }

//    public static void test() {
//        TS_SQLUploadUtils
//                .upload(null, "tn")
//                .setFile("colName", new File("").toPath())
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//    }
}
