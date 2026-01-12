package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.cast.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_TextBoxUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_TextBoxUtils.class);

    private TGC_TextBoxUtils() {

    }

    public static void onChange(TextBox tb, TGS_FuncMTU exe) {
        tb.addValueChangeHandler((ValueChangeEvent<String> event) -> {
            exe.run();
        });
    }

    public static void validateTypeNumber(TextBox tb, Integer def) {
        var valStr = tb.getText();
        Long valLng = TGS_CastUtils.toLong(valStr).orElse(null);
        if (TGS_StringUtils.cmn().isNullOrEmpty(valStr)) {
            d.ce("validateTypeNumber", "!TGS_StringUtils.cmn().isPresent(valStr)");
            if (def != null) {
                d.ce("validateTypeNumber", "value set to def", def);
                tb.setText(def.toString());
            }
            return;
        }
        var minStr = tb.getElement().getAttribute("min");
        Long minLng = null;
        if (TGS_StringUtils.cmn().isPresent(minStr)) {
            minLng = TGS_CastUtils.toLong(minStr).orElse(null);
            if (minLng != null && valLng < minLng) {
                d.ce("validateTypeNumber", "valLng < minLng", "value set to min", minStr);
                tb.setText(minStr);
                return;
            }
        }
        var maxStr = tb.getElement().getAttribute("max");
        Long maxLng = null;
        if (TGS_StringUtils.cmn().isPresent(maxStr)) {
            maxLng = TGS_CastUtils.toLong(maxStr).orElse(null);
            if (minLng != null && valLng > maxLng) {
                d.ce("validateTypeNumber", "valLng > maxLng", "value set to min", maxStr);
                tb.setText(maxStr);
                return;
            }
        }
    }

    public static void setTypeNumber(TextBox tb, Integer optional_min, Integer optional_def, Integer optional_max) {
        tb.getElement().setAttribute("type", "number");
        if (optional_min != null) {
            tb.getElement().setAttribute("min", optional_min.toString());
        }
        if (optional_def != null) {
            tb.setText(optional_def.toString());
        }
        if (optional_max != null) {
            tb.getElement().setAttribute("max", optional_max.toString());
        }
    }

    public static void setTypeColor(TextBox tb) {
        tb.getElement().setAttribute("type", "color");
    }
}
