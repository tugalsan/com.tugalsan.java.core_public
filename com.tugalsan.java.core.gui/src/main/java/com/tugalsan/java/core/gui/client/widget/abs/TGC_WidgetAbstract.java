package com.tugalsan.java.core.gui.client.widget.abs;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.gui.client.focus.*;
import com.tugalsan.java.core.shape.client.*;
import com.tugalsan.java.core.string.client.*;

abstract public class TGC_WidgetAbstract<T extends Widget> {

//    final private static TGC_Log d = TGC_Log.of(TGC_WidgetAbstract.class);

    public TGC_WidgetAbstract(CharSequence tooltip, T widget) {
        this.widget = widget;
        this.tooltip = tooltip == null ? null : tooltip.toString();
        if (tooltip != null) {
            widget.setTitle(this.tooltip);
        }
    }
    final public T widget;
    final public String tooltip;

    public T getWidget() {
        return widget;
    }

    public boolean isVisible() {
        return widget.isVisible();
    }

    public TGC_WidgetAbstract<T> setVisible(boolean visible) {
        widget.setVisible(visible);
        return this;
    }

    public boolean isEnabled() {
        return widget instanceof FocusWidget ? ((FocusWidget) widget).isEnabled() : true;
    }

    public TGC_WidgetAbstract<T> setEnabled(boolean enable) {
        if (widget instanceof FocusWidget) {
            ((FocusWidget) widget).setEnabled(enable);
        }
        return this;
    }

    public TGC_WidgetAbstract<T> setId(String id) {
        widget.getElement().setId(id);
        return this;
    }

    public String getId() {
        return widget.getElement().getId();
    }

    public TGC_WidgetAbstract setSize(TGS_ShapeDimension<Integer> dimPx) {
        widget.setSize(TGS_StringUtils.cmn().concat(String.valueOf(dimPx.width), "px"), TGS_StringUtils.cmn().concat(String.valueOf(dimPx.height), "px"));
        return this;
    }

    public TGS_ShapeDimension<Integer> getDimensions() {
        return new TGS_ShapeDimension(widget.getElement().getClientWidth(), widget.getElement().getClientHeight());
    }

    public void setFocusAfterGUIUpdate() {
        if (widget instanceof FocusWidget) {
            TGC_FocusUtils.setFocusAfterGUIUpdate((FocusWidget) widget);
        }
    }
}
