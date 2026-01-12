package com.tugalsan.java.core.sql.order.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.string;
import java.util.*;

public class TS_SQLOrder {

    public TS_SQLOrder(boolean ascending) {
        this.ascending = ascending;
    }
    public List<String> columnNames = TGS_ListUtils.of();
    public boolean ascending = true;

    @Override
    public String toString() {
        if (columnNames.isEmpty()) {
            return "";
        }
        TS_SQLSanitizeUtils.sanitize(columnNames);
        return TGS_StringUtils.cmn().concat("ORDER BY ", TGS_StringUtils.cmn().toString(columnNames, ","), ascending ? " ASC" : " DESC");
    }
}
