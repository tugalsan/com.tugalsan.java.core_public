package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.TextBox;

public class PasteAwareTextBox extends TextBox {

    public PasteAwareTextBox() {
        super();
        sinkEvents(Event.ONPASTE);
    }

    @Override
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONPASTE:
                onPasted(getClipboardData(event));
                break;
        }
    }

    private void onPasted(CharSequence clipboardData) {
        System.out.println("Pasted:" + clipboardData);
    }

    private static native String getClipboardData(Event event) /*-{
        return event.clipboardData.getData('text/plain'); 
    }-*/;
}
