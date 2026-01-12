package com.tugalsan.java.core.gui.client.key;

import com.google.gwt.user.client.Event;

import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.log.client.TGC_Log;
import java.util.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_KeyTriggerUtils {

    private TGC_KeyTriggerUtils() {

    }

    final private static TGC_Log d = TGC_Log.of(TGC_KeyTriggerUtils.class);

    public static void add2Dom() {
        var body = TGC_DOMUtils.getElementByTagName("body");
        Event.sinkEvents(body, Event.ONKEYDOWN | Event.ONKEYUP);
        Event.setEventListener(body, event -> {
            if (Event.ONKEYDOWN == event.getTypeInt()) {
                var ctrl = event.getCtrlKey();
                var shift = event.getShiftKey();
                if (ctrl == isCtrl && shift == isShift) {
                    return;
                }
                isPreCtrl = isCtrl;
                isPreShift = isShift;
                isCtrl = ctrl;
                isShift = shift;
                onTrigger.stream().forEachOrdered(t -> t.run());
            }
            if (Event.ONKEYUP == event.getTypeInt()) {
                var ctrl = event.getCtrlKey();
                var shift = event.getShiftKey();
                if (ctrl == isCtrl && shift == isShift) {
                    return;
                }
                isPreCtrl = isCtrl;
                isPreShift = isShift;
                isCtrl = ctrl;
                isShift = shift;
                onTrigger.stream().forEachOrdered(t -> t.run());
            }
        });
        onTrigger.add(() -> {
            if (isCtrl && isShift) {
                if (quickCtrlShift != null) {
                    quickCtrlShift.run();
                }
                d.ci("add2Dom", "quickCtrlShift", "CS");
            } else if (isCtrl) {
                if (quickCtrl != null) {
                    quickCtrl.run();
                }
                d.ci("add2Dom", "quickCtrl", "C");
            } else if (isShift) {
                if (quickShift != null) {
                    quickShift.run();
                }
                d.ci("add2Dom", "quickShift", "S");
            } else {
                if (quickNull != null) {
                    quickNull.run();
                }
                d.ci("add2Dom", "quickNull", "N");
            }
        });
    }

    public static boolean isPreCtrl = false;
    public static boolean isPreShift = false;
    public static boolean isCtrl = false;
    public static boolean isShift = false;
    final public static List<TGS_FuncMTU> onTrigger = TGS_ListUtils.of();
    public static TGS_FuncMTU quickCtrlShift = null;
    public static TGS_FuncMTU quickCtrl = null;
    public static TGS_FuncMTU quickShift = null;
    public static TGS_FuncMTU quickNull = null;
}
