package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.function;
import module java.desktop;

public class TS_DesktopJMenuButton extends JMenu {

    private TS_DesktopJMenuButton(String title, TGS_FuncMTU_In1<TS_DesktopJMenuButton> onSelected) {
        super(title);
        addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                onSelected.run(TS_DesktopJMenuButton.this);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
    }

    public static TS_DesktopJMenuButton of(String title, TGS_FuncMTU_In1<TS_DesktopJMenuButton> onSelected) {
        return new TS_DesktopJMenuButton(title, onSelected);
    }
}
