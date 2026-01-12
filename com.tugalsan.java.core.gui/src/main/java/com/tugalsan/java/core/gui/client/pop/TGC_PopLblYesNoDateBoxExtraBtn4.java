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
import com.tugalsan.java.core.network.client.TGS_NetworkHTMLUtils;
import com.tugalsan.java.core.string.client.TGS_StringUtils;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopLblYesNoDateBoxExtraBtn4 implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoDateBoxExtraBtn4.class);

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

    public TGC_PopLblYesNoDateBoxExtraBtn4(TGC_Dimension dim,
            CharSequence lblText, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onEsc,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                lblText, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNoDateBoxExtraBtn4(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onEsc,
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
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;
    final private String btnOkText, btnCancelText;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onEsc, onExe;
    final public TGS_FuncMTU onVisible;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHtml);
        dateBox = TGC_DateBoxUtils.create();
        btnAdd1 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd1");
        btnAdd2 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd2");
        btnAdd3 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd3");
        btnAdd4 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd4");
    }
    public PushButton btnEsc, btnExe;
    public HTML label;
    public DateBox dateBox;
    private PushButton btnAdd1, btnAdd2, btnAdd3, btnAdd4;

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
        TGC_ClickUtils.add(btnAdd1, () -> {
            if (onAdd1 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd1?");
            } else {
                onAdd1.run(this);
            }
        });
        TGC_ClickUtils.add(btnAdd2, () -> {
            if (onAdd2 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd2?");
            } else {
                onAdd2.run(this);
            }
        });
        TGC_ClickUtils.add(btnAdd3, () -> {
            if (onAdd3 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd3?");
            } else {
                onAdd3.run(this);
            }
        });
        TGC_ClickUtils.add(btnAdd4, () -> {
            if (onAdd4 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd4?");
            } else {
                onAdd4.run(this);
            }
        });
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(dateBox.getTextBox(), () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnAdd1, () -> {
            if (onAdd1 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd1?");
            } else {
                onAdd1.run(this);
            }
        }, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnAdd1, () -> {
            if (onAdd2 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd2?");
            } else {
                onAdd2.run(this);
            }
        }, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnAdd3, () -> {
            if (onAdd3 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd3?");
            } else {
                onAdd3.run(this);
            }
        }, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnAdd4, ()
                -> {
            if (onAdd4 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd4?");
            } else {
                onAdd4.run(this);
            }
        },
                () -> onEsc.run(this)
        );
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(btnExe, btnExe, btnAdd3, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, btnEsc, btnAdd4, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyUp(dateBox.getTextBox(), new TGS_FocusSides4(null, null, btnEsc, btnAdd1));
        TGC_FocusUtils.addKeyUp(btnAdd1, new TGS_FocusSides4(btnAdd2, btnAdd2, dateBox.getTextBox(), btnAdd3));
        TGC_FocusUtils.addKeyUp(btnAdd2, new TGS_FocusSides4(btnAdd1, btnAdd2, dateBox.getTextBox(), btnAdd4));
        TGC_FocusUtils.addKeyUp(btnAdd3, new TGS_FocusSides4(btnAdd4, btnAdd4, btnAdd1, btnEsc));
        TGC_FocusUtils.addKeyUp(btnAdd4, new TGS_FocusSides4(btnAdd3, btnAdd3, btnAdd2, btnExe));
    }

    @Override
    final public void configLayout() {
        btnAdd1.setVisible(false);
        btnAdd2.setVisible(false);
        btnAdd3.setVisible(false);
        btnAdd4.setVisible(false);
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(5,
                        TGC_PanelLayoutUtils.createVertical(
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnEsc, btnExe),
                                label,
                                dateBox,
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnAdd1, btnAdd2),
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnAdd3, btnAdd4)
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

    //EXTRA BUTTON
    public void btnAddHide(boolean hide1, boolean hide2, boolean hide3, boolean hide4) {
//        d.ci("btnAddHide", hide1, hide2);
        btnAdd1.setVisible(!hide1);
        btnAdd2.setVisible(!hide2);
        btnAdd3.setVisible(!hide3);
        btnAdd4.setVisible(!hide4);
    }

    public void btnAddShowAs(
            String optional_iconClass1Name, String optional_AddBtn1Text,
            String optional_iconClass2Name, String optional_AddBtn2Text,
            String optional_iconClass3Name, String optional_AddBtn3Text,
            String optional_iconClass4Name, String optional_AddBtn4Text
    ) {
        //d.ci("btnAddShowAs", "#0", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        optional_iconClass1Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass1Name);
        optional_AddBtn1Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn1Text);

        optional_iconClass2Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass2Name);
        optional_AddBtn2Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn2Text);

        optional_iconClass3Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass3Name);
        optional_AddBtn3Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn3Text);

        optional_iconClass4Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass4Name);
        optional_AddBtn4Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn4Text);
        //d.ci("btnAddShowAs", "#1", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        btnAddHide(
                optional_iconClass1Name.isEmpty() && optional_AddBtn1Text.isEmpty(),
                optional_iconClass2Name.isEmpty() && optional_AddBtn2Text.isEmpty(),
                optional_iconClass3Name.isEmpty() && optional_AddBtn3Text.isEmpty(),
                optional_iconClass4Name.isEmpty() && optional_AddBtn4Text.isEmpty()
        );
        TGC_ButtonUtils.setIcon(btnAdd1, optional_iconClass1Name, optional_AddBtn1Text);
        TGC_ButtonUtils.setIcon(btnAdd2, optional_iconClass2Name, optional_AddBtn2Text);
        TGC_ButtonUtils.setIcon(btnAdd3, optional_iconClass3Name, optional_AddBtn3Text);
        TGC_ButtonUtils.setIcon(btnAdd4, optional_iconClass4Name, optional_AddBtn4Text);
    }

    public void btnAddSet(
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onAdd1,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onAdd2,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onAdd3,
            TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onAdd4
    ) {
        this.onAdd1 = onAdd1;
        this.onAdd2 = onAdd2;
        this.onAdd3 = onAdd3;
        this.onAdd4 = onAdd4;
    }
    private TGS_FuncMTU_In1<TGC_PopLblYesNoDateBoxExtraBtn4> onAdd1, onAdd2, onAdd3, onAdd4;
}
