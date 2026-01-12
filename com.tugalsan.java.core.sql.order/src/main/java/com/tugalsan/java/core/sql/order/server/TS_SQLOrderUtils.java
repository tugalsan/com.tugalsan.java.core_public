package com.tugalsan.java.core.sql.order.server;

public class TS_SQLOrderUtils {

    public static TS_SQLOrder asc() {
        return new TS_SQLOrder(true);
    }

    public static TS_SQLOrder desc() {
        return new TS_SQLOrder(false);
    }
}
