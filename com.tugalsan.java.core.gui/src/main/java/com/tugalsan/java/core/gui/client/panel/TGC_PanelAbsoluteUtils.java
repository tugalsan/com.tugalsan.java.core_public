package com.tugalsan.java.core.gui.client.panel;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.widget.abs.TGC_ScrollPanel;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;

public class TGC_PanelAbsoluteUtils {

    private TGC_PanelAbsoluteUtils() {

    }

    final private static TGC_Log d = TGC_Log.of(TGC_PanelAbsoluteUtils.class);

    public static AbsolutePanel wrap(TGC_ScrollPanel scroll) {
        var dim = scroll.getSize();
        var rect = TGS_ShapeRectangle.of(0, 0, dim.getWidth(), dim.getHeight());
        var abs = TGC_PanelAbsoluteUtils.create(dim);
        TGC_PanelAbsoluteUtils.setWidget(abs, scroll, rect);
        return abs;
    }

    public static AbsolutePanel create(TGC_Dimension dim) {
        var panel = new AbsolutePanel();
        setSize(panel, dim);
        return panel;
    }

    public static void addElementById(AbsolutePanel panel, CharSequence elementId, TGS_ShapeRectangle<Integer> rect) {
        var element = Document.get().getElementById(elementId.toString());
        TGC_DOMUtils.setPositionAbsolute(element);
        element.getStyle().setLeft(rect.x, Style.Unit.PX);
        element.getStyle().setTop(rect.y, Style.Unit.PX);
        element.getStyle().setWidth(rect.width, Style.Unit.PX);
        element.getStyle().setHeight(rect.height, Style.Unit.PX);
        panel.getElement().appendChild(element);
    }

    private static void addWidget(AbsolutePanel panel, Widget widget, TGS_ShapeRectangle<Integer> rect) {
        if (panel == null) {
            d.ce("addWidget", "panel == null");
            return;
        }
        if (widget == null) {
            d.ce("addWidget", "widget == null");
            return;
        }
        if (rect == null) {
            d.ce("addWidget", "rect == null");
            return;
        }
        d.ci("addWidget", "#0");
        TGC_DOMUtils.setPositionAbsolute(widget.getElement());
        d.ci("addWidget", "#1");
        widget.setSize(rect.width + "px", rect.height + "px");
        d.ci("addWidget", "#1");
        panel.add(widget, rect.x, rect.y);
        d.ci("addWidget", "#1");

        var tolerans = 5;
        var newPanelWidth = rect.x + rect.width + tolerans;
        var newPanelHeight = rect.y + rect.height + tolerans;
        TGS_FuncMTCUtils.run(() -> {
            var oldPanelWidth = TGC_DOMUtils.getWidth(panel.getElement());
            var oldPanelHeight = TGC_DOMUtils.getHeight(panel.getElement());
            if (oldPanelWidth < newPanelWidth) {
                oldPanelWidth = newPanelWidth;
            }
            if (oldPanelHeight < newPanelHeight) {
                oldPanelHeight = newPanelHeight;
            }
            TGC_DOMUtils.setSize(panel.getElement(), oldPanelWidth, oldPanelHeight);
        }, e -> {
            TGC_DOMUtils.setSize(panel.getElement(), newPanelWidth, newPanelHeight);
        });
//        addElement(widget.getElement(), x, y, width, height);//button not working
    }

    public static void setWidget(AbsolutePanel panel, Widget widget, TGS_ShapeRectangle<Integer> rect) {
        d.ci("setWidget", "#0");
        removeWidget(panel, widget);
        d.ci("setWidget", "#1");
        addWidget(panel, widget, rect);
        d.ci("setWidget", "#2");
    }

    public static void removeWidget(AbsolutePanel panel, Widget widget) {
        if (panel == null) {
            d.ce("removeWidget", "panel == null");
            return;
        }
        if (widget == null) {
            d.ce("removeWidget", "widget == null");
            return;
        }
        d.ci("removeWidget", "#0");
        panel.remove(widget);
        d.ci("removeWidget", "#0");
    }

    public static void removeAll(AbsolutePanel panel) {
        panel.clear();
    }

    public static boolean contains(AbsolutePanel panel, FocusWidget fw) {
        return panel.getWidgetIndex(fw) != -1;
    }

    public static void setSize(AbsolutePanel panel, TGC_Dimension dim) {
        if (dim == null) {
            panel.setSize("100%", "100%");
        } else {
            panel.setSize(dim.getWidth() + "px", dim.getHeight() + "px");
        }
    }
}
