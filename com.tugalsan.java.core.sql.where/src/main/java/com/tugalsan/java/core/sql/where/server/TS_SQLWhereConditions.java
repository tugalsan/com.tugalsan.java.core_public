package com.tugalsan.java.core.sql.where.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.sql.where;
import module java.sql;
import java.util.*;
import java.util.stream.*;

public abstract class TS_SQLWhereConditions {

    public final static TS_Log d = TS_Log.of(TS_SQLWhereConditions.class);

    public TS_SQLWhereConditions(boolean operatorIsAnd) {
        this.operatorIsAnd = operatorIsAnd;
    }
    final private boolean operatorIsAnd;

    public final List<TS_SQLWhereCondAbstract> conditions = TGS_ListUtils.of();

    public TS_SQLWhereConditions blobLenGrt(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondBlobLenGrt(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions blobLenGrtOrEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondBlobLenGrtOrEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions blobLenEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondBlobLenEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions blobLenSml(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondBlobLenSml(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions blobLenSmlOrEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondBlobLenSmlOrEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions colSml(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColSml(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions colGrt(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColGrt(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions colSmlEq(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColSmlEq(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions colGrtEq(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColGrtEq(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions colEq(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColEq(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions colEqNot(CharSequence columnName, String columnName2) {
        conditions.add(new TS_SQLWhereCondColEqNot(columnName, columnName2));
        return this;
    }

    public TS_SQLWhereConditions lngBtwEncl(CharSequence columnName, long min, long max) {
        conditions.add(new TS_SQLWhereCondLngBtwEncl(columnName, min, max));
        return this;
    }

    public TS_SQLWhereConditions lngEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions lngEqNot(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngEqNot(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions lngGrt(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngGrt(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions lngGrtOrEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngGrtOrEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions lngSml(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngSml(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions lngSmlOrEq(CharSequence columnName, long val) {
        conditions.add(new TS_SQLWhereCondLngSmlOrEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strCon(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrCon(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strConUpperCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrConUpperCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strConLowerCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrConLowerCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strPresent(CharSequence columnName) {
        conditions.add(new TS_SQLWhereCondStrPresent(columnName));
        return this;
    }

    public TS_SQLWhereConditions strEmpty(CharSequence columnName) {
        conditions.add(new TS_SQLWhereCondStrEmpty(columnName));
        return this;
    }

    public TS_SQLWhereConditions strEq(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrEq(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strEqNot(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrEqNot(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strEqUpperCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrEqUpperCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strEqLowerCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrEqLowerCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strPre(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrPre(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strPreUpperCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrPreUpperCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strPreLowerCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrPreLowerCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strSuf(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrSuf(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strSufUpperCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrSufUpperCase(columnName, val));
        return this;
    }

    public TS_SQLWhereConditions strSufLowerCase(CharSequence columnName, CharSequence val) {
        conditions.add(new TS_SQLWhereCondStrSufLowerCase(columnName, val));
        return this;
    }

    @Override
    public String toString() {
        if (conditions.isEmpty()) {
            return "";
        }
        var sb = new StringBuilder("(");
        IntStream.range(0, conditions.size()).forEachOrdered(i -> {
            if (i != 0) {
                addOperator(sb);
            }
            sb.append(conditions.get(i).toString());
        });
        return sb.append(")").toString();
    }

    public int fill(PreparedStatement stmt, int offset) {
        d.ci("fill", "processed");
        TGS_Tuple1<Integer> pack = new TGS_Tuple1(offset);
        conditions.stream().forEachOrdered(c -> pack.value0 = c.fill(stmt, pack.value0));
        return pack.value0;
    }

    private void addOperator(StringBuilder sb) {
        sb.append(operatorIsAnd ? " AND " : " OR ");
    }
}
