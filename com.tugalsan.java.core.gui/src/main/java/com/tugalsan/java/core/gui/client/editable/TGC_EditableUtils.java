package com.tugalsan.java.core.gui.client.editable;

import com.google.gwt.user.client.ui.ValueBoxBase;

public class TGC_EditableUtils {
    
    private TGC_EditableUtils(){
        
    }

    public static void set(ValueBoxBase w, boolean readOnly) {
        w.setReadOnly(readOnly);
    }
}
