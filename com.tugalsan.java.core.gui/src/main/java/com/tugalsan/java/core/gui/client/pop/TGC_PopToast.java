package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopToast implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopToast.class);

    public TGC_PopToast(TGC_Dimension dim, TGS_FuncMTU onVisible_optional) {
        this.dim = dim;
        this.onVisible = onVisible_optional;
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
    }
    private final TGC_Dimension dim;
    public final TGS_FuncMTU onVisible;

    @Override
    final public void createWidgets() {
        html = new HTML("");
    }
    private HTML html;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
    }

    @Override
    final public void configActions() {
    }

    @Override
    final public void configFocus() {
    }

    @Override
    final public void configLayout() {
        panelPopup = new TGC_Pop(
                html,
                dim,
                onVisible
        );
        panelPopup.widget.setAutoHideEnabled(true);
        panelPopup.widget.setGlassEnabled(false);
    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }

    public void toast(int seconds, String htmlContent) {
        html.setHTML(htmlContent);
        RootPanel.get().add(panelPopup.widget);
        panelPopup.widget.setPopupPositionAndShow((int offsetWidth, int offsetHeight) -> {
            //center
//            int left = (Window.getClientWidth() - offsetWidth) / 2;
//            int top = (Window.getClientHeight() - offsetHeight) / 2;
            panelPopup.widget.setPopupPosition(
                    10,
                    TGC_Dimension.FULLSCREEN.getHeight() - offsetHeight - 3
            );
        });
        TGC_ThreadUtils.run_afterSeconds(exe -> {
            RootPanel.get().remove(panelPopup.widget);
        }, seconds);
    }
}
