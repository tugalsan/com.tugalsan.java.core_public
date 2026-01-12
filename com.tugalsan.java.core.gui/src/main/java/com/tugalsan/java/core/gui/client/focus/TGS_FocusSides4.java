package com.tugalsan.java.core.gui.client.focus;

import com.google.gwt.user.client.ui.FocusWidget;

public class TGS_FocusSides4<T extends FocusWidget> {

    public T left;
    public T right;
    public T up;
    public T down;

    @Override
    public String toString() {
        return TGS_FocusSides4.class.getSimpleName() + "{left/right/up/down: " + left + "/" + right + "/" + up + "/" + down + "}";
    }

    public TGS_FocusSides4(T left, T right, T up, T down) {
        set(left, right, up, down);
    }

    public TGS_FocusSides4(TGS_FocusSides4<T> sides) {
        sniffFrom(sides);
    }

    final public void sniffFrom(TGS_FocusSides4<T> sides) {
        set(sides.left, sides.right, sides.up, sides.down);
    }

    final public void set(T left, T right, T up, T down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }
}
