package com.tugalsan.java.core.gui.gl.client.three4g.common;

import java.util.stream.IntStream;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.math.Vector2;

public class TGC_GLMath {//EXTEND MATH LIB ON DEMAND

    final public TGC_GLProgramAbstract program;

    public TGC_GLMath(TGC_GLProgramAbstract program) {
        this.program = program;
    }

    public float getRadians(float degrees) {
        return getRadians(degrees, true);
    }

    public float getRadians(float degrees, boolean simplify) {
        if (simplify) {
            degrees %= 360;
            while (degrees < 0) {
                degrees += 360;
            }
        }
//        return 2f * degrees * (float)Math.PI / 180f;
        return org.treblereel.gwt.three4g.math.Math.degToRad(degrees);
    }

    public float getDegrees(float radians) {
        return getDegrees(radians, true);
    }

    public float getDegrees(float radians, boolean simplify) {
        if (simplify) {
            radians %= radians360;
            while (radians < 0) {
                radians += radians360;
            }
        }
        return org.treblereel.gwt.three4g.math.Math.radToDeg(radians);
    }
    private final float radians360 = org.treblereel.gwt.three4g.math.Math.degToRad(360);

    public float randomFloat(float low, float high) {
        return org.treblereel.gwt.three4g.math.Math.randFloat(low, high);
    }

    public float randomInt(int low, int high) {
        return org.treblereel.gwt.three4g.math.Math.randInt(low, high);
    }

    public float clamp(float min, float max, float value) {
        return org.treblereel.gwt.three4g.math.Math.clamp(value, min, max);

    }

    public int powInt(int x, int y) {
        return (int) Math.pow(x, y);
    }

    public Vector2[] createVector2_DemoLathePoints() {
        var points = new Vector2[10];
        IntStream.range(0, points.length).parallel().forEach(i -> {
            points[i] = new Vector2((float) Math.sin(i * 0.2) * 10 + 5, (i - 5) * 2);
        });
        return points;
    }
}
