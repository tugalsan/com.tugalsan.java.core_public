package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.CssColor;

public class TGC_Canvas2DPaintFillStyleUtils {

    private TGC_Canvas2DPaintFillStyleUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintFillStyleUtils.class);
    public static Canvas style(Canvas canvas, CssColor color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setFillStyle(color);
        return canvas;
    }

    public static Canvas style(Canvas canvas, CanvasGradient color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setFillStyle(color);
        return canvas;
    }

    public static Canvas style(Canvas canvas, CanvasPattern color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setFillStyle(color);
        return canvas;
    }
}
