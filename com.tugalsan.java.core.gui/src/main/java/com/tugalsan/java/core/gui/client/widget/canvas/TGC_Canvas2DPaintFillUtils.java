package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.log.client.TGC_Log;
import com.tugalsan.java.core.shape.client.TGS_ShapeArc;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;
import java.util.List;
import java.util.stream.IntStream;

public class TGC_Canvas2DPaintFillUtils {

    private TGC_Canvas2DPaintFillUtils() {

    }

    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintFillUtils.class);

    public static Canvas paintList(Canvas canvas, List<TGS_ShapeLocation<Integer>> locs) {
        if (locs.size() < 3) {
            return canvas;
        }
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.moveTo(locs.get(0).x, locs.get(0).y);
        IntStream.range(1, locs.size()).forEachOrdered(i -> c2d.lineTo(locs.get(i).x, locs.get(i).y));
        c2d.closePath();
        c2d.fill();
        return canvas;
    }

    public static Canvas paintArc(Canvas canvas, TGS_ShapeArc<Integer, Integer, Float> arc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        c2d.arc(arc.x, arc.y, arc.radius, Math.toRadians(arc.angleInDegrees_start), Math.toRadians(arc.angleInDegrees_end));
        c2d.closePath();
        c2d.fill();
        return canvas;
    }

    public static Canvas paintCirle(Canvas canvas, TGS_ShapeLocation<Integer> loc, int radius) {
        TGS_ShapeLocation<Float> degrees = TGS_ShapeLocation.of(0f, 360f);
        TGS_ShapeArc<Integer, Integer, Float> arc = TGS_ShapeArc.of(loc, radius, degrees);
        return paintArc(canvas, arc);
    }

    public static Canvas paintRect(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        d.ci("paintRect", "init");
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        d.ci("paintRect", "c2d", c2d);
        c2d.fillRect(rect.x, rect.y, rect.width, rect.height);
        d.ci("paintRect", "fin");
        return canvas;
    }

    @Deprecated //CANNOT READ PROPERTIES OF NULL
    public static Canvas paintAll(Canvas canvas) {
        d.ci("paintAll", "init");
        var loc = new TGS_ShapeLocation(0, 0);
        d.ci("paintAll", "loc", loc);
        var dim = TGC_CanvasUtils.getDimension(canvas);//WHY
        d.ci("paintAll", "dim", dim);
        var rect = TGS_ShapeRectangle.of(loc, dim);
        d.ci("paintAll", "rect", rect);
        return paintRect(canvas, rect);
    }

    public static Canvas paintRectRadius(Canvas canvas, TGS_ShapeRectangle<Integer> rect, float radius) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.beginPath();
        TGC_Canvas2DPathUtils.addPathRoundRect(canvas, rect, radius);
        c2d.closePath();
        c2d.fill();
        return canvas;
    }
}
