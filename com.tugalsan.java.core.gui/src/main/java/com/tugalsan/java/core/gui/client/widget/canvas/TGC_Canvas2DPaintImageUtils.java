package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.tugalsan.java.core.gui.client.widget.TGC_ImageUtils;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DPaintImageUtils {

    private TGC_Canvas2DPaintImageUtils() {

    }

    public static Canvas paint(Canvas canvas, Canvas imageData, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        TGC_ImageUtils.setCrossOriginAllow(imageData.getElement());
        var ce = TGC_Canvas2DUtils.toCanvasElement(imageData);
        c2d.drawImage(ce, loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, ImageData imageData, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.putImageData(imageData, loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, ImageElement imageElement, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        imageElement.setAttribute("crossOrigin", "Anonymous");//Anonymous or ''
        c2d.drawImage(imageElement, loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, Image image, TGS_ShapeLocation<Integer> loc) {
        paint(canvas, TGC_ImageUtils.toImageElement(image), loc);
        return canvas;
    }
}
