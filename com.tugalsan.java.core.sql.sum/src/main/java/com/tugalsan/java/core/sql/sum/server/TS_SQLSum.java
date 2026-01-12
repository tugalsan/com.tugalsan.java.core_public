package com.tugalsan.java.core.sql.sum.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.where;

public class TS_SQLSum {

    public TS_SQLSum(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.executor = new TS_SQLSumExecutor(anchor, tableName, columnName);
    }
    private final TS_SQLSumExecutor executor;

    public long whereGroupAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public long whereGroupOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public long whereConditionAnd(TGS_FuncMTU_In1<TS_SQLWhereConditionsAnd> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public long whereConditionOr(TGS_FuncMTU_In1<TS_SQLWhereConditionsOr> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public long whereConditionNone() {
        return executor.run();
    }
}
