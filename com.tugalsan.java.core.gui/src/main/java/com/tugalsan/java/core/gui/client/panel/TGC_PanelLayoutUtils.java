package com.tugalsan.java.core.gui.client.panel;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.gui.client.browser.*;
import com.tugalsan.java.core.gui.client.dom.*;
import com.tugalsan.java.core.tuple.client.*;
import com.tugalsan.java.core.shape.client.*;
import com.tugalsan.java.core.string.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_PanelLayoutUtils {

    private TGC_PanelLayoutUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_PanelLayoutUtils.class);
    public static int MAX_GRID_WIDTH() {
        return 500;
    }

    private static double DOCK_ROW_HEIGHT() {
        return 2.5d;
    }

    public static void setSizePx(Widget widget, int width, int height) {
        widget.setSize(TGS_StringUtils.cmn().concat(String.valueOf(width), "px"), TGS_StringUtils.cmn().concat(String.valueOf(height), "px"));
    }

    public static void setSizePx(Widget widget, TGS_ShapeDimension<Integer> dimension) {
        TGC_PanelLayoutUtils.setSizePx(widget, dimension.width, dimension.height);
    }

    public static void add(AbsolutePanel pa, Element element, int x, int y) {
        TGC_DOMUtils.setPositionAbsolute(element);
        element.getStyle().setLeft(x, Style.Unit.PX);
        element.getStyle().setTop(y, Style.Unit.PX);
        pa.getElement().appendChild(element);
    }

    public static void addById(AbsolutePanel pa, CharSequence elementId, int x, int y) {
        add(pa, Document.get().getElementById(elementId.toString()), x, y);
    }

    public static void add(AbsolutePanel pa, Widget widget, int x, int y) {
        add(pa, widget.getElement(), x, y);
    }

    public static SplitLayoutPanel createSplitRight(int size, Widget right, Widget center) {
        var sp = new SplitLayoutPanel();
        sp.addEast(right, size);
        sp.add(center);
        return sp;
    }

    public static DockLayoutPanel createDock(Widget center) {
        var dp = new DockLayoutPanel(Style.Unit.EM);
        dp.add(center);
        return dp;
    }

    public static DockLayoutPanel createDockNorth(int rowCount, Widget north, Widget center) {
        var dp = new DockLayoutPanel(Style.Unit.EM);
        dp.addNorth(north, DOCK_ROW_HEIGHT() * rowCount);
        if (center != null) {
            dp.add(center);
        }
        return dp;
    }

    public static DockLayoutPanel createDockNorthSouth(int rowCountNorth, Widget north, int rowCountSouth, Widget south, Widget center) {
        var dp = new DockLayoutPanel(Style.Unit.EM);
        dp.addNorth(north, DOCK_ROW_HEIGHT() * rowCountNorth);
        dp.addSouth(south, DOCK_ROW_HEIGHT() * rowCountSouth);
        dp.add(center);
        return dp;
    }

    public static HorizontalPanel createHorizontal(Widget childRest, Widget... childeren) {
        TGS_Tuple2<Widget, Integer>[] childerenAndWidths = new TGS_Tuple2[childeren.length];
        IntStream.range(0, childeren.length).forEachOrdered(i -> childerenAndWidths[i] = new TGS_Tuple2(childeren[i], null));
        return createHorizontal(10, false, childRest, childerenAndWidths);
    }

    public static HorizontalPanel createHorizontal(Widget childRest, TGS_Tuple2<Widget, Integer>... childerenAndWidths) {
        return createHorizontal(10, false, childRest, childerenAndWidths);
    }

    public static HorizontalPanel createHorizontal(int spacingPx, boolean top, Widget childRest, TGS_Tuple2<Widget, Integer>... childerenAndWidths) {
        var hp = new HorizontalPanel();
        hp.setWidth("100%");
        hp.setVerticalAlignment(top ? HasVerticalAlignment.ALIGN_TOP : HasVerticalAlignment.ALIGN_MIDDLE);
        hp.setSpacing(spacingPx);
        Arrays.stream(childerenAndWidths).forEachOrdered(w -> {
            hp.add(w.value0);
            if (w.value1 != null) {
                hp.setCellWidth(w.value0, w.value1 + "px");
            }
        });
        if (childRest != null) {
            childRest.setWidth("100%");
            hp.setCellWidth(childRest, "100%");
        }
        return hp;
    }

    public static VerticalPanel createVertical(List<Widget> childeren) {
        var childs = new Widget[childeren.size()];
        IntStream.range(0, childs.length).forEach(i -> childs[i] = childeren.get(i));
        return createVertical(10, true, childs);
    }

    public static VerticalPanel createVertical(Widget... childeren) {
        return createVertical(10, true, childeren);
    }

    public static VerticalPanel createVertical(int spacingPx, boolean center, Widget... childeren) {
        var vp = new VerticalPanel();
        vp.setHorizontalAlignment(center ? HasHorizontalAlignment.ALIGN_CENTER : HasHorizontalAlignment.ALIGN_LEFT);
        vp.setSpacing(spacingPx);
        Arrays.stream(childeren).forEachOrdered(w -> vp.add(w));
        return vp;
    }

    public static Grid createGridPair(Integer maxWidth, Integer percentLeft, Widget wLeft, Widget wRight) {
        return createGridPair(maxWidth, percentLeft, wLeft, wRight, true);
    }

    public static Grid createGridPair(Integer maxWidth, Integer percentLeft, Widget wLeft, Widget wRight, boolean makeLabelsAllignRight) {
        var widthPercents = new Integer[2];
        widthPercents[0] = percentLeft == null || percentLeft < 0 ? 50 : percentLeft;
        widthPercents[1] = 100 - widthPercents[0];
        Widget[] widgets = {wLeft, wRight};
        return TGC_PanelLayoutUtils.createGrid(maxWidth, widthPercents, widgets, makeLabelsAllignRight);
    }

    public static LayoutPanel createLayoutPair(Widget wLeft, Widget wRight) {
        return TGC_PanelLayoutUtils.createLayoutPair(wLeft, wRight, 50);
    }

    public static LayoutPanel createLayoutPair(Widget wLeft, Widget wRight, int percentLeft) {
        var pl = new LayoutPanel();
        wLeft.setWidth("95%");
        wRight.setWidth("95%");
        TGC_PanelLayoutUtils.add(pl, wLeft);
        TGC_PanelLayoutUtils.add(pl, wRight);
        setLayoutPair(pl, wLeft, wRight, percentLeft);
        return pl;
    }

    public static void setLayoutPair(LayoutPanel pl, Widget wLeft, Widget wRight, int percentLeft) {
        TGC_PanelLayoutUtils.setWidthLeftPercent(pl, wLeft, 0, percentLeft);
        TGC_PanelLayoutUtils.setWidthRightPercent(pl, wRight, 0, 100 - percentLeft);
    }

    public static void add(LayoutPanel p, Widget w) {
        p.add(w);
    }

    public static void setWidthLeftPercent(LayoutPanel p, Widget w, int perStart, int perEnd) {
        p.setWidgetLeftWidth(w, perStart, Style.Unit.PCT, perEnd, Style.Unit.PCT);
    }

    public static void setWidthRightPercent(LayoutPanel p, Widget w, int perStart, int perEnd) {
        p.setWidgetRightWidth(w, perStart, Style.Unit.PCT, perEnd, Style.Unit.PCT);
    }

    public static void setWidthLeftFont(LayoutPanel p, Widget w, int perStart, int perEnd) {
        p.setWidgetLeftWidth(w, perStart, Style.Unit.EM, perEnd, Style.Unit.EM);
    }

    public static void setWidthRightFont(LayoutPanel p, Widget w, int perStart, int perEnd) {
        p.setWidgetRightWidth(w, perStart, Style.Unit.EM, perEnd, Style.Unit.EM);
    }

    public static void cls(FlowPanel p) {
        p.clear();
    }

    public static void setWidgets(Grid grid, int rowIdx, Widget... ws) {
        IntStream.range(0, ws.length).forEachOrdered(ci -> grid.setWidget(rowIdx, ci, ws[ci]));
    }

    public static Grid createGrid(Integer maxWidth, Integer[] columnPercent, Widget[] widgets, boolean makeLabelsAllignRight) {
        var cssWidth = maxWidth == null ? "100%" : (TGC_BrowserDimensionUtils.getDimension().width > maxWidth ? maxWidth + "px" : "100%");
        var rowSize = widgets.length / columnPercent.length;
        var colSize = columnPercent.length;
        var widget = new Grid(rowSize, colSize);
        widget.setWidth(cssWidth);
        IntStream.range(0, colSize).forEachOrdered(ci -> widget.getColumnFormatter().setWidth(ci, columnPercent[ci] + "%"));
        IntStream.range(0, widgets.length).forEachOrdered(wi -> {
            var w = widgets[wi];
            var rowI = wi / colSize;
            var colI = wi % colSize;
            if (w == null) {
                return;
            }
            widget.setWidget(rowI, colI, w);
            w.setWidth("100%");
            if (makeLabelsAllignRight && w instanceof HTML) {
                w.getElement().getStyle().setTextAlign(Style.TextAlign.RIGHT);
            }
        });
        return widget;
    }
}
