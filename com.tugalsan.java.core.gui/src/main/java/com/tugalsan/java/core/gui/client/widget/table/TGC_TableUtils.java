package com.tugalsan.java.core.gui.client.widget.table;

import com.google.gwt.user.cellview.client.*;
import com.google.gwt.view.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.tuple.client.*;
import com.tugalsan.java.core.string.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;
import java.util.*;
import java.util.stream.*;

public class TGC_TableUtils {

    private TGC_TableUtils() {

    }

    public static TGS_Tuple2<CellTable<TGC_TableRow>, List<TGC_TableRow>> createTable(int rowSize, List<String> columnTitles, TGS_FuncMTU_In1<String> onSelectTitle) {
        CellTable<TGC_TableRow> cellTable = new CellTable();
        var colSize = columnTitles.size();

        var builder = new TGC_TableStyle<TGC_TableRow>(cellTable);
        cellTable.setTableBuilder(builder);
        TGC_TableStyle.DEFAULT_RESOURCE.cellTableStyle().ensureInjected();
        TGC_TableStyle.CUSTOM_RESOURCE.cellTableStyle().ensureInjected();
        builder.setStyle(new TGC_TableStyle.StyleAdapter(TGC_TableStyle.CUSTOM_RESOURCE.cellTableStyle()));
        cellTable.redraw();

        cellTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        cellTable.setSelectionModel(new SingleSelectionModel());
        cellTable.setRowCount(rowSize, true);
        List<TGC_TableRow> tableData = TGS_ListUtils.of();
        IntStream.range(0, rowSize).forEachOrdered(ri -> tableData.add(new TGC_TableRow(ri, colSize)));
        IntStream.range(0, colSize).forEachOrdered(ci -> {
            var columnHeader = new Header<String>(new TGC_TableHeaderStyled()) {
                @Override
                public String getValue() {
                    return columnTitles.get(ci);
                }
            };
            columnHeader.setUpdater((String value) -> {
                if (onSelectTitle != null) {
                    onSelectTitle.run(value);
                }
            });
            var columnPresenter = new TextColumn<TGC_TableRow>() {
                @Override
                public String getValue(TGC_TableRow t) {
                    return t.cells[ci];
                }
            };
            cellTable.addColumn(columnPresenter, columnHeader);
        });
        return new TGS_Tuple2(cellTable, tableData);
    }

    public static void setColumnWidthsPx(CellTable cellTable, int[] widths) {
        cellTable.setWidth("100%", true);
        IntStream.range(0, widths.length).forEachOrdered(ci -> cellTable.setColumnWidth(ci, TGS_StringUtils.cmn().concat(String.valueOf(widths[ci]), "px")));
    }

    public static void setColumnWidthsPx(CellTable cellTable, int widthsCommon) {
        var widths = new int[cellTable.getColumnCount()];
        Arrays.fill(widths, widthsCommon);
        setColumnWidthsPx(cellTable, widths);
    }

    public static void refreshData(CellTable<TGC_TableRow> cellTable, List<TGC_TableRow> tableData) {
        cellTable.setRowData(tableData);
    }

    public static TGC_TableRow getSelectedRow(CellTable<TGC_TableRow> cellTable) {
        return cellTable.getSelectionModel() instanceof SingleSelectionModel ? (TGC_TableRow) ((SingleSelectionModel) cellTable.getSelectionModel()).getSelectedObject() : null;
    }

    public static void addOnSelectAction(CellTable cellTable, TGS_FuncMTU onSelect) {
        var sm = cellTable.getSelectionModel() instanceof SingleSelectionModel ? (SingleSelectionModel) cellTable.getSelectionModel() : null;
        if (sm != null) {
            sm.addSelectionChangeHandler(e -> {
                var selRow = sm.getSelectedObject();
                if (selRow != null) {
                    onSelect.run();
                }
            });
        }
    }
}
