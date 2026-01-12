package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;
import elemental2.dom.CSSProperties;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import java.util.Objects;

public class TGC_GLDomUtils {

    public static void setLocationAndDimension(HTMLElement e, TGS_ShapeRectangle p) {
        e.style.position = "absolute";
        e.style.left = p.x + "px";
        e.style.top = p.y + "px";
        e.style.width = CSSProperties.WidthUnionType.of(p.width + "px");
        e.style.height = CSSProperties.HeightUnionType.of(p.height + "px");
    }

    public static void addToBody(HTMLDivElement e) {
        DomGlobal.document.body.appendChild(e);
    }

    public static void setVisible(HTMLElement e, boolean visible) {
        var vh = visible ? "visible" : "hidden";
        if (!Objects.equals(e.style.visibility, vh)) {
            e.style.visibility = vh;
        }
    }

    public static HTMLDivElement createDiv() {
        return (HTMLDivElement) DomGlobal.document.createElement("div");
    }
}
