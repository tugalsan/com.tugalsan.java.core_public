package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.time.client.TGS_Time;

public class TGC_DateBoxUtils {

    private TGC_DateBoxUtils() {

    }

    public static int minWidth() {
        return 80;
    }

    public static DateBox create(TGS_FuncMTU_In1<DateBox> onSelect) {
        var w = new DateBox();
        w.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd.MM.yyyy")));
        w.getDatePicker().setYearAndMonthDropdownVisible(true);
        w.getDatePicker().setYearArrowsVisible(true);
        w.setWidth(minWidth() + "px");
        if (onSelect != null) {
            w.addValueChangeHandler(__ -> onSelect.run(w));
        }
        return w;
    }

    public static DateBox create() {
        return create(null);
    }

    public static void setDate(DateBox w, TGS_Time now) {
        w.getTextBox().setText(now.toString_dateOnly());
    }

    public static TGS_Time getDate(DateBox w) {
        return TGS_Time.ofDate_D_M_Y(w.getTextBox().getText());
    }
}
