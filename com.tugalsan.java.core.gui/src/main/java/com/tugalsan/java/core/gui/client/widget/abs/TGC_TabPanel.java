package com.tugalsan.java.core.gui.client.widget.abs;

import com.google.gwt.user.client.ui.*;
import java.util.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_TabPanel extends TGC_WidgetAbstract<TabPanel> {

//    final private static TGC_Log d = TGC_Log.of(TGC_TabPanel.class);

    public TGC_TabPanel() {
        this(null);
    }

    public TGC_TabPanel(CharSequence tooltip) {
        super(tooltip == null ? null : tooltip.toString(), new TabPanel());
        widget.setAnimationEnabled(true);
        widget.getTabBar().addStyleName(TGC_TabPanel.class.getSimpleName());
    }

//    public void setBackColor(String tabBar_backGroundColor) {
//        widget.getTabBar().getElement().getStyle().setBackgroundColor(tabBar_backGroundColor);
//    }
    public int size() {
        return widget.getWidgetCount();
    }

    public TGC_TabPanel clear() {
        widget.clear();
        return this;
    }

    public TGC_TabPanel add(AbsolutePanel w, CharSequence tabText, CharSequence optional_tooltip) {
        if (optional_tooltip == null) {
            widget.add(w, tabText.toString());
        } else {
            widget.add(w, TGS_StringUtils.cmn().concat("<span title='", optional_tooltip, "'>", tabText, "</span>"), true);
        }
        showLast();
        return this;
    }

    public TGC_TabPanel remove(int idx) {
        widget.remove(idx);
        return this;
    }

    public boolean remove(AbsolutePanel w) {
        var idx = find(w);
        if (idx == null) {
            return false;
        }
        remove(idx);
        return true;
    }

    public AbsolutePanel get(int idx) {
        var w = widget.getWidget(idx);
        if (w instanceof AbsolutePanel) {
            return (AbsolutePanel) w;
        }
        return null;
    }

    public int getSelectedTabIndex() {
        return widget.getTabBar().getSelectedTab();
    }

    public Integer find(AbsolutePanel fw) {
        var size = size();
        for (var i = 0; i < size; i++) {
            if (Objects.equals(fw, get(i))) {
                return i;
            }
        }
        return null;
    }

    public boolean contains(AbsolutePanel fw) {
        return find(fw) != null;
    }

    //EXTRA
    public void show(int idx) {
        widget.selectTab(idx);
    }

    public void showLast() {
        widget.selectTab(widget.getWidgetCount() - 1);
    }

    public Integer show(AbsolutePanel w) {
        var idx = find(w);
        if (idx != null) {
            show(idx);
        }
        return idx;
    }
}
