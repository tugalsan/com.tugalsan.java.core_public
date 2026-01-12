package com.tugalsan.java.core.random.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.hex;
import module com.tugalsan.java.core.shape;
import com.tugalsan.java.core.random.client.core.TGS_RandomDriverUtils;
import com.tugalsan.java.core.random.server.core.TS_UUIDType5Utils;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;

public class TS_RandomUtils {

    public static String getUUIDType5(String seed) {
        return TS_UUIDType5Utils.run(seed).toString();
    }

    public static String nextUUIDType4() {
        return TGS_FuncMTCUtils.call(() -> {
            var salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            return TGS_HexUtils.toHex(salt.digest());
        });
    }

    public static final Random driver() {//THREAD SAFE
        return ThreadLocalRandom.current();
    }

    public static TGS_ShapeLocation<Integer> nextLoc(TGS_ShapeDimension<Integer> boundary) {
        return TGS_RandomDriverUtils.nextLoc(driver(), boundary);
    }

    public static TGS_ShapeLocation<Integer> nextLoc(TGS_ShapeRectangle<Integer> boundary) {
        return TGS_RandomDriverUtils.nextLoc(driver(), boundary);
    }

    public static TGS_ShapeRectangle<Integer> nextRect(TGS_ShapeDimension<Integer> boundary) {
        return TGS_RandomDriverUtils.nextRect(driver(), TGS_ShapeRectangle.of(0, 0, boundary.width, boundary.height));
    }

    public static TGS_ShapeRectangle<Integer> nextRect(TGS_ShapeRectangle<Integer> boundaryRect) {
        return TGS_RandomDriverUtils.nextRect(driver(), TGS_ShapeRectangle.of(0, 0, boundaryRect.width, boundaryRect.height));
    }

    @Deprecated //FLOAT IS YOUR ENEMY!
    public static float nextFloat(float min, float max) {
        return TGS_RandomDriverUtils.nextFloat(driver(), min, max);
    }

    public static double nextDouble(double min, double max) {
        return TGS_RandomDriverUtils.nextDouble(driver(), min, max);
    }

    public static boolean nextBoolean() {
        return TGS_RandomDriverUtils.nextBoolean(driver());
    }

    public static long nextLong(long min, long max) {
        return TGS_RandomDriverUtils.nextLong(driver(), min, max);
    }

    public static int nextInt(int min, int max) {
        return TGS_RandomDriverUtils.nextInt(driver(), min, max);
    }

    public static String nextString(int charSize, boolean numberChars, boolean smallChars, boolean bigChars, boolean alphaChars, CharSequence customChars) {
        return TGS_RandomDriverUtils.nextString(driver(), charSize, numberChars, smallChars, bigChars, alphaChars, customChars);
    }

    public static String nextString(int charSize, CharSequence alphabet) {
        return TGS_RandomDriverUtils.nextString(driver(), charSize, alphabet);
    }

    public static int[] nextIntArray(int size, int minValue, int maxValue) {
        return TGS_RandomDriverUtils.nextIntArray(driver(), size, minValue, maxValue);
    }
}
