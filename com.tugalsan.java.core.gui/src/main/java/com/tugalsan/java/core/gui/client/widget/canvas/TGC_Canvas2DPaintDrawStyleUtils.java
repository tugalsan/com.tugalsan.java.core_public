package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class TGC_Canvas2DPaintDrawStyleUtils {

    private TGC_Canvas2DPaintDrawStyleUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintDrawStyleUtils.class);
    public static Canvas styleCap_BUTT(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineCap(Context2d.LineCap.BUTT);
        return canvas;
    }

    public static Canvas styleCap_ROUND(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineCap(Context2d.LineCap.ROUND);
        return canvas;
    }

    public static Canvas styleCap_SQUARE(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineCap(Context2d.LineCap.SQUARE);
        return canvas;
    }

    public static Canvas styleJoin_ROUND(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineJoin(Context2d.LineJoin.ROUND);
        return canvas;
    }

    public static Canvas styleJoin_BEVEL(Canvas canvas, Float optional_mitterLimit) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        if (optional_mitterLimit == null) {
            c2d.setLineJoin(Context2d.LineJoin.BEVEL);
            return canvas;
        }
        c2d.setLineJoin(Context2d.LineJoin.MITER);
        c2d.setMiterLimit(optional_mitterLimit);
        return canvas;
    }

    public static Canvas style(Canvas canvas, float width, CanvasGradient color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineWidth(width);
        c2d.setStrokeStyle(color);
        return canvas;
    }

    public static Canvas style(Canvas canvas, float width, CssColor color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineWidth(width);
        c2d.setStrokeStyle(color);
        c2d.setLineCap(Context2d.LineCap.BUTT);
        c2d.setLineJoin(Context2d.LineJoin.BEVEL);
        return canvas;
    }

    public static Canvas style(Canvas canvas, float width, CanvasPattern color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setLineWidth(width);
        c2d.setStrokeStyle(color);
        return canvas;
    }

}
