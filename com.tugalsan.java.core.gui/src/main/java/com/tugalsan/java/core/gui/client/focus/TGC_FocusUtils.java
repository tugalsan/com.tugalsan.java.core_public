package com.tugalsan.java.core.gui.client.focus;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.thread.client.*;

public class TGC_FocusUtils {

    private TGC_FocusUtils() {

    }

    final private static TGC_Log d = TGC_Log.of(TGC_FocusUtils.class);

    public static void setFocusAfterGUIUpdate(FocusWidget fw) {
        TGC_ThreadUtils.run_afterGUIUpdate(() -> {
            if (fw == null) {//FIX
                return;
            }
            fw.setFocus(true);
            if (fw instanceof TextArea) {
                //DO NOTHING
            } else if (fw instanceof ValueBoxBase) {
                var vb = (ValueBoxBase) fw;
                if (!vb.isReadOnly()) {
                    vb.selectAll();
                }
            }
        });
    }

    //SIDES LOGIC DRIVER
    public static void focusSideLeft(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        if (fw instanceof TextBox) {//SKIP - includes PasswordTextBox and DateBox
            var tb = (TextBox) fw;
            if (tb.getText().length() != 0 && tb.getCursorPos() != 0) {
                return;
            }
        }
        if (sides != null && sides.left != null && sides.left.isVisible()) {
            sides.left.setFocus(true);
        }
    }

    public static void focusSideRight(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        if (fw instanceof TextBox) {//SKIP - inclusdes PasswordTextBox and DateBox
            var tb = (TextBox) fw;
            if (tb.getText().length() != 0 && tb.getCursorPos() != tb.getText().length()) {
                return;
            }
        }
        if (sides != null && sides.right != null && sides.right.isVisible()) {
            sides.right.setFocus(true);
        }
    }

    public static void focusSideUp(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        if (fw instanceof ListBox) {//SKIP
            var tb = (ListBox) fw;
            if (tb.getItemCount() != 0 && tb.getSelectedIndex() != -1 && tb.getSelectedIndex() != 0) {
                return;
            }
        }
        if (sides != null && sides.up != null && sides.up.isVisible()) {
            sides.up.setFocus(true);
        }
    }

    public static void focusSideDown(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        if (fw instanceof ListBox) {//SKIP
            var tb = (ListBox) fw;
            if (tb.getItemCount() != 0 && tb.getSelectedIndex() != -1 && tb.getSelectedIndex() != tb.getItemCount() - 1) {
                return;
            }
        }
        if (sides != null && sides.down != null && sides.down.isVisible()) {
            sides.down.setFocus(true);
        }
    }

    public static void focusSide(FocusWidget fw, TGS_FocusSides4 sides, int nativeKeyCode) {
        if (fw == null) {//FIX
            return;
        }
        switch (nativeKeyCode) {
            case KeyCodes.KEY_UP:
                d.ci("KeyUpHandler", "KeyCodes.KEY_UP");
                if (fw instanceof TextArea) {
                    var ta = (TextArea) fw;
                    var pos0 = ta.getCursorPos();
                    TGC_ThreadUtils.run_afterGUIUpdate(() -> {
                        var pos1 = ta.getCursorPos();
                        if (pos0 == pos1) {
                            d.ci("pos", "posizyon değişmedi, focus up");
                            focusSideUp(fw, sides);
                        } else {
                            d.ci("pos", "posizyon değişti, skip");
                        }
                    });
                } else {
                    focusSideUp(fw, sides);
                }
                break;
            case KeyCodes.KEY_DOWN:
                d.ci("KeyUpHandler", "KeyCodes.KEY_DOWN");
                focusSideDown(fw, sides);
                break;
            case KeyCodes.KEY_LEFT:
                d.ci("KeyUpHandler", "KeyCodes.KEY_LEFT");
                focusSideLeft(fw, sides);
                break;
            case KeyCodes.KEY_RIGHT:
                d.ci("KeyUpHandler", "KeyCodes.KEY_RIGHT");
                focusSideRight(fw, sides);
                break;
            default:
                break;
        }
    }
    
    public static void addKeyUp(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        fw.addKeyUpHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            focusSide(fw, sides, curKeyCode);
        });
    }

    @Deprecated //WHY use keyup
    public static void addKeyDown(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw == null) {//FIX
            return;
        }
        fw.addKeyDownHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            focusSide(fw, sides, curKeyCode);
        });
    }

    public static void addKeyUp(FocusWidget fw, TGS_FuncMTU_In1<Integer> exe) {
        if (fw == null) {//FIX
            return;
        }
        fw.addKeyUpHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            exe.run(curKeyCode);
        });
    }

    @Deprecated //WHY use keyup
    public static void addKeyDown(FocusWidget fw, TGS_FuncMTU_In1<Integer> exe) {
        if (fw == null) {//FIX
            return;
        }
        fw.addKeyDownHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            exe.run(curKeyCode);
        });
    }

    public static void addKeyUp(ListBox lb, TGS_FocusSides4 sides, TGS_FuncMTU_In1<Integer> exe) {
        if (lb == null) {//FIX
            return;
        }
        TGC_FocusUtils.addKeyUp(lb, nativeKeyCode -> {
            if (null != nativeKeyCode) {
                switch (nativeKeyCode) {
                    case KeyCodes.KEY_UP:
                        if (sides.up == null) {
                            return;
                        }
                        if (lb.getItemCount() == 0 || lb.getSelectedIndex() == 0) {
                            TGC_FocusUtils.setFocusAfterGUIUpdate(sides.up);
                            if (sides.up instanceof TextBox) {
                                var tb = (TextBox) sides.up;
                                tb.selectAll();
                            }
                        } else {
                            exe.run(lb.getSelectedIndex());
                        }
                        break;
                    case KeyCodes.KEY_DOWN:
                        if (sides.down == null) {
                            return;
                        }
                        if (lb.getItemCount() == 0 || lb.getSelectedIndex() == lb.getItemCount() - 1) {
                            TGC_FocusUtils.setFocusAfterGUIUpdate(sides.down);
                            if (sides.down instanceof TextBox) {
                                var tb = (TextBox) sides.down;
                                tb.selectAll();
                            }
                        } else {
                            exe.run(lb.getSelectedIndex());
                        }
                        break;
                    default:
                        focusSide(lb, sides, nativeKeyCode);
                        break;
                }
            }
        });
    }
}
