package com.tugalsan.java.core.sql.min.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.where;

public class TS_SQLMin {

    public TS_SQLMin(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.executor = new TS_SQLMinExecutor(anchor, tableName, columnName);
    }
    final private TS_SQLMinExecutor executor;

    public TS_SQLMinValue whereGroupAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return new TS_SQLMinValue(executor);
    }

    public TS_SQLMinValue whereGroupOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return new TS_SQLMinValue(executor);
    }

    public TS_SQLMinValue whereConditionAnd(TGS_FuncMTU_In1<TS_SQLWhereConditionsAnd> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public TS_SQLMinValue whereConditionOr(TGS_FuncMTU_In1<TS_SQLWhereConditionsOr> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public TS_SQLMinValue whereConditionNone() {
        return new TS_SQLMinValue(executor);
    }
}
