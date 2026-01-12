package com.tugalsan.java.core.sql.where.server;

import module com.tugalsan.java.core.sql.where;

public class TS_SQLWhereUtils {

    public static TS_SQLWhere where() {
        return new TS_SQLWhere();
    }

    public static void setInfo(boolean enabled) {
        TS_SQLWhere.d.infoEnable = enabled;
        TS_SQLWhereGroups.d.infoEnable = enabled;
        TS_SQLWhereConditions.d.infoEnable = enabled;
        TS_SQLWhereCondBlobLenEq.d.infoEnable = enabled;
        TS_SQLWhereCondBlobLenGrt.d.infoEnable = enabled;
        TS_SQLWhereCondBlobLenGrtOrEq.d.infoEnable = enabled;
        TS_SQLWhereCondBlobLenSml.d.infoEnable = enabled;
        TS_SQLWhereCondBlobLenSmlOrEq.d.infoEnable = enabled;
        TS_SQLWhereCondColEq.d.infoEnable = enabled;
        TS_SQLWhereCondColEqNot.d.infoEnable = enabled;
        TS_SQLWhereCondColGrt.d.infoEnable = enabled;
        TS_SQLWhereCondColGrtEq.d.infoEnable = enabled;
        TS_SQLWhereCondColSml.d.infoEnable = enabled;
        TS_SQLWhereCondColSmlEq.d.infoEnable = enabled;
        TS_SQLWhereCondLngBtwEncl.d.infoEnable = enabled;
        TS_SQLWhereCondLngEq.d.infoEnable = enabled;
        TS_SQLWhereCondLngEqNot.d.infoEnable = enabled;
        TS_SQLWhereCondLngGrt.d.infoEnable = enabled;
        TS_SQLWhereCondLngGrtOrEq.d.infoEnable = enabled;
        TS_SQLWhereCondLngSml.d.infoEnable = enabled;
        TS_SQLWhereCondLngSmlOrEq.d.infoEnable = enabled;
        TS_SQLWhereCondStrCon.d.infoEnable = enabled;
        TS_SQLWhereCondStrConLowerCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrConUpperCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrEq.d.infoEnable = enabled;
        TS_SQLWhereCondStrEqLowerCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrEqUpperCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrPre.d.infoEnable = enabled;
        TS_SQLWhereCondStrPreLowerCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrPreUpperCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrSuf.d.infoEnable = enabled;
        TS_SQLWhereCondStrSufLowerCase.d.infoEnable = enabled;
        TS_SQLWhereCondStrSufUpperCase.d.infoEnable = enabled;
    }
}
