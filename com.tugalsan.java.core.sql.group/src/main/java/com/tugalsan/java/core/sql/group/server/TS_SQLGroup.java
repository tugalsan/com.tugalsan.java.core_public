package com.tugalsan.java.core.sql.group.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.sql.sanitize;
import module com.tugalsan.java.core.string;
import java.util.*;

public class TS_SQLGroup {

    public TS_SQLGroup() {
    }
    public List<String> columnNames = TGS_ListUtils.of();

    @Override
    public String toString() {
        if (columnNames.isEmpty()) {
            return "";
        }
        TS_SQLSanitizeUtils.sanitize(columnNames);
        return TGS_StringUtils.cmn().concat("GROUP BY ", TGS_StringUtils.cmn().toString(columnNames, ","));
    }
}
