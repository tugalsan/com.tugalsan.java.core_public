package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;

public class TGC_Canvas2DPathUtils {

    private TGC_Canvas2DPathUtils() {

    }

    public static Canvas addPathRoundRect(Canvas canvas, TGS_ShapeRectangle<Integer> rect, float radiusInDegrees) {
        float midx = rect.x + rect.width / 2, midy = rect.y + rect.height / 2, maxx = rect.x + rect.width, maxy = rect.y + rect.height;
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.moveTo(rect.x, midy);
        c2d.arcTo(rect.x, rect.y, midx, rect.y, radiusInDegrees);
        c2d.arcTo(maxx, rect.y, maxx, midy, radiusInDegrees);
        c2d.arcTo(maxx, maxy, midx, maxy, radiusInDegrees);
        c2d.arcTo(rect.x, maxy, rect.x, midy, radiusInDegrees);
        return canvas;
    }
}
