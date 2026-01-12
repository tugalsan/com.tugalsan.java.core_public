package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.gui.client.click.*;
import com.tugalsan.java.core.gui.client.focus.*;
import com.tugalsan.java.core.gui.client.key.*;
import com.tugalsan.java.core.gui.client.dim.*;
import com.tugalsan.java.core.gui.client.widget.*;
import com.tugalsan.java.core.gui.client.panel.*;
import com.tugalsan.java.core.icon.client.*;
import com.tugalsan.java.core.network.client.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_PopLblYesNoTextBoxExtraBtn2 implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoTextBoxExtraBtn2.class);

    public void setLabelHTML(String html) {
        lblHtml = TGS_NetworkHTMLUtils.HTML_SPACE() + html;
        label.setHTML(lblHtml);
    }
    private String lblHtml;

    public TGC_PopLblYesNoTextBoxExtraBtn2(TGC_Dimension dim,
            CharSequence lblText, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onEsc,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                lblText, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }
    final private String btnOkText, btnCancelText;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onEsc, onExe;
    final public TGS_FuncMTU onVisible;

    public TGC_PopLblYesNoTextBoxExtraBtn2(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onEsc,
            TGS_FuncMTU onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHtml = lblHtml.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = textBox;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHtml);
        textBox = new TextBox();
        btnAdd1 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd1");
        btnAdd2 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd2");
    }
    public PushButton btnEsc, btnExe;
    public HTML label;
    public TextBox textBox;
    private PushButton btnAdd1, btnAdd2;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
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
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(textBox, () -> onExe.run(this), () -> onEsc.run(this));
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
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(null, btnExe, null, textBox));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, textBox, null, textBox));
        TGC_FocusUtils.addKeyUp(textBox, new TGS_FocusSides4(null, null, btnEsc, btnAdd1));
        TGC_FocusUtils.addKeyUp(btnAdd1, new TGS_FocusSides4(null, btnAdd2, textBox, null));
        TGC_FocusUtils.addKeyUp(btnAdd2, new TGS_FocusSides4(btnAdd1, null, textBox, null));
    }

    @Override
    final public void configLayout() {
        btnAdd1.setVisible(false);
        btnAdd2.setVisible(false);
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(
                        4,
                        TGC_PanelLayoutUtils.createVertical(
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnEsc, btnExe),
                                label,
                                textBox,
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnAdd1, btnAdd2)
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
    public void btnAddHide(boolean hide1, boolean hide2) {
        d.ci("btnAddHide", hide1, hide2);
        btnAdd1.setVisible(!hide1);
        btnAdd2.setVisible(!hide2);
    }

    public void btnAddShowAs(String optional_iconClass1Name, String optional_AddBtn1Text, String optional_iconClass2Name, String optional_AddBtn2Text) {
        d.ci("btnAddShowAs", "#0", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        optional_iconClass1Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass1Name);
        optional_AddBtn1Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn1Text);
        optional_iconClass2Name = TGS_StringUtils.cmn().toEmptyIfNull(optional_iconClass2Name);
        optional_AddBtn2Text = TGS_StringUtils.cmn().toEmptyIfNull(optional_AddBtn2Text);
        d.ci("btnAddShowAs", "#1", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        TGC_ButtonUtils.setIcon(btnAdd1, optional_iconClass1Name, optional_AddBtn1Text);
        TGC_ButtonUtils.setIcon(btnAdd2, optional_iconClass2Name, optional_AddBtn2Text);
        d.ci("btnAddShowAs", "#2", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        btnAddHide(optional_iconClass1Name.isEmpty() && optional_AddBtn1Text.isEmpty(), optional_iconClass2Name.isEmpty() && optional_AddBtn2Text.isEmpty());
    }

    public void btnAddSet(TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onAdd1, TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onAdd2) {
        this.onAdd1 = onAdd1;
        this.onAdd2 = onAdd2;
    }
    private TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxExtraBtn2> onAdd1, onAdd2;
}
