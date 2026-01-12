package com.tugalsan.java.core.color.client;

import com.google.gwt.canvas.dom.client.*;
import com.tugalsan.java.core.random.client.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_ColorUtils {

    public static CssColor createHSLRandom1() {
        return createHSL(
                TGS_RandomUtils.nextInt(0, 360),
                TGS_RandomUtils.nextInt(0, 20) + 10,//Saturation between 0.1 and 0.3
                90
        );
    }

    public static CssColor createHSLRandomLight() {
        return createHSL(
                TGS_RandomUtils.nextInt(0, 360),
                90,
                100
        );
    }

    public static CssColor createHSL(int hue_0_360, int saturation_0_100, int lightness_0_100) {
        return CssColor.make(TGS_StringUtils.cmn().concat(
                "hsl(",
                String.valueOf(hue_0_360), ", ",
                String.valueOf(saturation_0_100), "%,",
                String.valueOf(lightness_0_100), "%",
                ")"
        ));
    }

    public static CssColor createRandom(int limit) {
        return CssColor.make(
                TGS_RandomUtils.nextInt(limit, 255 - limit),
                TGS_RandomUtils.nextInt(limit, 255 - limit),
                TGS_RandomUtils.nextInt(limit, 255 - limit)
        );
    }

    public static CssColor create(int red, int green, int blue, float alpha) {
        alpha *= 100;
        alpha = Math.round(alpha) / 100f;
        return CssColor.make(TGS_StringUtils.cmn().concat(
                "rgba(",
                String.valueOf(red), ", ",
                String.valueOf(green), ",",
                String.valueOf(blue), ", ",
                String.valueOf(alpha),
                ")"
        ));
    }

    public static CssColor createRandom(boolean opaque) {
        var r = TGS_RandomUtils.nextInt(0, 255);
        var g = TGS_RandomUtils.nextInt(0, 255);
        var b = TGS_RandomUtils.nextInt(0, 255);
        var a = opaque ? 1 : TGS_RandomUtils.nextFloat(0, 1);
        return create(r, g, b, a);
    }
}
