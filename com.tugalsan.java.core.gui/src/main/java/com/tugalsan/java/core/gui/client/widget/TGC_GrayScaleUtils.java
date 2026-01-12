package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;

public class TGC_GrayScaleUtils {

    private TGC_GrayScaleUtils() {

    }

    public static HTML create(CharSequence text, Double fontSize) {
        var whg = new HTML(text.toString());
        whg.getElement().setPropertyString("filter", "grayscale(100%)");
        if (fontSize != null) {
            whg.getElement().getStyle().setFontSize(3, Style.Unit.EM);
        }
        return whg;
    }
}
