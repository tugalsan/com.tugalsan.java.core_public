package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.PushButton;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.gui.client.browser.TGC_BrowserWindowUtils;
import com.tugalsan.java.core.gui.client.click.TGC_ClickUtils;
import com.tugalsan.java.core.gui.client.key.TGC_KeyUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.java.core.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;
import com.tugalsan.java.core.log.client.TGC_Log;


import com.tugalsan.java.core.url.client.TGS_Url;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopFrame implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopFrame.class);

    public TGC_PopFrame(TGC_Dimension dim,
            TGS_Url url_optional, CharSequence btnOkText, CharSequence btnTabText,
            TGS_FuncMTU_In1<TGC_PopFrame> onExe,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                url_optional, btnOkText, btnTabText,
                onExe, onVisible_optional,
                null
        );
    }

    public TGC_PopFrame(TGC_Dimension dim,
            TGS_Url url_optional, CharSequence btnOkText, CharSequence btnTabText,
            TGS_FuncMTU_In1<TGC_PopFrame> onExe,
            TGS_FuncMTU onVisible_optional, CharSequence iconClassExe_optional) {
        this.dim = dim;
        this.url = url_optional == null ? new TGS_Url("") : url_optional;
        this.btnOkText = btnOkText.toString();
        this.btnTabText = btnTabText.toString();
        this.onExe = onExe;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = btnExe;
    }
    private String iconClassExe;
    private TGC_Dimension dim;
    final private TGS_Url url;
    final private String btnOkText;
    final private String btnTabText;
    final public TGS_FuncMTU_In1<TGC_PopFrame> onExe;
    final private TGS_FuncMTU onVisible;

    @Override
    final public void createWidgets() {
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        btnTab = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_UPLOAD() : iconClassExe, btnTabText);
        frame = new Frame(url.toString());
    }
    public Frame frame;
    public PushButton btnExe, btnTab;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onExe.run(this));
        TGS_FuncMTU onTab = () -> {
            TGC_BrowserWindowUtils.openNew(url);
            onExe.run(this);
        };
        TGC_ClickUtils.add(btnTab, onTab);
        TGC_KeyUtils.add(btnTab, onTab, () -> onExe.run(this));
    }

    @Override
    final public void configFocus() {
    }

    @Override
    final public void configLayout() {
        frame.setWidth("100%");
        frame.setHeight("100%");
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(1,
                        TGC_PanelLayoutUtils.createLayoutPair(btnExe, btnTab),
                        frame
                ),
                dim, onVisible
        );
    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }

    public void setUrl(TGS_Url newUrl) {
        d.ci("setUrl", newUrl);
        url.setUrl(newUrl.getUrl());
        frame.setUrl(url.getUrl().toString());
    }
}
