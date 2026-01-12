package com.tugalsan.java.core.gui.client.click;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.*;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.tuple.client.TGS_Tuple3;
import java.util.ArrayList;
import java.util.List;

public class TGC_ClickUtils {
    
    private TGC_ClickUtils(){
        
    }

    private final static TGC_Log d = TGC_Log.of(TGC_ClickUtils.class);
    private final static long DOUBLE_CLICK_THRESHOLD_MS = 300;

    public static void add(PushButton w, TGS_FuncMTU exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(ListBox w, TGS_FuncMTU singleClick, TGS_FuncMTU doubleClick) {
        w.addClickHandler(e -> {
            var sourceWidget = (ListBox) e.getSource();
            var sourceIdx = sourceWidget.getSelectedIndex();
            var now = System.currentTimeMillis();
            d.ci("add(ListBox)", "now", now);
            var found = lastClik_ListBox_Time_Idx.stream().filter(lc -> lc.value0.equals(sourceWidget)).findAny().orElse(null);
            d.ci("add(ListBox)", "found", found);
            if (found == null) {
                lastClik_ListBox_Time_Idx.add(TGS_Tuple3.of(sourceWidget, now, sourceIdx));
                if (singleClick != null) {
                    d.ci("add(ListBox)", "run", "found not found");
                    singleClick.run();
                }
            } else {
                var now1SecondAgo = now - DOUBLE_CLICK_THRESHOLD_MS;
                d.ci("add(ListBox)", "now1SecondAgo", now1SecondAgo);
                if (found.value1 < now1SecondAgo) {
                    if (singleClick != null) {
                        d.ci("add(ListBox)", "run", found.value1, "found < 1seondAgo", now1SecondAgo);
                        singleClick.run();
                    }
                } else {
                    if (found.value2 != -1) {
                        sourceWidget.setSelectedIndex(found.value2);
                        d.ci("add(ListBox)", "compansate", found.value1, "found < 1seondAgo", now1SecondAgo);
                    } else {
                        d.ci("add(ListBox)", "cancel", found.value1, "found < 1seondAgo", now1SecondAgo);
                    }
                }
                found.value1 = now;
                found.value2 = sourceIdx;
            }
        });
        w.addDoubleClickHandler(e -> {
            if (doubleClick != null) {
                doubleClick.run();
            }
        });
    }
    final private static List<TGS_Tuple3<ListBox, Long, Integer>> lastClik_ListBox_Time_Idx = new ArrayList();

    public static void add(TextBox w, TGS_FuncMTU exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(CheckBox w, TGS_FuncMTU exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(ToggleButton w, TGS_FuncMTU_In1<Boolean> exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                var tb = (ToggleButton) e.getSource();
                exe.run(tb.isDown());
            }
        });
    }

    public static void add(RadioButton w, TGS_FuncMTU_In1<Boolean> exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                var rb = (RadioButton) e.getSource();
                exe.run(rb.getValue());
            }
        });
    }

    public static void add(Image w, TGS_FuncMTU exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
//                var image = (Image) e.getSource();
                exe.run();
            }
        });
    }

    public static void add(HTML html, TGS_FuncMTU exe) {
        html.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(Canvas canvas, TGS_FuncMTU exe) {
        canvas.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }
}
