package com.tugalsan.java.core.color.client;

import com.tugalsan.java.core.hex.client.*;

public class TGS_ColorUtils {

    public static String toHex(TGS_Color colorRGB) {
        return TGS_HexUtils.toHex(new int[]{colorRGB.r, colorRGB.g, colorRGB.b});
    }

    public static String toHexInverted(CharSequence hex) {
        var rgb = toRGB(hex);
        return toHex(new TGS_Color(
                255 - rgb.r,
                255 - rgb.g,
                255 - rgb.b
        ));
    }

    public static TGS_Color toRGB(CharSequence colorHex) {
        var b = TGS_HexUtils.toInt(colorHex);
        return TGS_Color.of(b[2], b[1], b[0]);
    }

    public static String toRGBString(int red0_255, int green0_255, int blue0_255) {
        return "rgb(" + red0_255 + "," + green0_255 + "," + blue0_255 + ")";
    }
    
    public static TGS_Color HSBtoRGB(int hue0_360, int saturation0_100, int brightness0_100) {
        int r = 0, g = 0, b = 0;
        brightness0_100 = (int) (brightness0_100 * 2.55);
        if (saturation0_100 == 0) {
            r = g = b = brightness0_100;
        } else {
            var h = hue0_360 / 60.0;
            var f = h - Math.floor(h);
            var p = (brightness0_100 * (100 - saturation0_100)) / 100;
            var q = (int) ((brightness0_100 * (100 - (saturation0_100 * f))) / 100);
            var t = (int) (brightness0_100 * (100 - (saturation0_100 * (1.0 - f)))) / 100;
            switch ((int) h) {
                case 0:
                    r = brightness0_100;
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = brightness0_100;
                    b = p;
                    break;
                case 2:
                    r = p;
                    g = brightness0_100;
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = brightness0_100;
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = brightness0_100;
                    break;
                case 5:
                    r = brightness0_100;
                    g = p;
                    b = q;
                    break;
            }
        }
        return TGS_Color.of(r, g, b);
    }
}
