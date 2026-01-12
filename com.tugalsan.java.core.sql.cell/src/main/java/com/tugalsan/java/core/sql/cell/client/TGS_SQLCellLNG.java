package com.tugalsan.java.core.sql.cell.client;

import java.io.Serializable;

final public class TGS_SQLCellLNG extends TGS_SQLCellAbstract implements Serializable {

    @Override
    public TGS_SQLCellLNG cloneIt() {
        return new TGS_SQLCellLNG(valueLong);
    }
    private long valueLong;

    public long getValueLong() {
        return valueLong;
    }

    public void imitateValueLong(long valueLong) {
        this.valueLong = valueLong;
    }

    public TGS_SQLCellLNG() {
        this(0L);
    }

    public TGS_SQLCellLNG(long valueLong) {
        this.valueLong = valueLong;
    }

    @Override
    public String toString() {
        return String.valueOf(valueLong);
    }
}
