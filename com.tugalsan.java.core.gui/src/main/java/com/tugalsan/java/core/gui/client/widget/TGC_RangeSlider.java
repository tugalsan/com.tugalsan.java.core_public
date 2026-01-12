package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TGC_RangeSlider extends Composite {

    private InputElement rangeElement;
    private Label valueLabel;

    public TGC_RangeSlider(int min, int max, int value) {
        rangeElement = Document.get().createTextInputElement();
        rangeElement.setAttribute("type", "range");
        rangeElement.setAttribute("min", String.valueOf(min));
        rangeElement.setAttribute("max", String.valueOf(max));
        rangeElement.setAttribute("value", String.valueOf(value));
        valueLabel = new Label(String.valueOf(value));
        var panel = new HorizontalPanel();
        panel.add(new Widget() {
            {
                setElement(rangeElement);
            }
        });
        panel.add(valueLabel);
        initWidget(panel);
        addDomHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                valueLabel.setText(rangeElement.getValue());
            }
        }, ChangeEvent.getType());
    }

    public int getValue() {
        return Integer.parseInt(rangeElement.getValue());
    }

    public void setValue(int value) {
        rangeElement.setValue(String.valueOf(value));
        valueLabel.setText(String.valueOf(value));
    }

    public void addChangeHandler(ChangeHandler handler) {
        addDomHandler(handler, ChangeEvent.getType());
    }
}
