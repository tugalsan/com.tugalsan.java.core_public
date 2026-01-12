package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DPaintTextUtils {

    private TGC_Canvas2DPaintTextUtils() {

    }

    public static Canvas paint(Canvas canvas, TGS_ShapeLocation<Integer> loc, CharSequence text) {
        return paint(canvas, loc, text, null);
    }

    public static Canvas paint(Canvas canvas, TGS_ShapeLocation<Integer> loc, CharSequence text, CharSequence font) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        if (font != null) {
            c2d.setFont(font.toString());
        }
        c2d.fillText(text.toString(), loc.x, loc.y);
        return canvas;
    }
}
