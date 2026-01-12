package com.tugalsan.java.core.sql.cell.client;

import java.io.Serializable;

final public class TGS_SQLCellBYTES extends TGS_SQLCellAbstract implements Serializable {

    @Override
    public TGS_SQLCellBYTES cloneIt() {
        return new TGS_SQLCellBYTES(valueBytes);
    }

    private byte[] valueBytes;

    public byte[] getValueBytes() {
        return valueBytes;
    }

    public void imitateValueBytes(byte[] valueBytes) {
        this.valueBytes = valueBytes;
    }

    public TGS_SQLCellBYTES() {
        this(null);
    }

    public TGS_SQLCellBYTES(byte[] valueBytes) {
        this.valueBytes = valueBytes;
    }

    @Override
    public String toString() {
        return null;
    }
}
