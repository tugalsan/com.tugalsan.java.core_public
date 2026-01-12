package com.tugalsan.java.core.gui.client.theme;

import com.google.gwt.user.client.ui.Widget;
import java.util.Arrays;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;

public class TGC_PanelStyleUtils {

    private TGC_PanelStyleUtils() {

    }

    public static void warn(Widget w, int seconds) {
        red(w);
        TGC_ThreadUtils.run_afterSeconds_afterGUIUpdate(t -> TGC_ThreadUtils.run_afterGUIUpdate(() -> {
            remove(w);
        }), seconds);
    }

    public static void remove(Widget... widgets) {
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.removeStyleName(TGC_PanelBlack.class.getSimpleName());
            w.removeStyleName(TGC_PanelYellow.class.getSimpleName());
            w.removeStyleName(TGC_PanelRed.class.getSimpleName());
            w.removeStyleName(TGC_PanelGreen.class.getSimpleName());
            w.removeStyleName(TGC_PanelBlue.class.getSimpleName());
            w.removeStyleName(TGC_PanelGray.class.getSimpleName());
            w.removeStyleName(TGC_PanelPurple.class.getSimpleName());
            w.removeStyleName(TGC_PanelOrange.class.getSimpleName());
        });
    }

    public static void black(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelBlack.class.getSimpleName());
        });
    }

    public static void blackLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelBlack.class.getSimpleName());
        });
    }

    public static void yellow(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelYellow.class.getSimpleName());
        });
    }

    public static void yellowLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelYellow.class.getSimpleName());
        });
    }

    public static void red(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelRed.class.getSimpleName());
        });
    }

    public static void redLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelRed.class.getSimpleName());
        });
    }

    public static void green(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelGreen.class.getSimpleName());
        });
    }

    public static void greenLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelGreen.class.getSimpleName());
        });
    }

    public static void blue(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelBlue.class.getSimpleName());
        });
    }

    public static void blueLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelBlue.class.getSimpleName());
        });
    }

    public static void gray(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelGray.class.getSimpleName());
        });
    }

    public static void grayLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelGray.class.getSimpleName());
        });
    }

    public static void purple(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelPurple.class.getSimpleName());
        });
    }

    public static void purpleLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelPurple.class.getSimpleName());
        });
    }

    public static void orange(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelOrange.class.getSimpleName());
        });
    }

    public static void orangeLoosely(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.addStyleName(TGC_PanelOrange.class.getSimpleName());
        });
    }
}
