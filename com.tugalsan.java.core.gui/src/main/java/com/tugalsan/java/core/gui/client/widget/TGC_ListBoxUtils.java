package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.user.client.ui.ListBox;
import java.util.List;
import java.util.stream.IntStream;
import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.gui.client.browser.TGC_BrowserNavigatorUtils;
import com.tugalsan.java.core.stream.client.*;

public class TGC_ListBoxUtils {

    private TGC_ListBoxUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_ListBoxUtils.class);
    public static ListBox create(boolean comboBox) {
        var listBox = new ListBox() {

            @Override
            public void addItem(String item) {
                super.addItem(item);
                var childeren = super.getStyleElement().getChildNodes();
                var lastChild = childeren.getItem(childeren.getLength() - 1);
                if (lastChild.getNodeType() != Node.ELEMENT_NODE) {
                    return;
                }
                var lastChildElement = (Element) lastChild;
                lastChildElement.setTitle(item);
            }
        };
        listBox.setVisibleItemCount(TGC_BrowserNavigatorUtils.mobile() || comboBox ? 1 : 3);
        return listBox;
    }

    public static boolean isComboBox(ListBox w) {
        return w.getVisibleItemCount() == 1;
    }

    public static void setEnable(ListBox w, boolean enable, int itemsIdx) {
        if (w.getItemCount() > itemsIdx) {
            TGC_DOMUtils.setListBoxItemEnableAt(w, itemsIdx, enable);
        }
    }

    public static boolean isEmpty(ListBox w) {
        return w.getItemCount() == 0;
    }

    public static Boolean isSelectedAll(ListBox w) {
        if (isEmpty(w)) {
            return null;
        }
        return getSelectedIndexes(w).size() == w.getItemCount();
    }

    public static Boolean isSelectedNone(ListBox w) {
        if (isEmpty(w)) {
            return null;
        }
        return getSelectedIndexes(w).isEmpty();
    }

    public static List<Integer> getSelectedIndexes(ListBox w) {
        return TGS_StreamUtils.toLst(
                IntStream.range(0, w.getItemCount())
                        .filter(i -> w.isItemSelected(i))
                        .boxed()
        );
    }

    public static boolean hasSelectedIndex(ListBox w) {
        for (var i = 0; i < w.getItemCount(); i++) {
            if (w.isItemSelected(i)) {
                return true;
            }
        }
        return false;
    }

    public static void selectNone(ListBox w) {
        selectAll(w, false);
    }

    public static void selectAll(ListBox w) {
        selectAll(w, true);
    }

    public static void selectAll(ListBox w, boolean value) {
        IntStream.range(0, w.getItemCount()).forEach(i -> w.setItemSelected(i, value));
    }

    public static void selectOnlyLastItem(ListBox w) {
        selectNone(w);
        if (w.getItemCount() > 0) {
            w.setItemSelected(w.getItemCount() - 1, true);
        }
    }

    public static void selectOnlyFirstItem(ListBox w) {
        selectNone(w);
        if (w.getItemCount() > 0) {
            w.setItemSelected(0, true);
        }
    }

    @Deprecated //NOT SUPPORTED BY LISTBOX OF HTML
    public static void setItemStyle(ListBox listBox, int idx, CharSequence iconClassName) {
        var children = listBox.getElement().getChildNodes();
        var child = children.getItem(idx);
        if (child.getNodeType() == Node.ELEMENT_NODE && child instanceof OptionElement) {
            var optionElement = (OptionElement) child;
            optionElement.addClassName(iconClassName.toString());
        }
    }

    @Deprecated //NOT SUPPORTED BY LISTBOX OF HTML
    public static void removeItemStyle(ListBox listBox, int idx, CharSequence iconClassName) {
        var children = listBox.getElement().getChildNodes();
        var child = children.getItem(idx);
        if (child.getNodeType() == Node.ELEMENT_NODE && child instanceof OptionElement) {
            var optionElement = (OptionElement) child;
            optionElement.removeClassName(iconClassName.toString());
        }
    }
}
