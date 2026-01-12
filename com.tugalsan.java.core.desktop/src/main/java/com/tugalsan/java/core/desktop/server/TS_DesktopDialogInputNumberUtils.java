package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.union;
import module java.desktop;

public class TS_DesktopDialogInputNumberUtils {

    private TS_DesktopDialogInputNumberUtils() {

    }

    public static TGS_UnionExcuse<Integer> show(String title) {
        return show(null, title);
    }

    public static TGS_UnionExcuse<Integer> show(String title, Integer initValue) {
        return show(null, title, initValue);
    }

    public static TGS_UnionExcuse<Integer> show(Component parent, String title) {
        var p = new TS_DesktopDialogInputNumberGUI();
        return show(parent, p, title);
    }

    public static TGS_UnionExcuse<Integer> show(Component parent, String title, Integer initValue) {
        var p = new TS_DesktopDialogInputNumberGUI(initValue);
        return show(parent, p, title);
    }

    private static TGS_UnionExcuse<Integer> show(Component parent, TS_DesktopDialogInputNumberGUI panel, String title) {
        var pane = new JOptionPane(panel);
        pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        var dia = pane.createDialog(parent, title);
        dia.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                panel.gainedFocus();
            }
        });
        dia.setDefaultCloseOperation(JOptionPane.OK_OPTION); // necessary?
        dia.setVisible(true);
        var val = pane.getValue();
        if (val == null) {
            return TGS_UnionExcuse.ofExcuse(TS_DesktopDialogInputNumberUtils.class.getSimpleName(), "show", "val == null");
        }
        if (!val.equals(JOptionPane.OK_OPTION)) {
            return TGS_UnionExcuse.ofExcuse(TS_DesktopDialogInputNumberUtils.class.getSimpleName(), "show", "!val.equals(JOptionPane.OK_OPTION)");
        }
        return panel.getNumber();
    }
}
