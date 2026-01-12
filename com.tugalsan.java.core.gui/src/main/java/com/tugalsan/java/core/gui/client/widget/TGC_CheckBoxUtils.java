package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;

public class TGC_CheckBoxUtils {

    private TGC_CheckBoxUtils() {

    }

    public static CheckBox createIcon(CharSequence iconClassName, CharSequence text) {
        return new CheckBox(TGS_IconUtils.createSpan(iconClassName, text, true, false), true);
    }
}
