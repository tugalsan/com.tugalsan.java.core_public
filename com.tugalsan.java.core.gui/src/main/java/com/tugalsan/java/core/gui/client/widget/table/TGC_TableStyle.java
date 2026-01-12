package com.tugalsan.java.core.gui.client.widget.table;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.OutlineStyle;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.AbstractCellTable.Style;

//http://federico.defaveri.org/projects/CustomTableStyle/CustomTableStyle.html
public class TGC_TableStyle<T> extends AbstractCellTableBuilder<T> {

    interface CustomResources extends CellTable.Resources {

        @ClientBundle.Source("TGC_TableStyle.css")
        @Override
        CustomStyle cellTableStyle();
    }

    interface CustomStyle extends CellTable.Style {
    }

    protected static CellTable.Resources DEFAULT_RESOURCE = GWT.create(CellTable.Resources.class);
    protected static CustomResources CUSTOM_RESOURCE = GWT.create(CustomResources.class);

    /**
     * Class copied from {@link CellTable} where it is private...
     *
     */
    public static class StyleAdapter implements AbstractCellTable.Style {

        private final CellTable.Style style;

        public StyleAdapter(CellTable.Style style) {
            this.style = style;
        }

        @Override
        public String cell() {
            return style.cellTableCell();
        }

        @Override
        public String evenRow() {
            return style.cellTableEvenRow();
        }

        @Override
        public String evenRowCell() {
            return style.cellTableEvenRowCell();
        }

        @Override
        public String firstColumn() {
            return style.cellTableFirstColumn();
        }

        @Override
        public String firstColumnFooter() {
            return style.cellTableFirstColumnFooter();
        }

        @Override
        public String firstColumnHeader() {
            return style.cellTableFirstColumnHeader();
        }

        @Override
        public String footer() {
            return style.cellTableFooter();
        }

        @Override
        public String header() {
            return style.cellTableHeader();
        }

        @Override
        public String hoveredRow() {
            return style.cellTableHoveredRow();
        }

        @Override
        public String hoveredRowCell() {
            return style.cellTableHoveredRowCell();
        }

        @Override
        public String keyboardSelectedCell() {
            return style.cellTableKeyboardSelectedCell();
        }

        @Override
        public String keyboardSelectedRow() {
            return style.cellTableKeyboardSelectedRow();
        }

        @Override
        public String keyboardSelectedRowCell() {
            return style.cellTableKeyboardSelectedRowCell();
        }

        @Override
        public String lastColumn() {
            return style.cellTableLastColumn();
        }

        @Override
        public String lastColumnFooter() {
            return style.cellTableLastColumnFooter();
        }

        @Override
        public String lastColumnHeader() {
            return style.cellTableLastColumnHeader();
        }

        @Override
        public String oddRow() {
            return style.cellTableOddRow();
        }

        @Override
        public String oddRowCell() {
            return style.cellTableOddRowCell();
        }

        @Override
        public String selectedRow() {
            return style.cellTableSelectedRow();
        }

        @Override
        public String selectedRowCell() {
            return style.cellTableSelectedRowCell();
        }

        @Override
        public String sortableHeader() {
            return style.cellTableSortableHeader();
        }

        @Override
        public String sortedHeaderAscending() {
            return style.cellTableSortedHeaderAscending();
        }

        @Override
        public String sortedHeaderDescending() {
            return style.cellTableSortedHeaderDescending();
        }

        @Override
        public String widget() {
            return style.cellTableWidget();
        }
    }

    private String evenRowStyle;
    private String oddRowStyle;
    private String selectedRowStyle;
    private String cellStyle;
    private String evenCellStyle;
    private String oddCellStyle;
    private String firstColumnStyle;
    private String lastColumnStyle;
    private String selectedCellStyle;

    public TGC_TableStyle(AbstractCellTable<T> cellTable) {
        super(cellTable);

        // Cache styles for faster access.
        var style = cellTable.getResources().style();
        setStyle(style);
    }

    final public void setStyle(Style style) {
        evenRowStyle = style.evenRow();
        oddRowStyle = style.oddRow();
        selectedRowStyle = " " + style.selectedRow();
        cellStyle = style.cell();
        evenCellStyle = " " + style.evenRowCell();
        oddCellStyle = " " + style.oddRowCell();
        firstColumnStyle = " " + style.firstColumn();
        lastColumnStyle = " " + style.lastColumn();
        selectedCellStyle = " " + style.selectedRowCell();
    }

    @Override
    public void buildRowImpl(T rowValue, int absRowIndex) {

        // Calculate the row styles.
        var selectionModel = cellTable.getSelectionModel();
        var isSelected = (selectionModel == null || rowValue == null) ? false : selectionModel.isSelected(rowValue);
        var isEven = absRowIndex % 2 == 0;
        var trClasses = new StringBuilder(isEven ? evenRowStyle : oddRowStyle);
        if (isSelected) {
            trClasses.append(selectedRowStyle);
        }

        // Add custom row styles.
        RowStyles<T> rowStyles = cellTable.getRowStyles();
        if (rowStyles != null) {
            String extraRowStyles = rowStyles.getStyleNames(rowValue, absRowIndex);
            if (extraRowStyles != null) {
                trClasses.append(" ").append(extraRowStyles);
            }
        }

        // Build the row.
        var tr = startRow();
        tr.className(trClasses.toString());

        // Build the columns.
        var columnCount = cellTable.getColumnCount();
        for (var curColumn = 0; curColumn < columnCount; curColumn++) {
            var column = cellTable.getColumn(curColumn);
            // Create the cell styles.
            var tdClasses = new StringBuilder(cellStyle);
            tdClasses.append(isEven ? evenCellStyle : oddCellStyle);
            if (curColumn == 0) {
                tdClasses.append(firstColumnStyle);
            }
            if (isSelected) {
                tdClasses.append(selectedCellStyle);
            }
            // The first and last column could be the same column.
            if (curColumn == columnCount - 1) {
                tdClasses.append(lastColumnStyle);
            }

            // Add class names specific to the cell.
            var context = new Context(absRowIndex, curColumn, cellTable.getValueKey(rowValue));
            var cellStyles = column.getCellStyleNames(context, rowValue);
            if (cellStyles != null) {
                tdClasses.append(" ").append(cellStyles);
            }

            // Build the cell.
            var hAlign = column.getHorizontalAlignment();
            var vAlign = column.getVerticalAlignment();
            var td = tr.startTD();
            td.className(tdClasses.toString());
            if (hAlign != null) {
                td.align(hAlign.getTextAlignString());
            }
            if (vAlign != null) {
                td.vAlign(vAlign.getVerticalAlignString());
            }

            // Add the inner div.
            var div = td.startDiv();
            div.style().outlineStyle(OutlineStyle.NONE).endStyle();

            // Render the cell into the div.
            renderCell(div, context, column, rowValue);

            // End the cell.
            div.endDiv();
            td.endTD();
        }

        // End the row.
        tr.endTR();
    }
}
