package com.tugalsan.java.core.gui.client.widget.abs;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.stream.IntStream;
import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.panel.TGC_PanelAbsoluteUtils;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;

public final class TGC_ScrollPanel extends ScrollPanel {

    public TGC_ScrollPanel(TGC_Dimension dim) {
        content = TGC_PanelAbsoluteUtils.create(null);
        setWidget(content);
        setSize(dim);
    }

    public void setSize(TGC_Dimension dim) {
        this.dim = dim;
        TGC_DOMUtils.setSize(getWidget().getElement(), dim.getWidth(), dim.getHeight());
    }
    
    public TGC_Dimension getSize(){
        return dim;
    }
    private TGC_Dimension dim;

    public AbsolutePanel getContent() {
        return content;
    }
    private final AbsolutePanel content;

    public void addWidget_enlargeContentSize(Widget widget, int x, int y, int w, int h) {
        TGC_PanelAbsoluteUtils.setWidget(content, widget, TGS_ShapeRectangle.of(x, y, w, h));
        enlargeContentSize();
    }

    private void enlargeContentSize() {
        var wc = content.getWidgetCount();
        IntStream.range(0, wc).forEach(i -> {
            var grandChildWidget = content.getWidget(i);
            var gcx = TGC_DOMUtils.getLeft(grandChildWidget.getElement());
            var gcy = TGC_DOMUtils.getTop(grandChildWidget.getElement());
            var gcw = TGC_DOMUtils.getWidth(grandChildWidget.getElement());
            var gch = TGC_DOMUtils.getHeight(grandChildWidget.getElement());
            var w = TGC_DOMUtils.getWidth(content.getElement());
            var h = TGC_DOMUtils.getHeight(content.getElement());
            if (gcx + gcw > w) {
                w = gcx + gcw;
            }
            if (gcy + gch > h) {
                h = gcy + gch;
            }
            TGC_DOMUtils.setSize(content.getElement(), w, h);
        });
    }
}
