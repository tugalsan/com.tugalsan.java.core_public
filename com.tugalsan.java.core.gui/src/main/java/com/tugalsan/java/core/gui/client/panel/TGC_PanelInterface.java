package com.tugalsan.java.core.gui.client.panel;

import com.google.gwt.user.client.ui.Widget;

public interface TGC_PanelInterface {

    public String getBrowserTitle();
    
    public void loadParams();

    public void createWidgets();

    public void createPops();

    public void configInit();

    public void configActions();

    public void configFocus();

    public void configLayout();

    public Widget getWidget();
}
