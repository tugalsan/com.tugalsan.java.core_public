package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;

public class TGC_Canvas2DClipUtils {

    private TGC_Canvas2DClipUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DClipUtils.class);
    public static Canvas clipPathRect(Canvas canvas, float x, float y, float width, float height) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.rect(x, y, width, height);
        c2d.clip();
        return canvas;
    }

    public static Canvas clipPath(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.clip();
        return canvas;
    }

}
