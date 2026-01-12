package com.tugalsan.java.core.sql.cell.client;

import com.tugalsan.java.core.string.client.*;
import java.io.Serializable;

final public class TGS_SQLCellSTR extends TGS_SQLCellAbstract implements Serializable {

    @Override
    public TGS_SQLCellSTR cloneIt() {
        return new TGS_SQLCellSTR(valueString);
    }
    private String valueString;

    public String getValueString() {
        return valueString;
    }

    final public void imitateValueString(CharSequence valueString) {
        this.valueString = TGS_StringUtils.cmn().toEmptyIfNull(valueString);
    }

    public TGS_SQLCellSTR() {
        this("");
    }

    public TGS_SQLCellSTR(CharSequence valueString) {
        imitateValueString(valueString);
    }

    @Override
    public String toString() {
        return valueString;
    }
}
