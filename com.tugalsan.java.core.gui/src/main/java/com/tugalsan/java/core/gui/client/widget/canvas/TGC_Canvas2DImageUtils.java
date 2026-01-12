package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.ImageData;
import com.tugalsan.java.core.gui.client.widget.TGC_ImageUtils;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.shape.client.TGS_ShapeDimension;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;

public class TGC_Canvas2DImageUtils {

    private TGC_Canvas2DImageUtils() {

    }

    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DImageUtils.class);

    public static TGS_ShapeDimension<Integer> getDimension(ImageData imageData) {
        return new TGS_ShapeDimension(imageData.getWidth(), imageData.getHeight());
    }

    public static ImageData toImageData(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        return c2d.getImageData(rect.x, rect.y, rect.width, rect.height);
    }

    public static ImageData toImageData(Canvas canvas) {
        var dim = TGC_CanvasUtils.getResolution(canvas);
        return toImageData(canvas, TGS_ShapeRectangle.of(0, 0, dim.width, dim.height));
    }

    public static ImageData toImageDataScaled(Canvas canvasSource, float scaleX, float scaleY) {
        var dimSource = TGC_CanvasUtils.getDimension(canvasSource);
        var dimScaled = dimSource.cloneIt();
        dimScaled.set(Math.round(dimSource.width * scaleX), Math.round(dimSource.height * scaleY));
        var canvasScaled = TGC_CanvasUtils.createCanvas();
        TGC_ImageUtils.setCrossOriginAllow(canvasScaled.getElement());
        TGC_CanvasUtils.setResolution(canvasScaled, dimScaled);
        TGC_Canvas2DMatrixUtils.scale(canvasScaled, scaleX, scaleY);
        TGC_Canvas2DPaintImageUtils.paint(canvasScaled, canvasSource, new TGS_ShapeLocation(0, 0));
        return toImageData(canvasScaled, TGS_ShapeRectangle.of(0, 0, dimSource.width, dimSource.height));
    }

    @Deprecated//NOT WORKING, IT ONLY CROPS
    public static ImageData toImageDataScaled(ImageData imageData, float scaleX, float scaleY) {
        var dimSource = getDimension(imageData);
        var dimScaled = dimSource.cloneIt();
        dimScaled.set(Math.round(dimSource.width * scaleX), Math.round(dimSource.height * scaleY));
        var canvasScaled = TGC_CanvasUtils.createCanvas();
        TGC_CanvasUtils.setResolution(canvasScaled, dimScaled);
        TGC_Canvas2DMatrixUtils.scale(canvasScaled, scaleX, scaleY);
        TGC_Canvas2DPaintImageUtils.paint(canvasScaled, imageData, new TGS_ShapeLocation(0, 0));
        return TGC_Canvas2DImageUtils.toImageData(canvasScaled, TGS_ShapeRectangle.of(0, 0, dimSource.width, dimSource.height));
    }

    public static void filterGrayScale(ImageData imageData) {
        for (var x = 0; x < imageData.getWidth(); ++x) {
            for (var y = 0; y < imageData.getHeight(); ++y) {
                var average = (imageData.getRedAt(x, y) + imageData.getGreenAt(x, y) + imageData.getBlueAt(x, y)) / 3;
                imageData.setRedAt(average, x, y);
                imageData.setGreenAt(average, x, y);
                imageData.setBlueAt(average, x, y);
            }
        }
    }

    public static void filterInvert(ImageData imageData) {
        for (var x = 0; x < imageData.getWidth(); ++x) {
            for (var y = 0; y < imageData.getHeight(); ++y) {
                imageData.setRedAt(255 - imageData.getRedAt(x, y), x, y);
                imageData.setGreenAt(255 - imageData.getGreenAt(x, y), x, y);
                imageData.setBlueAt(255 - imageData.getBlueAt(x, y), x, y);
            }
        }
    }

    public static String createImageUrl(TGS_ShapeDimension<Integer> dim, CssColor bg) {
        if (!TGC_CanvasUtils.isSupported()) {
            return null;
        }
        var c = TGC_CanvasUtils.toCanvas(dim);
        TGC_Canvas2DPaintFillStyleUtils.style(c, bg);
        TGC_Canvas2DPaintFillUtils.paintRect(c, TGS_ShapeRectangle.of(0, 0, dim.width, dim.height));
        return TGC_CanvasUtils.toUrl(c);
    }

    public static String createTextImageUrl(TGS_ShapeDimension<Integer> dim, TGS_ShapeLocation<Integer> loc,
            CssColor fg, CharSequence text, float scale) {
        if (!TGC_CanvasUtils.isSupported()) {
            return null;
        }
        d.ci("createTextImageUrl", "canvas supported");
        var c = TGC_CanvasUtils.toCanvas(dim);
        d.ci("createTextImageUrl", "setting color fore");
        TGC_Canvas2DPaintFillStyleUtils.style(c, fg);
        d.ci("createTextImageUrl", "write text");
        TGC_Canvas2DPaintTextUtils.paint(c, loc, text);
        d.ci("createTextImageUrl", "scale");
        TGC_Canvas2DMatrixUtils.scale(c, scale, scale);
        d.ci("createTextImageUrl", "create url");
        var url = TGC_CanvasUtils.toUrl(c);
        d.ci("createTextImageUrl", "fin as", url);
        return url;
    }
}
