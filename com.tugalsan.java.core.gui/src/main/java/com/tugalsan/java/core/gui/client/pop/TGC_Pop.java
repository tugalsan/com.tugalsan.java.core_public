package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.*;

import com.tugalsan.java.core.gui.client.browser.*;
import com.tugalsan.java.core.gui.client.dim.*;
import com.tugalsan.java.core.gui.client.dom.*;
import com.tugalsan.java.core.gui.client.focus.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_Pop {

    final private static TGC_Log d = TGC_Log.of(TGC_Pop.class);

    //WARNING: !!! CSS-STATIC-CLASS-NAME !!!
    static class TGC_PopGlassStyle {
    }

    public TGC_Pop(Widget content, TGC_Dimension dim, TGS_FuncMTU onVisible_optional) {
        this.onVisible = onVisible_optional;
        this.content = content;
        this.dim = dim == null ? new TGC_Dimension(null, null, true) : dim;
        changeContentDimension(dim);
        content.setStyleName(d.className());
        widget = new PopupPanel();
        widget.setGlassEnabled(true);
        widget.setGlassStyleName(TGC_PopGlassStyle.class.getSimpleName());
        widget.setWidget(content);
    }
    public final TGS_FuncMTU onVisible;
    public final PopupPanel widget;
    private final TGC_Dimension dim;
    private final Widget content;

    final public void changeContentDimension(TGC_Dimension dim) {//LATER EXTEND PANEL, AFTER SET VISIBLE TRUE
        dim = dim == null ? TGC_Dimension.FULLSCREEN : dim;
        var screenGap = 25;
        var maxWidth = TGC_Dimension.FULLSCREEN.getWidth() - screenGap;
        var maxHeight = TGC_Dimension.FULLSCREEN.getHeight() - screenGap * 2;
        var dim2 = new TGC_Dimension(
                dim.getWidth() > maxWidth ? maxWidth : dim.getWidth(),
                dim.getHeight() > maxHeight ? maxHeight : dim.getHeight(),
                false
        );
        d.ci("changeContentDimension", dim2.getWidth(), dim2.getHeight());
        TGC_DOMUtils.setSize(content.getElement(), dim2.getWidth(), dim2.getHeight());
    }

    public boolean isFullScreen() {
        var b = TGC_DOMUtils.getWidth(content.getElement()) == TGC_Dimension.FULLSCREEN.getWidth() && TGC_DOMUtils.getHeight(content.getElement()) == TGC_Dimension.FULLSCREEN.getHeight();
        d.ci("isFullScreen", b);
        return b;
    }

    public void center() {
        d.ci("center", "init");
        widget.center();
    }

    public void setVisibleFullScreen() {
        d.ci("setVisibleFullScreen", "changeContentDimension");
        changeContentDimension(TGC_Dimension.FULLSCREEN);
        d.ci("setVisibleFullScreen", "changeContentDimension");
        widget.show();
        d.ci("setVisibleFullScreen", "show");
        TGC_DOMUtils.setLeft(widget.getElement(), 0);
        TGC_DOMUtils.setTop(widget.getElement(), 0);
        d.ci("setVisibleFullScreen", "center");
        if (setVisible_focus != null) {
            d.ci("setVisibleFullScreen", "setFocusAfterGUIUpdate");
            TGC_FocusUtils.setFocusAfterGUIUpdate(setVisible_focus);
        }
        if (onVisible != null) {
            d.ci("setVisibleFullScreen", "onVisible");
            onVisible.run();
        }
    }

    public void setVisible(boolean visible) {
        d.ci("setVisible", visible);
        if (visible == isVisible()) {
            d.ci("setVisible", "visible == isVisible()");
            return;
        }
        if (visible) {
            d.ci("setVisible", "changeContentDimension.dim");
            changeContentDimension(dim);
            d.ci("setVisible", "show");
            widget.show();
            d.ci("setVisible", "center");
            widget.center();
            if (setVisible_focus != null) {
                d.ci("setVisibleFullScreen", "setFocusAfterGUIUpdate");
                TGC_FocusUtils.setFocusAfterGUIUpdate(setVisible_focus);
            }
            if (onVisible != null) {
                d.ci("setVisibleFullScreen", "onVisible");
                onVisible.run();
            }
        } else {
            widget.hide();
        }
    }
    public FocusWidget setVisible_focus = null;

    public boolean isVisible() {
        return widget.isShowing();
    }

    public void setVisible_beCeneteredAt(UIObject optional_uiObject) {
        d.ci("setVisible_beCeneteredAt", "uiObject.exists?", optional_uiObject != null, "dim=dim");
        setVisible_beCeneteredAt(optional_uiObject, dim);
    }

    public void setVisible_beCeneteredAt(UIObject optional_uiObject, TGC_Dimension optional_dim) {
        d.ci("setVisible_beCeneteredAt", "uiObject.exists?", optional_uiObject != null, "dim.exists?", optional_dim != null);
        if (optional_uiObject == null || optional_dim == null) {
            d.ci("setVisible_beCeneteredAt", "uiObject == null || dim == null", "call->setVisible(true)");
            setVisible(true);
            return;
        }
        d.ci("setVisible_beCeneteredAt", "calculating center...");
        var uiObjectCX = optional_uiObject.getAbsoluteLeft() + optional_uiObject.getOffsetWidth() / 2;
        var uiObjectCY = optional_uiObject.getAbsoluteTop() + optional_uiObject.getOffsetHeight() / 2;
        var widgetW = optional_dim.getWidth() / 2;
        var widgetH = optional_dim.getHeight() / 2;
        var widgetX = uiObjectCX - widgetW;
        var widgetY = uiObjectCY - widgetH;
        widgetX = widgetX < 0 ? 0 : widgetX;
        widgetY = widgetY < 0 ? 0 : widgetY;

        d.ci("setVisible_beCeneteredAt", "fixing boundary...");
        var dimW = TGC_BrowserDimensionUtils.getDimension();
        while (widgetX + optional_dim.getWidth() > dimW.width) {
            widgetX--;
        }
        while (widgetY + optional_dim.getHeight() > dimW.height) {
            widgetY--;
        }
        d.ci("setVisible_beCeneteredAt", "setVisible(true)");
        setVisible(true);
        d.ci("setVisible_beCeneteredAt", "setPopupPosition");
        widget.setPopupPosition(widgetX, widgetY);
    }
}
