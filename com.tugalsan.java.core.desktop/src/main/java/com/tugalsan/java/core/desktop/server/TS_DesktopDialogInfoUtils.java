package com.tugalsan.java.core.desktop.server;

import module java.desktop;

public class TS_DesktopDialogInfoUtils {

    private TS_DesktopDialogInfoUtils() {

    }

    public static void show(String title, String text) {
        var infoPane = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE);
        var infoDialog = infoPane.createDialog(title);
        infoDialog.setModalityType(Dialog.ModalityType.MODELESS);
        infoDialog.setVisible(true);
    }
}
