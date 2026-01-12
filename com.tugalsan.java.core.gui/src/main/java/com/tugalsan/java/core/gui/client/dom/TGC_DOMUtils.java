package com.tugalsan.java.core.gui.client.dom;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.cast.client.*;
import com.tugalsan.java.core.shape.client.*;
import com.tugalsan.java.core.string.client.*;

/*
setCursor
setTextAlign
setBorder
setFontBold
 */
public class TGC_DOMUtils {

//    final private static TGC_Log d = TGC_Log.of(TGC_DOMUtils.class);
    private TGC_DOMUtils() {

    }

    public static TGS_ShapeRectangle getRect(Widget w) {
        return TGS_ShapeRectangle.of(
                TGC_DOMUtils.getLeft(w.getElement()),
                TGC_DOMUtils.getTop(w.getElement()),
                TGC_DOMUtils.getWidth(w.getElement()),
                TGC_DOMUtils.getHeight(w.getElement())
        );
    }

    public static void setVisible(Element e, boolean enable) {
        e.getStyle().setVisibility(enable ? Style.Visibility.VISIBLE : Style.Visibility.HIDDEN);
    }

    public static Element createDiv() {
        return DOM.createDiv();
    }

    public static void setListBoxItemEnableAt(ListBox lb, int itemIdx, boolean enable) {
        if (enable) {
            lb.getElement().getElementsByTagName("option").getItem(itemIdx).removeAttribute("disabled");
        } else {
            lb.getElement().getElementsByTagName("option").getItem(itemIdx).setAttribute("disabled", "disabled");
        }
    }

    public static boolean getListBoxItemEnableAt(ListBox lb, int itemIdx) {
        return !lb.getElement().getElementsByTagName("option").getItem(itemIdx).getAttribute("disabled").equals("disabled");
    }

    public static void removeFromParent(Element c) {
        c.removeFromParent();
    }

    public static void removeFromParent(Element rootPanel_getBodyElement, CharSequence id) {
        removeFromParent(getElementById(id.toString()));
    }

    public static void setFontBold(Element e, boolean enable) {
        e.getStyle().setFontWeight(enable ? Style.FontWeight.BOLD : Style.FontWeight.NORMAL);
    }

    public static void setFontItalic(Element e, boolean enable) {
        e.getStyle().setFontStyle(enable ? Style.FontStyle.ITALIC : Style.FontStyle.NORMAL);
    }

    public static void setMargin(Element e, int px) {
        e.getStyle().setMargin(px, Style.Unit.PX);
        //DOM.setStyleAttribute(e, "margin", px + "px auto");
    }

    public static void setCursorDefault(Element e) {
        e.getStyle().setCursor(Style.Cursor.DEFAULT);
    }

    public static void setCursorPointer(Element e) {
        e.getStyle().setCursor(Style.Cursor.POINTER);
    }

    public static void setCursorCrossHair(Element e) {
        e.getStyle().setCursor(Style.Cursor.CROSSHAIR);
    }

    public static void setCursorHelp(Element e) {
        e.getStyle().setCursor(Style.Cursor.HELP);
    }

    public static void setCursorWAIT(Element e) {
        e.getStyle().setCursor(Style.Cursor.WAIT);
    }

    public static void setBackGroundImageUrl(Element e, CharSequence backImageUrl) {
        e.getStyle().setBackgroundImage(TGS_StringUtils.cmn().concat("url('", backImageUrl, "')"));
    }

    public static void setOverflowHidden(Element e) {
        e.getStyle().setOverflow(Style.Overflow.HIDDEN);
    }

    public static void setBackGroundColorText(Element e, CharSequence color) {
//        e.getStyle().setProperty("background", "transparent");//fix
//        setOpacity(e, 1);//fix
        e.getStyle().setBackgroundColor(color.toString());//#000000 or lightgray
    }

    public static void setOpacity(Element e, int per) {
        e.getStyle().setOpacity(per);
    }

    public static void setBackGroundColorHex(Element e, CharSequence rgb) {
        setBackGroundColorText(e, "#" + rgb.toString());
    }

    public static void set(Element e, CharSequence attr, CharSequence value) {
        e.getStyle().setProperty(attr.toString(), value.toString());
    }

    public static void setForegroundColorText(Element e, CharSequence color) {
        e.getStyle().setColor(color.toString());
    }

    public static void setWidth(Element e, int pxValue) {
        e.getStyle().setWidth(pxValue, Style.Unit.PX);
    }

    public static void setHeight(Element e, int pxValue) {
        e.getStyle().setHeight(pxValue, Style.Unit.PX);
    }

    public static void setWidthPercent(Element e, int per) {
        e.getStyle().setWidth(per, Style.Unit.PCT);
    }

    public static void setHeightPercent(Element e, int per) {
        e.getStyle().setHeight(per, Style.Unit.PCT);
    }

    public static Integer getWidth(Element e) {
        return px2Int(e.getStyle().getWidth());
    }

    public static Integer getHeight(Element e) {
        return px2Int(e.getStyle().getHeight());
    }

    public static Integer px2Int(CharSequence pxValue) {
//        d.cr("px2Int", pxValue);
        if (TGS_StringUtils.cmn().isNullOrEmpty(pxValue)) {
            return null;
        }
        return TGS_CastUtils.toInteger(pxValue.subSequence(0, pxValue.length() - 2)).orElse(null);
    }

    public static void setSize(Element e, int widthInPx, int heightInPx) {
        e.getStyle().setWidth(widthInPx, Style.Unit.PX);
        e.getStyle().setHeight(heightInPx, Style.Unit.PX);
    }

    public static void setTextAlignCenter(Element e) {
        e.getStyle().setTextAlign(Style.TextAlign.CENTER);
    }

    public static void setTextAlignLeft(Element e) {
        e.getStyle().setTextAlign(Style.TextAlign.LEFT);
    }

    public static void setTextAlignRight(Element e) {
        e.getStyle().setTextAlign(Style.TextAlign.RIGHT);
    }

    public static void setPadding(Element e, int px) {
        e.getStyle().setPadding(px, Style.Unit.PX);
    }

    public static void setBorder(Element e, int px, boolean dotted, CharSequence color) {
        e.getStyle().setBorderStyle(dotted ? Style.BorderStyle.DOTTED : Style.BorderStyle.SOLID);
        e.getStyle().setBorderColor(color.toString());
        e.getStyle().setBorderWidth(px, Style.Unit.PX);
    }

    public static void setFontFamily_Arial(Element e) {
        //DOM.setStyleAttribute(e, "fontFamily", "Arial");
        e.getStyle().setProperty("fontFamily", "Arial");
    }

    public static int getBorderTopWidth(Element e) {
        return DOM.getIntStyleAttribute(e, "borderTopWidth");
    }

    public static void setPositionAbsolute(Element e) {
        //DOM.setStyleAttribute(e, "position", fixed ? "fixed" : "absolute");
        e.getStyle().setPosition(Style.Position.ABSOLUTE);
    }

    public static void addChild(Element parent, Element child) {
        DOM.appendChild(parent, child);
    }

    public static void setTop(Element e, int px) {
        e.getStyle().setTop(px, Style.Unit.PX);
    }

    public static void setLeft(Element e, int px) {
        e.getStyle().setLeft(px, Style.Unit.PX);
    }

    public static Integer getTop(Element e) {
        return px2Int(e.getStyle().getTop());
    }

    public static Integer getLeft(Element e) {
        return px2Int(e.getStyle().getLeft());
    }

    public static void setInnerHTML(Element e, CharSequence text) {
        //DOM.setElementProperty(e, "innerHTML", text);
        e.setPropertyString("innerHTML", text.toString());
    }

    public static int getRelativeLeft(Element parent, Element child) {
        return getLeft(parent) - getLeft(child);
    }

    public static int getRelativeTop(Element parent, Element child) {
        return getTop(parent) - getTop(child);
    }

    public static Element getElementById(CharSequence id) {
        return DOM.getElementById(id.toString());
    }

    /**
     * Gets an element by its tag name; handy for single elements like HTML,
     * HEAD, BODY.
     *
     * @param tagName The name of the tag.
     * @return The element with that tag name.
     */
    public native static Element getElementByTagName(CharSequence tagName) /*-{
  var elem = $doc.getElementsByTagName(tagName);
  return elem ? elem[0] : null;
}-*/;

    @Deprecated
    public static void addAbsolute(Widget child, TGS_ShapeRectangle<Integer> rectPx) {
        child.setSize(rectPx.width + "px", rectPx.height + "px");
        RootPanel.get().add(child, rectPx.x, rectPx.y);
    }

    @Deprecated
    public static void addAbsolute(Element parent, Element child, TGS_ShapeRectangle<Integer> rectPx) {
        TGC_DOMUtils.setPositionAbsolute(child);
        child.getStyle().setLeft(rectPx.x, Style.Unit.PX);
        child.getStyle().setTop(rectPx.y, Style.Unit.PX);
        child.getStyle().setWidth(rectPx.width, Style.Unit.PX);
        child.getStyle().setHeight(rectPx.height, Style.Unit.PX);
        parent.appendChild(child);
    }

    @Deprecated
    public static void addAbsolute(Element child, TGS_ShapeRectangle<Integer> rectPx) {
        addAbsolute(RootPanel.get().getElement(), child, rectPx);
    }

    @Deprecated
    public static void addAbsolute(String childId, TGS_ShapeRectangle<Integer> rectPx) {
        var child = DOM.getElementById(childId);
        addAbsolute(RootPanel.get().getElement(), child, rectPx);
    }
}
