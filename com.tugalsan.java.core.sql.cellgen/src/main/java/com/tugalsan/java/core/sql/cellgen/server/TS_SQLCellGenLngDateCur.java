package com.tugalsan.java.core.sql.cellgen.server;

import module com.tugalsan.java.core.time;

public class TS_SQLCellGenLngDateCur<E> extends TS_SQLCellGenAbstract<E, Long> {

    public TS_SQLCellGenLngDateCur(E executor, int colIdx) {
        super(executor, colIdx);
    }

    @Override
    public Long val() {
        return TGS_Time.getCurrentDate();
    }
}
