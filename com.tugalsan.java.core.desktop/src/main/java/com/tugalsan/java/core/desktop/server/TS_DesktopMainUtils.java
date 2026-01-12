package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.function;
import module java.desktop;

public class TS_DesktopMainUtils {

    private TS_DesktopMainUtils() {

    }

    public static void setThemeAndinvokeLaterAndFixTheme(TGS_FuncMTU_OutTyped<Component> component) {
        TS_DesktopThemeUtils.setThemeDefault();
        TS_DesktopMainUtils.invokeLater(() -> {
            var comp = component.call();
            TS_DesktopThemeUtils.setThemeDarkLAF(comp);
        });
    }

    public static void invokeLater(TGS_FuncMTU run) {
        SwingUtilities.invokeLater(() -> run.run());
    }
}
