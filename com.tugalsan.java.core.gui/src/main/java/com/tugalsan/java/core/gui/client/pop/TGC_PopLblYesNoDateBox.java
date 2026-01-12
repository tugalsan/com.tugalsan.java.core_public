package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.gui.client.click.TGC_ClickUtils;
import com.tugalsan.java.core.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.java.core.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.java.core.gui.client.key.TGC_KeyUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.java.core.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;


import com.tugalsan.java.core.gui.client.widget.TGC_DateBoxUtils;
import com.tugalsan.java.core.network.client.*;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopLblYesNoDateBox implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoTextBox.class);

    public void setLabelHTML(String html) {
        lblHtml = TGS_NetworkHTMLUtils.HTML_SPACE() + html;
        label.setHTML(lblHtml);
    }
    private String lblHtml;

    public void setVisibleDatePicker(boolean visible) {
        TGC_ThreadUtils.run_afterGUIUpdate(() -> {
            if (visible) {
                dateBox.showDatePicker();
            } else {
                dateBox.hideDatePicker();
            }
            d.ci("setVisibleDatePicker", "visible", visible);
        });
//        TGC_DOMUtils.setVisible(dateBox.getDatePicker().getElement(), visible);//TODO CAUSES A GLASS PANEL ON LEFT TOP
    }

    public TGC_PopLblYesNoDateBox(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBox> onEsc,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                lblHtml, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNoDateBox(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBox> onEsc,
            TGS_FuncMTU onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHtml = lblHtml.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onVisible = () -> {
            setVisibleDatePicker(false);
            if (onVisible_optional != null) {
                onVisible_optional.run();
            }
        };
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = dateBox.getTextBox();
    }
    private String iconClassExe, iconClassEsc;
    private TGC_Dimension dim;
    final private String btnOkText, btnCancelText;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoDateBox> onEsc, onExe;
    final public TGS_FuncMTU onVisible;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHtml);
        dateBox = TGC_DateBoxUtils.create();
    }
    public PushButton btnEsc, btnExe;
    public HTML label;
    public DateBox dateBox;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
        TGC_ClickUtils.add(dateBox.getTextBox(), () -> setVisibleDatePicker(true));
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(dateBox.getTextBox(), () -> onExe.run(this), () -> onEsc.run(this));
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(null, btnExe, null, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, dateBox.getTextBox(), null, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyUp(dateBox.getTextBox(), new TGS_FocusSides4(null, null, btnEsc, null));
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(3,
                        TGC_PanelLayoutUtils.createVertical(TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnEsc, btnExe),
                                label,
                                dateBox
                        ),
                        new HTML("")
                ),
                dim, onVisible
        );
    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
