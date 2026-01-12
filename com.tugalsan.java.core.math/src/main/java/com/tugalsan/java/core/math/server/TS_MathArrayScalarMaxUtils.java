package com.tugalsan.java.core.math.server;

import java.util.*;
import java.util.stream.*;

public class TS_MathArrayScalarMaxUtils {

    private TS_MathArrayScalarMaxUtils() {

    }

    public OptionalDouble floats_to_double(boolean parallel, float[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToDouble(i -> arr[i]).max();
        } else {
            return IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).max();
        }
    }

    public OptionalDouble doubles(boolean parallel, double[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().max();
        } else {
            return Arrays.stream(arr).max();
        }
    }

    public OptionalLong longs(boolean parallel, long[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().max();
        } else {
            return Arrays.stream(arr).max();
        }
    }

    public OptionalLong shorts_to_longs(boolean parallel, short[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).max();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).max();
        }
    }

    public OptionalLong bytes_to_longs(boolean parallel, byte[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).max();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).max();
        }
    }

    public OptionalInt integers(boolean parallel, int[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().max();
        } else {
            return Arrays.stream(arr).max();
        }
    }

    public OptionalLong integers_to_longs(boolean parallel, int[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).max();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).max();
        }
    }

}
