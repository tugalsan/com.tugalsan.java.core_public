package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.icon.client.*;
import com.tugalsan.java.core.tuple.client.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_ButtonUtils {

    private TGC_ButtonUtils() {

    }

    public static PushButton create(TGS_Tuple3<String, String, Boolean> urlImageUpDownVertical) {
        return create(urlImageUpDownVertical, null);
    }

    public static PushButton create(TGS_Tuple3<String, String, Boolean> urlImageUpDownVertical, CharSequence label) {
        PushButton pb;
        if (urlImageUpDownVertical == null) {
            pb = new PushButton(label.toString());
        } else {
            pb = new PushButton(new Image(urlImageUpDownVertical.value0), new Image(urlImageUpDownVertical.value1));
            if (label != null) {
                var innerHtml = pb.getElement().getInnerHTML();
                if (urlImageUpDownVertical.value2) {
                    innerHtml = TGS_StringUtils.cmn().concat("<div><center>", innerHtml, "</center><label style='cursor:pointer'><center>", label, "</center></label></div>");
                } else {
                    innerHtml = TGS_StringUtils.cmn().concat("<table><tr><td>", innerHtml, "</td><td><label style='cursor:pointer'>", label, "</label></td></tr></table>");
                }
                pb.getElement().setInnerHTML(innerHtml);
            }
        }
        return pb;
    }

    @Deprecated
    public static PushButton create(HTML label) {
        var pb = new PushButton();
//        var innerHtml = pb.getElement().getInnerHTML();
//        innerHtml = TGS_StringUtils.cmn().concat("<table><tr><td>", innerHtml, "</td><td><label style='cursor:pointer'>", label.getHTML(), "</label></td></tr></table>");
        var innerHtml = label.getHTML();
        pb.getElement().setInnerHTML(innerHtml);
        return pb;
    }

    @Deprecated
    public static PushButton createGray(CharSequence label, Double fontSize) {
        return create(TGC_GrayScaleUtils.create(label, fontSize));
    }

    public static PushButton createIcon(CharSequence fullIconClassName) {
        return createIcon(fullIconClassName, null);
    }

    //EXAMPLE: btnFilter = TGC_ButtonUtils.createIcon("icon icon-filter", "Süz");
    public static PushButton createIcon(CharSequence fullIconClassName, CharSequence text) {
        var btn = new PushButton();
        setIcon(btn, fullIconClassName, text);
        return btn;
    }

    public static PushButton createIcon(CharSequence fullIconClassName0, CharSequence fullIconClassName1, CharSequence text) {
        var btn = new PushButton();
        setIcon(btn, fullIconClassName0, fullIconClassName1, text);
        return btn;
    }

    public static void setIcon(CustomButton btn, CharSequence fullIconClassName0, CharSequence fullIconClassName1, CharSequence text) {
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName0, TGS_IconUtils.createSpan(fullIconClassName1, text)));
    }

    public static void setIcon(CustomButton btn, CharSequence fullIconClassName, CharSequence text) {
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName, text));
    }

//    public static void setIcon(CustomButton btn, String fullIconClassName, String text) {
//        btn.getUpFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        btn.getUpFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        btn.getDownFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        
//    }
    public static ToggleButton createIconToggle(CharSequence fullIconClassName, CharSequence text) {
        var btn = new ToggleButton();
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName, text));
        return btn;
    }
}
