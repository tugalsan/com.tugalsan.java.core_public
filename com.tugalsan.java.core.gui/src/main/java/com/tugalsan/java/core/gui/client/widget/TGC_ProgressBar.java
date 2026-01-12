package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.math.client.TGS_MathUtils;
import com.tugalsan.java.core.tuple.client.TGS_Tuple2;

public class TGC_ProgressBar extends Widget {

    public Element barElement;
    public Element textElement;

    public TGC_ProgressBar() {
        textElement = TGC_DOMUtils.createDiv();
        textElement.setClassName("gwt-ProgressBar-textElement");

        barElement = TGC_DOMUtils.createDiv();
        barElement.setClassName("gwt-ProgressBar-barElement");
        DOM.appendChild(barElement, textElement);

        setElement(TGC_DOMUtils.createDiv());
        getElement().setClassName("gwt-ProgressBar");
        DOM.appendChild(getElement(), barElement);

        range = new TGS_Tuple2(0, 100);
        update(100);
    }

    public TGS_Tuple2<Integer, Integer> getRange() {
        return range;
    }
    public TGS_Tuple2<Integer, Integer> range;

    final public void update(int cur) {
        var percent = TGS_MathUtils.convertWeightedInt(cur, range, toMinMax100);
        barElement.getStyle().setWidth(percent, Style.Unit.PCT);
        textElement.setPropertyString("innerHTML", percent + "%");
    }
    private final TGS_Tuple2<Integer, Integer> toMinMax100 = new TGS_Tuple2(0, 100);
//    private final TGS_Tuple2<Integer, Integer> toMinMax255 = new TGS_Tuple2(20, 50);

    public void setTextVisible(boolean textVisible) {
        textElement.getStyle().setVisibility(textVisible ? Style.Visibility.VISIBLE : Style.Visibility.HIDDEN);
    }
}
