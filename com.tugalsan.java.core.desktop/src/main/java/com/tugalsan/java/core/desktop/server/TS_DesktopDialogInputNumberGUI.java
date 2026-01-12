package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.cast;
import module com.tugalsan.java.core.union;
import module java.desktop;

public class TS_DesktopDialogInputNumberGUI extends JPanel {

    final private JTextField tf = new JTextField();
    final private JLabel lbl = new JLabel();
    private boolean focusedAlready;

    public void gainedFocus() {
        if (!focusedAlready) {
            focusedAlready = true;
            tf.requestFocusInWindow();
        }
    }

    public TS_DesktopDialogInputNumberGUI(Integer initValue) {
        super(new FlowLayout());
        focusedAlready = false;
        var d = new Dimension();
        d.setSize(30, 22);
        tf.setMinimumSize(d);
        tf.setColumns(10);
        lbl.setText("#: ");
        if (initValue != null) {
            tf.setText(initValue.toString());
        }
        add(lbl);
        add(tf);
    }

    public TS_DesktopDialogInputNumberGUI() {
        this(null);
    }

    public TGS_UnionExcuse<Integer> getNumber() {
        var val = TGS_CastUtils.toInteger(tf.getText()).orElse(null);
        if (val == null) {
            return TGS_UnionExcuse.ofExcuse(TS_DesktopDialogInputNumberGUI.class.getSimpleName(), "getNumber", "val == null");
        }
        return TGS_UnionExcuse.of(val);
    }
}
