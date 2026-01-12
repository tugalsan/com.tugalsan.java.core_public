package com.tugalsan.java.core.sql.distinct.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.sql.conn;
import module com.tugalsan.java.core.sql.where;

public class TS_SQLDistinctWhere {

    public TS_SQLDistinctWhere(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    final private TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctGroup whereGroupAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> gAnd) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(gAnd);
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereGroupOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> gOr) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(gOr);
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereConditionAnd(TGS_FuncMTU_In1<TS_SQLWhereConditionsAnd> cAnd) {
        whereGroupAnd(where -> where.conditionsAnd(cAnd));
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereConditionOr(TGS_FuncMTU_In1<TS_SQLWhereConditionsOr> cOr) {
        whereGroupOr(where -> where.conditionsOr(cOr));
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctExecutor whereFirstColumnAsId(long id) {
        return whereConditionAnd(conditions -> {
            conditions.lngEq(
                    TS_SQLConnColUtils.names(executor.anchor, executor.tableName).get(0),
                    id
            );
        }).groupNone().orderNone().rowIdxOffsetNone().rowSizeLimitNone();
    }

    public TS_SQLDistinctGroup whereConditionNone() {
        return new TS_SQLDistinctGroup(executor);
    }
}
