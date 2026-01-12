package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ListBox;
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
import com.tugalsan.java.core.gui.client.widget.TGC_CheckBoxUtils;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;

import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.tuple.client.TGS_Tuple2;
import com.tugalsan.java.core.stream.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopLblYesNoCheckListBox implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoCheckListBox.class);
    final private String lblListBoxHTML, btnOkText, btnCancelText, lblCheckBoxHTML;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onEsc, onExe, onListChange;
    final public List<String> listBoxContent;
    final public List<TGS_Tuple2<String, String>> checkBoxIconAndLabels;
    final private TGS_FuncMTU onVisible;

    public TGC_PopLblYesNoCheckListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional, List<TGS_Tuple2<String, String>> checkBoxIconAndLabels,
            CharSequence lblListBoxHTML, CharSequence lblCheckBoxHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onEsc,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onListChange,
            TGS_FuncMTU onVisible_optional) {
        this(dim, listBoxContent_optional, checkBoxIconAndLabels,
                lblListBoxHTML, lblCheckBoxHTML, btnOkText, btnCancelText,
                onExe, onEsc, onListChange, onVisible_optional, null, null);
    }

    public TGC_PopLblYesNoCheckListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional, List<TGS_Tuple2<String, String>> checkBoxIconAndLabels,
            CharSequence lblListBoxHTML, CharSequence lblCheckBoxHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onEsc,
            TGS_FuncMTU_In1<TGC_PopLblYesNoCheckListBox> onListChange,
            TGS_FuncMTU onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblListBoxHTML = lblListBoxHTML.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.lblCheckBoxHTML = lblCheckBoxHTML.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onListChange = onListChange == null ? TGS_FuncMTU_In1.empty : onListChange;
        this.listBoxContent = listBoxContent_optional;
        this.checkBoxIconAndLabels = checkBoxIconAndLabels;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = listBox;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;

    public void selectLastItem() {
        listBox.setSelectedIndex(listBox.getItemCount() - 1);
    }

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        lblListBox = new HTML(lblListBoxHTML);
        lblCheckBox = new HTML(lblCheckBoxHTML);
        listBox = TGC_ListBoxUtils.create(false);
        checkBoxes = TGS_StreamUtils.toLst(
                IntStream.range(0, checkBoxIconAndLabels.size())
                        .mapToObj(i -> TGC_CheckBoxUtils.createIcon(checkBoxIconAndLabels.get(i).value0, checkBoxIconAndLabels.get(i).value1))
        );
    }
    public HTML lblListBox;
    public HTML lblCheckBox;
    public PushButton btnEsc, btnExe;
    public ListBox listBox;
    public List<CheckBox> checkBoxes;

    @Override
    final public void createPops() {
    }
    private TGC_Pop panelPopup;

    @Override
    final public void configInit() {
        if (listBoxContent != null) {
            listBoxContent.stream().forEachOrdered(s -> listBox.addItem(s));
            listBox.setSelectedIndex(0);
        }
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(listBox, () -> onListChange.run(this), () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(listBox, () -> onExe.run(this), () -> onEsc.run(this), () -> onListChange.run(this), () -> onListChange.run(this));
        checkBoxes.stream().forEachOrdered(cb -> TGC_KeyUtils.add(cb, null, () -> onEsc.run(this)));
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(null, btnExe, null, checkBoxes.get(0)));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, checkBoxes.get(0), null, checkBoxes.get(0)));
        TGC_FocusUtils.addKeyUp(listBox, new TGS_FocusSides4(checkBoxes.get(checkBoxes.size() - 1), null, checkBoxes.get(checkBoxes.size() - 1), null));
        IntStream.range(0, checkBoxes.size()).forEachOrdered(i -> {
            TGC_FocusUtils.addKeyUp(checkBoxes.get(i), nativeKeyCode -> {
                if (null != nativeKeyCode) {
                    switch (nativeKeyCode) {
                        case KeyCodes.KEY_UP:
                            if (i == 0) {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(btnEsc);
                            } else {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(checkBoxes.get(i - 1));
                            }
                            break;
                        case KeyCodes.KEY_DOWN:
                            if (i == checkBoxes.size() - 1) {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(listBox);
                            } else {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(checkBoxes.get(i + 1));
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        });
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        var mobile = TGC_BrowserNavigatorUtils.mobile();
        Integer[] columnPercent = {50, 50};
        List<Widget> widgetsArr = TGS_ListUtils.of(
                btnEsc, btnExe,
                lblCheckBox
        );
        checkBoxes.stream().forEachOrdered(cb -> {
            widgetsArr.add(cb);
        });
        if (checkBoxes.size() % 2 == 0) {
            widgetsArr.add(new HTML());
        }
        widgetsArr.add(lblListBox);
        widgetsArr.add(mobile ? listBox : new HTML());
        var widgets = new Widget[widgetsArr.size()];
        IntStream.range(0, widgetsArr.size()).forEachOrdered(i -> widgets[i] = widgetsArr.get(i));
        var grid = TGC_PanelLayoutUtils.createGrid(maxWidth, columnPercent, widgets, false);
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
