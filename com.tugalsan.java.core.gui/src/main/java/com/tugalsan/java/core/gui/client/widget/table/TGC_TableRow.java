package com.tugalsan.java.core.gui.client.widget.table;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_TableRow {

    public TGC_TableRow(int rowIdx, int colSize) {
        this.rowIdx = rowIdx; 
        this.cells = new String[colSize];
    }
    public Integer rowIdx;
    public String[] cells;
}
