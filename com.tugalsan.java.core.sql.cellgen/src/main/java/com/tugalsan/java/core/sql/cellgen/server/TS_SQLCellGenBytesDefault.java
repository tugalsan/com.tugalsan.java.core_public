package com.tugalsan.java.core.sql.cellgen.server;

public class TS_SQLCellGenBytesDefault<E> extends TS_SQLCellGenAbstract<E, byte[]> {

    public TS_SQLCellGenBytesDefault(E executor, int colIdx) {
        super(executor, colIdx);
    }

    @Override
    public byte[] val() {
        return null;
    }
}
