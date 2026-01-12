package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.shape.client.TGS_ShapeArc;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;
import java.util.List;
import java.util.stream.IntStream;

public class TGC_Canvas2DPaintDrawUtils {

    private TGC_Canvas2DPaintDrawUtils() {

    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintDrawUtils.class);
    public static Canvas paintLine(Canvas canvas, TGS_ShapeLocation<Integer> locStart, TGS_ShapeLocation<Integer> locEnd) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.moveTo(locStart.x, locStart.y);
        c2d.lineTo(locEnd.x, locEnd.y);
        c2d.closePath();
        c2d.stroke();
        return canvas;
    }

    public static Canvas paintListLines(Canvas canvas, List<TGS_ShapeLocation<Integer>> locs) {
        if (locs.isEmpty()) {
            return canvas;
        }
        if (locs.size() == 1) {
            paintPoint(canvas, locs.get(0));
            return canvas;
        }
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.moveTo(locs.get(0).x, locs.get(0).y);
        IntStream.range(1, locs.size()).forEachOrdered(i -> c2d.lineTo(locs.get(i).x, locs.get(i).y));
        c2d.closePath();
        c2d.stroke();
        return canvas;
    }

    public static Canvas paintPoint(Canvas canvas, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.fillRect(loc.x, loc.y, 1, 1);
        return canvas;
    }

    public static Canvas paintListPoints(Canvas canvas, List<TGS_ShapeLocation<Integer>> locs) {
        if (locs.isEmpty()) {
            return canvas;
        }
        IntStream.range(0, locs.size()).forEachOrdered(i -> paintPoint(canvas, locs.get(i)));
        return canvas;
    }

    public static Canvas paintArc(Canvas canvas, TGS_ShapeArc<Integer, Integer, Float> arc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.arc(arc.x, arc.y, arc.radius, Math.toRadians(arc.angleInDegrees_start), Math.toRadians(arc.angleInDegrees_end));
        c2d.closePath();
        c2d.stroke();
        return canvas;
    }

    public static Canvas paintCirle(Canvas canvas, TGS_ShapeLocation<Integer> loc, int radius) {
        TGS_ShapeLocation<Float> degrees = TGS_ShapeLocation.of(0f, 360f);
        TGS_ShapeArc<Integer, Integer, Float> arc = TGS_ShapeArc.of(loc, radius, degrees);
        return paintArc(canvas, arc);
    }

    public static Canvas paintRect(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.strokeRect(rect.x, rect.y, rect.width, rect.height);
        return canvas;
    }

    public static Canvas paintRectRadius(Canvas canvas, TGS_ShapeRectangle<Integer> rect, float radius) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        TGC_Canvas2DPathUtils.addPathRoundRect(canvas, rect, radius);
        c2d.closePath();
        c2d.stroke();
        return canvas;
    }

}
