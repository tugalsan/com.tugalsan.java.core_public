package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.CanvasElement;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;

public class TGC_Canvas2DUtils {

    private TGC_Canvas2DUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DUtils.class);
    public static CanvasElement toCanvasElement(Canvas canvas) {
        var c2d = toContext2d(canvas);
        return c2d.getCanvas();
    }

    public static Context2d toContext2d(Canvas canvas) {
        return canvas.getContext2d();
    }

    public static Canvas clear(Canvas canvas) {
        var res = TGC_CanvasUtils.getResolution(canvas);
        return clear(canvas, TGS_ShapeRectangle.of(0, 0, res.width, res.height));
    }

    public static Canvas clear(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = toContext2d(canvas);
        c2d.clearRect(rect.x, rect.y, rect.width, rect.height);
        return canvas;
    }

    public static ImageData save(Canvas canvas) {
        var res = TGC_CanvasUtils.getResolution(canvas);
        return canvas.getContext2d().getImageData(0, 0, res.width, res.height);
    }

    public static boolean restore(ImageData savedImageData, Canvas canvas) {
        if (savedImageData == null) {
            return false;
        }
        var res = TGC_CanvasUtils.getResolution(canvas);
        canvas.getContext2d().clearRect(0, 0, res.width, res.height);
        canvas.getContext2d().putImageData(savedImageData, 0, 0);
        return true;
    }

}
