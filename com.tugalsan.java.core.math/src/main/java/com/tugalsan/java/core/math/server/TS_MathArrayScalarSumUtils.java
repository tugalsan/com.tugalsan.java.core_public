package com.tugalsan.java.core.math.server;

import java.util.*;
import java.util.stream.*;

public class TS_MathArrayScalarSumUtils {

    private TS_MathArrayScalarSumUtils() {

    }

    public double floats_to_double(boolean parallel, float[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToDouble(i -> arr[i]).sum();
        } else {
            return IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).sum();
        }
    }

    public double doubles(boolean parallel, double[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().sum();
        } else {
            return Arrays.stream(arr).sum();
        }
    }

    public long longs(boolean parallel, long[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().sum();
        } else {
            return Arrays.stream(arr).sum();
        }
    }

    public long shorts_to_longs(boolean parallel, short[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).sum();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).sum();
        }
    }

    public long bytes_to_longs(boolean parallel, byte[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).sum();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).sum();
        }
    }

    public int integers(boolean parallel, int[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().sum();
        } else {
            return Arrays.stream(arr).sum();
        }
    }

    public long integers_to_longs(boolean parallel, int[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).sum();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).sum();
        }
    }

}
