package com.tugalsan.java.core.sql.cellgen.server;

public class TS_SQLCellGenLngDefault<E> extends TS_SQLCellGenAbstract<E, Long> {

    public TS_SQLCellGenLngDefault(E executor, int colIdx) {
        super(executor, colIdx);
    }

    @Override
    public Long val() {
        return 0L;
    }
}
