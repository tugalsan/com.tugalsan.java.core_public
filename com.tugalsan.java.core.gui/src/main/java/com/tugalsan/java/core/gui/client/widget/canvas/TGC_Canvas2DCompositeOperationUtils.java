package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

public class TGC_Canvas2DCompositeOperationUtils {

    private TGC_Canvas2DCompositeOperationUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DCompositeOperationUtils.class);
    public static Canvas setCompositeOperation_COPY(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.COPY);
        return canvas;
    }

    public static Canvas setCompositeOperation_DESTINATION_ATOP(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.DESTINATION_ATOP);
        return canvas;
    }

    public static Canvas setCompositeOperation_DESTINATION_IN(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.DESTINATION_IN);
        return canvas;
    }

    public static Canvas setCompositeOperation_DESTINATION_OUT(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.DESTINATION_OUT);
        return canvas;
    }

    public static Canvas setCompositeOperation_DESTINATION_OVER(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.DESTINATION_OVER);
        return canvas;
    }

    public static Canvas setCompositeOperation_LIGHTER(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.LIGHTER);
        return canvas;
    }

    public static Canvas setCompositeOperation_SOURCE_ATOP(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.SOURCE_ATOP);
        return canvas;
    }

    public static Canvas setCompositeOperation_SOURCE_IN(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.SOURCE_IN);
        return canvas;
    }

    public static Canvas setCompositeOperation_SOURCE_OUT(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.SOURCE_OUT);
        return canvas;
    }

    public static Canvas setCompositeOperation_SOURCE_OVER(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.SOURCE_OVER);
        return canvas;
    }

    public static Canvas setCompositeOperation_XOR(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setGlobalCompositeOperation(Context2d.Composite.XOR);
        return canvas;
    }
}
