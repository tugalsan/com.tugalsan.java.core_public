package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.tuple.client.TGS_Tuple4;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DMatrixUtils {

    private TGC_Canvas2DMatrixUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DMatrixUtils.class);
    public static Canvas reset(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setTransform(1, 0, 0, 1, 0, 0);
        return canvas;
    }

    public static Canvas flipHorizontal(Canvas canvas) {
        return scale(canvas, -1, 1);
    }

    public static Canvas flipVertical(Canvas canvas) {
        return scale(canvas, 1, -1);
    }

    public static Canvas scale(Canvas canvas, float x, float y) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.scale(x, y);
        return canvas;
    }

    public static Canvas translate(Canvas canvas, float x, float y) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.translate(x, y);
        return canvas;
    }

    public static Canvas transform(Canvas canvas, TGS_Tuple4<Float, Float, Float, Float> matrix_m11_m22, TGS_ShapeLocation<Float> d) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.transform(matrix_m11_m22.value0, matrix_m11_m22.value1, matrix_m11_m22.value2, matrix_m11_m22.value3, d.x, d.y);
        return canvas;
    }
}
