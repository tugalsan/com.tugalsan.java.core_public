package com.tugalsan.java.core.gui.client.panel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.tugalsan.java.core.gui.client.browser.TGC_BrowserNavigatorUtils;

@Deprecated //for not scaling apps
public class TGC_PanelRootScaler {

    public TGC_PanelRootScaler(int widthDefault) {
        this.widthDefault = widthDefault;
    }
    public int widthDefault;

    private double scale(int currentWidth) {
        return Math.min(currentWidth, widthDefault) / (double) widthDefault;
    }

    private void setProperty(String name, String value) {
        RootPanel.get().getElement().getStyle().setProperty(name, value);
    }

    public void zoomBody(int currentWidth) {
        var scale = scale(currentWidth);
        if (TGC_BrowserNavigatorUtils.firefox()) {
            setProperty("transform", "scale(" + scale + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            setProperty("marginLeft", 50 - 50 / scale + "%"); //$NON-NLS-1$
            setProperty("marginTop", Window.getClientHeight() * (scale - 1) / 2 + "px");
            setProperty("width", 100 / scale + "%");
            return;
        }
        setProperty("zoom", String.valueOf(scale(currentWidth)));
    }
}
