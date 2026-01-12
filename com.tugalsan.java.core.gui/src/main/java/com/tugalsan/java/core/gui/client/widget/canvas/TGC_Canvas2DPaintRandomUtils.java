package com.tugalsan.java.core.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.color.client.TGC_ColorUtils;
import com.tugalsan.java.core.random.client.*;
import com.tugalsan.java.core.shape.client.TGS_ShapeArc;
import com.tugalsan.java.core.shape.client.TGS_ShapeDimension;
import com.tugalsan.java.core.shape.client.TGS_ShapeDimensionUtils;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import com.tugalsan.java.core.shape.client.TGS_ShapeMargin;
import com.tugalsan.java.core.stream.client.*;
import com.tugalsan.java.core.url.client.TGS_Url;
import java.util.stream.*;

public class TGC_Canvas2DPaintRandomUtils {

    private TGC_Canvas2DPaintRandomUtils() {

    }

    //img url TGS_LibResourceUtils.common.res.image.search.icon_png().toString()
    public static Canvas drawSomethingNew(Canvas canvas, TGS_ShapeDimension<Integer> canvasSize, TGS_Url url) {
        var previousImageDataScaled = TGC_Canvas2DImageUtils.toImageDataScaled(canvas, 0.20f, 0.20f);
        TGC_Canvas2DUtils.clear(canvas);
        TGC_Canvas2DPaintImageUtils.paint(canvas, previousImageDataScaled, new TGS_ShapeLocation(0, 0));
        var img = new Image();
        img.setUrl(url.toString());
        paintFillList(canvas, img);
        paintFillArc(canvas, img);
        paintFillRect(canvas, img);
        paintFillRectRadius(canvas, img);
        paintImage(canvas, img);
        paintText(canvas, canvasSize);
        return canvas;
    }

    public static Canvas paintText(Canvas canvas, TGS_ShapeDimension<Integer> canvasSize) {
        TGC_Canvas2DPaintTextUtils.paint(canvas, TGS_RandomUtils.nextLoc(canvasSize), TGS_RandomUtils.nextString(8, "ABCD"));
        return canvas;
    }

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintFillStyleUtils.class);
    public static Canvas styleDrawRandom(Canvas canvas) {
        switch (TGS_RandomUtils.nextInt(0, 2)) {
            case 0:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_BUTT(canvas);
                break;
            case 1:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_ROUND(canvas);
                break;
            case 2:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_SQUARE(canvas);
                break;
            default:
                break;
        }
        switch (TGS_RandomUtils.nextInt(0, 2)) {
            case 0:
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_ROUND(canvas);
                break;
            case 1:
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_BEVEL(canvas, null);
                break;
            case 2:
                var reso = TGC_CanvasUtils.getResolution(canvas);
                var min = Math.min(reso.width, reso.height) / 5;
                var mitterLimit = TGS_RandomUtils.nextFloat(1, min);
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_BEVEL(canvas, mitterLimit);
                break;
            default:
                break;
        }
        return canvas;
    }

    public static Canvas styleFillRandom(Canvas canvas, TGS_ShapeDimension<Integer> boundary, Image image) {
        switch (TGS_RandomUtils.nextInt(0, 7)) {
//            case 0:
//                d.ci("createGradiantLinear", "init");
//                var locLinearStart = TGS_RandomUtils.nextLoc(boundary);
//                var locLinearEnd = TGS_RandomUtils.nextLoc(boundary);
//                var offsetLinearColor = TGS_ListUtils.of(
//                        new TGS_Tuple2(0f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Tuple2(0.5f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Tuple2(1f, TGC_ColorUtils.createRandom(false))
//                );
//                d.ci("locLinearStart", locLinearStart);
//                d.ci("locLinearEnd", locLinearEnd);
//                d.ci("offsetLinearColor", offsetLinearColor);
//                var gradLinear = TGC_Canvas2DPaintStyleUtils.createGradiantLinear(canvas, locLinearStart, locLinearEnd, offsetLinearColor);
//                TGC_Canvas2DPaintFillStyleUtils.style(canvas, gradLinear);
//                break;
//            case 1:
//                d.ci("createGradiantRadial", "init");
//                var locRadialStart = new TGS_ShapeCircle(
//                        TGS_RandomUtils.nextLoc(boundary),
//                        TGS_RandomUtils.nextInt(0, Math.min(boundary.width, boundary.height) / 2)
//                );
//                var locRadialEnd = new TGS_ShapeCircle(
//                        TGS_RandomUtils.nextLoc(boundary),
//                        TGS_RandomUtils.nextInt(0, Math.min(boundary.width, boundary.height) / 2)
//                );
//                var offsetRadialColor = TGS_ListUtils.of(
//                        new TGS_Tuple2(0f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Tuple2(0.5f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Tuple2(1f, TGC_ColorUtils.createRandom(false))
//                );
//                d.ci("locRadialStart", locRadialStart);
//                d.ci("locRadialEnd", locRadialEnd);
//                d.ci("offsetRadialColor", offsetRadialColor);
//                var gradRadial = TGC_Canvas2DPaintStyleUtils.createGradiantRadial(canvas, locRadialStart, locRadialEnd, offsetRadialColor);
//                TGC_Canvas2DPaintFillStyleUtils.style(canvas, gradRadial);
//                break;
            case 2:
                var pattNone = TGC_Canvas2DPaintStyleUtils.createPatternRepeatNone(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattNone);
                break;
            case 3:
                var pattX = TGC_Canvas2DPaintStyleUtils.createPatternRepeatX(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattX);
                break;
            case 4:
                var pattY = TGC_Canvas2DPaintStyleUtils.createPatternRepeatY(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattY);
                break;
            case 5:
                var pattXY = TGC_Canvas2DPaintStyleUtils.createPatternRepeatXY(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattXY);
                break;
            case 6:
                var randomColor = TGC_ColorUtils.createRandom(false);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, randomColor);
                break;
            default:
                break;
        }
        return canvas;
    }

    public static Canvas paintImage(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var loc = TGS_RandomUtils.nextLoc(resolution);
        TGC_Canvas2DPaintImageUtils.paint(canvas, image, loc);
        return canvas;
    }

    public static Canvas paintFillList(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var locs = TGS_StreamUtils.toLst(
                IntStream.range(0, 4).mapToObj(i -> TGS_RandomUtils.nextLoc(resolution))
        );
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(locs), image);
        TGC_Canvas2DPaintFillUtils.paintList(canvas, locs);
        return canvas;
    }

    public static Canvas paintFillArc(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var loc = TGS_RandomUtils.nextLoc(resolution);
        TGS_ShapeMargin<Integer> margins = new TGS_ShapeMargin(
                loc.x,
                resolution.width - loc.x,
                loc.y,
                resolution.height - loc.y
        );
        var radiusMax = Math.min(Math.min(margins.left, margins.right), Math.min(margins.up, margins.down));
        var radius = TGS_RandomUtils.nextInt(1, radiusMax);
        TGS_ShapeLocation<Float> radiusDegrees_start_and_end = new TGS_ShapeLocation(
                TGS_RandomUtils.nextFloat(0, 360),
                TGS_RandomUtils.nextFloat(0, 360)
        );
        TGS_ShapeArc<Integer, Integer, Float> arc = new TGS_ShapeArc(
                loc.x, loc.y,
                radius,
                radiusDegrees_start_and_end.x,
                radiusDegrees_start_and_end.y
        );
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(radius), image);
        TGC_Canvas2DPaintFillUtils.paintArc(canvas, arc);
        return canvas;
    }

    public static Canvas paintFillRect(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var randRect = TGS_RandomUtils.nextRect(resolution);
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(randRect), image);
        TGC_Canvas2DPaintFillUtils.paintRect(canvas, randRect);
        return canvas;
    }

    public static Canvas paintFillRectRadius(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var randRect = TGS_RandomUtils.nextRect(resolution);
        var radius = Math.min(randRect.width, randRect.height) / 5;
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(randRect), image);
        TGC_Canvas2DPaintFillUtils.paintRectRadius(canvas, randRect, radius);
        return canvas;
    }

}
