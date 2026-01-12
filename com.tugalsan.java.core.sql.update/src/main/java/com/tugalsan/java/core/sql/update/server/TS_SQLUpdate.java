package com.tugalsan.java.core.sql.update.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.sql.conn;
import java.util.*;

public class TS_SQLUpdate {

    public TS_SQLUpdate(TS_SQLConnAnchor anchor, CharSequence tableName) {
        executor = new TS_SQLUpdateExecutor(anchor, tableName);
    }
    private final TS_SQLUpdateExecutor executor;

    public TS_SQLUpdateSet set(TGS_FuncMTU_In1<List<TGS_Tuple2<String, Object>>> set) {
        set.run(executor.set);
        return new TS_SQLUpdateSet(executor);
    }
}
