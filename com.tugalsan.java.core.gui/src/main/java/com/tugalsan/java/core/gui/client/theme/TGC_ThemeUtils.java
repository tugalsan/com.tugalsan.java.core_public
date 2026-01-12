package com.tugalsan.java.core.gui.client.theme;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;
import com.tugalsan.java.core.gui.client.widget.TGC_ListBoxUtils;
import com.tugalsan.java.core.gui.client.click.TGC_ClickUtils;
import com.tugalsan.java.core.gui.client.pop.TGC_PopLblYesNoListBox;
import com.tugalsan.java.core.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.java.core.gui.client.widget.TGC_HTMLUtils;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;
import java.util.stream.IntStream;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_ThemeUtils {
    
    private TGC_ThemeUtils(){
        
    }

    final private static TGC_Log d = TGC_Log.of(TGC_ThemeUtils.class);

    public static native JsArrayString getThemeNames()/*-{
        return $wnd.getThemeNames();
    }-*/;

    public static native int getThemeIdxStored()/*-{
        return $wnd.getThemeIdxStored();
    }-*/;

    public static native int getThemeIdxActive()/*-{
        return $wnd.getThemeIdxActive();
    }-*/;

    public static native void setThemeIdx(Integer idx)/*-{
        $wnd.setThemeIdx(idx);
    }-*/;

    public static HTML createLabel() {
        var lbl = TGC_HTMLUtils.create(TGS_IconUtils.CLASS_MAGIC_WAND(), null);
        lbl.setStyleName(TGC_ThemeUtils.class.getSimpleName() + "_Label");
        return lbl;
    }

    public static ListBox createListBox(boolean singleClick, boolean doubleClick, boolean enableFontAwesome) {
        //CREATE
        var themeSelector = TGC_ListBoxUtils.create(true);
        var themes = TGC_ThemeUtils.getThemeNames();
        IntStream.range(0, themes.length()).forEachOrdered(idx -> {
            themeSelector.addItem((enableFontAwesome ? "&#xf0d0 " : "") + themes.get(idx));
        });

        //CLICK
        TGC_ClickUtils.add(
                themeSelector,
                singleClick ? () -> onChange(themeSelector) : null,
                doubleClick ? () -> onChange(themeSelector) : null
        );

        //ACTION
        TGC_ThreadUtils.run_afterGUIUpdate(() -> {
            var themeStoredIdx = TGC_ThemeUtils.getThemeIdxStored();
            d.ci("GWTthemeStoredIdx.0: " + themeStoredIdx);
            themeStoredIdx = themeStoredIdx == -1 ? 0 : themeStoredIdx;
            d.ci("GWTthemeStoredIdx.1: " + themeStoredIdx);
            themeSelector.setSelectedIndex(themeStoredIdx);
            d.ci("GWTthemeSelected:" + themeSelector.getSelectedIndex());
        });

        //STYLE
        themeSelector.setStyleName(TGC_ThemeUtils.class.getSimpleName() + "_ListBox");
        if (enableFontAwesome) {
            themeSelector.addStyleName("font-awesome fa");
            var opts = (SelectElement) themeSelector.getElement().cast();
            IntStream.range(0, opts.getOptions().getLength()).forEach(i -> {
                opts.getOptions().getItem(i).setClassName("font-awesome fa");
            });
        }
        return themeSelector;
    }

    public static PushButton createButton(ListBox themeSelector) {
        var pop = new TGC_PopLblYesNoListBox(
                null, null, "Tema Seçin:", "Seç", "İptal",
                p -> {
                    p.getPop().setVisible(false);
                    TGC_ThemeUtils.onChange(p.listBox);
                },
                p -> p.getPop().setVisible(false),
                null, null
        );
        IntStream.range(0, themeSelector.getItemCount()).forEachOrdered(i -> pop.listBox.addItem(themeSelector.getItemText(i)));
        pop.listBox.setSelectedIndex(TGC_ThemeUtils.getThemeIdxStored());
        var btnTheme = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_MAGIC_WAND());
        TGC_ClickUtils.add(btnTheme, () -> pop.getPop().setVisible(true));
        onChange(pop.listBox);
        btnTheme.setStyleName(TGC_ThemeUtils.class.getSimpleName() + "_" + btnTheme.getClass().getSimpleName());
        return btnTheme;
    }

    public static void onChange(ListBox themeSelector) {
        var si = themeSelector.getSelectedIndex();
        d.ci("onChange", "themeSelector.getSelectedIndex()", si);
        TGC_ThemeUtils.setThemeIdx(si);
        if (onChangeAddon == null) {
            return;
        }
        onChangeAddon.run(si);
    }

    public static TGS_FuncMTU_In1<Integer> onChangeAddon = null;
}
