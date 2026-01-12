package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.ui.RadioButton;
import com.tugalsan.java.core.icon.client.TGS_IconUtils;

public class TGC_RadioButtonUtils {

    private TGC_RadioButtonUtils() {

    }

    public static RadioButton createIcon(CharSequence radioGroup, CharSequence iconClassName, CharSequence text) {
        return new RadioButton(radioGroup.toString(), TGS_IconUtils.createSpan(iconClassName, text, true, false), true);
    }
}
