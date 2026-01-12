package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.Image;
import com.tugalsan.java.core.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.java.core.shape.client.TGS_ShapeDimension;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;

public class TGC_CanvasUtils {

    private TGC_CanvasUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_CanvasUtils.class);
    public static boolean isSupported() {
        return createCanvas() != null;
    }

    public static Canvas createCanvas() {
        return Canvas.createIfSupported();
    }

    public static Canvas toCanvas(Image backgroundImage) {
        var dim = new TGS_ShapeDimension(backgroundImage.getWidth(), backgroundImage.getHeight());
        var c = TGC_CanvasUtils.toCanvas(dim);
        TGC_Canvas2DPaintImageUtils.paint(c, backgroundImage, new TGS_ShapeLocation(0, 0));
        return c;
    }

    public static Canvas toCanvas(TGS_ShapeDimension<Integer> dim) {
        var c = createCanvas();
        setResolution(c, dim);
        return c;
    }

    public static Canvas setResolution(Canvas c, TGS_ShapeDimension<Integer> dim) {
        c.setCoordinateSpaceWidth(dim.width);
        c.setCoordinateSpaceHeight(dim.height);
        return c;
    }

    public static Canvas setDimension(Canvas c, TGS_ShapeDimension<Integer> dim) {
        c.setWidth(dim.width + "px");
        c.setHeight(dim.height + "px");
        return c;
    }

    public static Canvas setMagnify(Canvas c, float magnify) {
        var dim = getDimension(c);
        if (dim.width == null || dim.height == null) {
            return c;
        }
        dim.set(Math.round(dim.width * magnify), Math.round(dim.height * magnify));
        setResolution(c, dim);
        return c;
    }

    public static float getMagnify(Canvas c) {
        var dim = getDimension(c);
        var res = getResolution(c);
        if (dim.width == null || dim.height == null || res.width == null || res.height == null) {
            return -1;
        }
        var ratioW = 1f * res.width / dim.width;
        var ratioH = 1f * res.height / dim.height;
        return Math.round((ratioW + ratioH) / 2);
    }

    public static TGS_ShapeDimension<Integer> getResolution(Canvas c) {
        return new TGS_ShapeDimension(c.getCoordinateSpaceWidth(), c.getCoordinateSpaceHeight());
    }

    public static TGS_ShapeDimension<Integer> getDimension(Canvas c) {
        return new TGS_ShapeDimension(TGC_DOMUtils.getWidth(c.getElement()), TGC_DOMUtils.getHeight(c.getElement()));
    }

    public static String toUrl(Canvas canvas) {
        return canvas.toDataUrl();
    }

}
