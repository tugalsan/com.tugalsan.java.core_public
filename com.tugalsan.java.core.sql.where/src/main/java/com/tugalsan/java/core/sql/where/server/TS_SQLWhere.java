package com.tugalsan.java.core.sql.where.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.tuple;
import module java.sql;

public class TS_SQLWhere {

    public final static TS_Log d = TS_Log.of(TS_SQLWhere.class);

    private TS_SQLWhereGroups group = null;

    public TS_SQLWhereGroups groupsAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        group = new TS_SQLWhereGroups(true);
        return group.groupsAnd(groups);
    }

    public TS_SQLWhereGroups groupsOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> groups) {
        group = new TS_SQLWhereGroups(false);
        return group.groupsOr(groups);
    }

    public TS_SQLWhereConditions conditionsAnd(TGS_FuncMTU_In1<TS_SQLWhereConditionsAnd> conditions) {
        TGS_Tuple1<TS_SQLWhereConditions> pack = new TGS_Tuple1();
        groupsAnd(groups -> pack.value0 = groups.conditionsAnd(conditions));
        return pack.value0;
    }

    public TS_SQLWhereConditions conditionsOr(TGS_FuncMTU_In1<TS_SQLWhereConditionsOr> conditions) {
        TGS_Tuple1<TS_SQLWhereConditions> pack = new TGS_Tuple1();
        groupsOr(groups -> pack.value0 = groups.conditionsOr(conditions));
        return pack.value0;
    }

    @Override
    public String toString() {
        if (group == null) {
            return "";
        }
        var groupStr = group.toString();
        if (groupStr.replace("(", "").replace(")", "").isEmpty()) {
            return "";
        }
        return "WHERE " + groupStr;
    }

    public int fill(PreparedStatement stmt, int offset) {
        d.ci("fill", "processed");
        return group.fill(stmt, offset);
    }
}
