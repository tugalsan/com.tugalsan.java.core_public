package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import java.util.List;
import java.util.stream.IntStream;
import com.tugalsan.java.core.gui.client.click.TGC_ClickUtils;
import com.tugalsan.java.core.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.java.core.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.java.core.gui.client.key.TGC_KeyUtils;
import com.tugalsan.java.core.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.java.core.gui.client.widget.TGC_ListBoxUtils;
import com.tugalsan.java.core.gui.client.browser.TGC_BrowserNavigatorUtils;
import com.tugalsan.java.core.gui.client.dim.TGC_Dimension;
import com.tugalsan.java.core.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;
import java.util.ArrayList;

public class TGC_PopLblYesNoTextBoxListBox implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoCheckListBox.class);
    final private String btnOkText, btnCancelText, btnSearchText, lblSearchText, lblListText, tbInitText;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onEsc, onExe, onSearch, onListChange;
    final public List<String> listBoxInitContent;
    final private TGS_FuncMTU onVisible;

    public TGC_PopLblYesNoTextBoxListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblSearchText, CharSequence tbInitValue, CharSequence lblListText,
            CharSequence btnOkText, CharSequence btnCancelText, CharSequence btnSearchText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onEsc,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onSearch,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onListChange,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                listBoxContent_optional,
                lblSearchText, tbInitValue, lblListText,
                btnOkText, btnCancelText, btnSearchText,
                onExe, onEsc, onSearch, onListChange, onVisible_optional,
                null, null, null);
    }

    public TGC_PopLblYesNoTextBoxListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblSearchText, CharSequence tbInitText, CharSequence lblListText,
            CharSequence btnOkText, CharSequence btnCancelText, CharSequence btnSearchText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onEsc,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onSearch,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextBoxListBox> onListChange,
            TGS_FuncMTU onVisible_optional,
            CharSequence iconClassExe_optional,
            CharSequence iconClassEsc_optional,
            CharSequence iconClassSearch_optional) {
        this.dim = dim;
        this.listBoxInitContent = listBoxContent_optional;
        this.lblSearchText = lblSearchText.toString();
        this.tbInitText = tbInitText.toString();
        this.lblListText = lblListText.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.btnSearchText = btnSearchText.toString();
        this.onExe = onExe;
        this.onEsc = onEsc;
        this.onSearch = onSearch;
        this.onListChange = onListChange == null ? TGS_FuncMTU_In1.empty : onListChange;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        this.iconClassSearch = iconClassSearch_optional == null ? null : iconClassSearch_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = tb;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc, iconClassSearch;

    public void selectLastItem() {
        listBox.setSelectedIndex(listBox.getItemCount() - 1);
    }

    @Override
    final public void createWidgets() {
        this.btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        this.btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        this.lblSearch = new HTML(lblSearchText);
        this.tb = new TextBox();
        this.tb.setText(tbInitText);
        this.btnSearch = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_SEARCH() : iconClassSearch, btnSearchText);
        this.lblList = new HTML(lblListText);
        this.listBox = TGC_ListBoxUtils.create(false);
    }
    public HTML lblSearch, lblList;
    public PushButton btnExe, btnEsc, btnSearch;
    public TextBox tb;
    public ListBox listBox;

    @Override
    final public void createPops() {
    }
    private TGC_Pop panelPopup;

    @Override
    final public void configInit() {
        if (listBoxInitContent != null) {
            listBoxInitContent.stream().forEachOrdered(s -> listBox.addItem(s));
            listBox.setSelectedIndex(0);
        }
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(btnSearch, () -> onSearch.run(this));
        TGC_ClickUtils.add(listBox, TGS_FuncMTU.empty, () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(tb, () -> onSearch.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnSearch, () -> onSearch.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(listBox, () -> onExe.run(this), () -> onEsc.run(this), () -> onListChange.run(this), () -> onListChange.run(this));
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(null, btnExe, null, tb));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, null, null, btnSearch));
        TGC_FocusUtils.addKeyUp(tb, new TGS_FocusSides4(null, btnSearch, btnEsc, listBox));
        TGC_FocusUtils.addKeyUp(btnSearch, new TGS_FocusSides4(tb, null, btnExe, listBox));
        TGC_FocusUtils.addKeyUp(listBox, new TGS_FocusSides4(null, null, tb, null));
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        var mobile = TGC_BrowserNavigatorUtils.mobile();
        Integer[] columnPercents = {20, 60, 20};
        List<Widget> lstWidgets = new ArrayList();
        lstWidgets.addAll(TGS_ListUtils.of(btnEsc, btnExe, new HTML()));
        lstWidgets.addAll(TGS_ListUtils.of(lblSearch, tb, btnSearch));
        lstWidgets.addAll(TGS_ListUtils.of(lblList, mobile ? listBox : new HTML(), new HTML()));
        var arrWidgets = new Widget[lstWidgets.size()];
        IntStream.range(0, lstWidgets.size()).forEachOrdered(i -> arrWidgets[i] = lstWidgets.get(i));
        var grid = TGC_PanelLayoutUtils.createGrid(maxWidth, columnPercents, arrWidgets, false);
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(4 /*+ checkBoxes.size() / 2*/, grid, mobile ? new HTML() : listBox),
                dim, onVisible
        );
    }

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
