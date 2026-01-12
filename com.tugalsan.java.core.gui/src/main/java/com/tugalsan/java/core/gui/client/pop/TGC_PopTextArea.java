package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.TextArea;
import com.tugalsan.java.core.gui.client.editable.TGC_EditableUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;


public class TGC_PopTextArea implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopTextArea.class);

    final private String initText;
    final public TGS_FuncMTU onVisible;

    public TGC_PopTextArea(TGC_Dimension dim, CharSequence initText, TGS_FuncMTU onVisible_optional) {
        this.dim = dim;
        this.initText = initText.toString();
        this.onVisible = onVisible_optional;
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = textArea;
    }

    private TGC_Pop panelPopup;
    public TextArea textArea;
    private final TGC_Dimension dim;

    @Override
    final public void createWidgets() {
        textArea = new TextArea();
    }

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
        TGC_EditableUtils.set(textArea, false);
        textArea.setText(initText);
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
                TGC_PanelLayoutUtils.createDock(
                        textArea
                ),
                dim, onVisible
        );
    }

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
