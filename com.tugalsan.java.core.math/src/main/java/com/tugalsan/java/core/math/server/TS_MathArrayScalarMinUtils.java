package com.tugalsan.java.core.math.server;

import java.util.*;
import java.util.stream.*;

public class TS_MathArrayScalarMinUtils {

    private TS_MathArrayScalarMinUtils() {

    }

    public OptionalDouble floats_to_double(boolean parallel, float[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToDouble(i -> arr[i]).min();
        } else {
            return IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).min();
        }
    }

    public OptionalDouble doubles(boolean parallel, double[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().min();
        } else {
            return Arrays.stream(arr).min();
        }
    }

    public OptionalLong longs(boolean parallel, long[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().min();
        } else {
            return Arrays.stream(arr).min();
        }
    }

    public OptionalLong shorts_to_longs(boolean parallel, short[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).min();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).min();
        }
    }

    public OptionalLong bytes_to_longs(boolean parallel, byte[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).min();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).min();
        }
    }

    public OptionalInt integers(boolean parallel, int[] arr) {
        if (parallel) {
            return Arrays.stream(arr).parallel().min();
        } else {
            return Arrays.stream(arr).min();
        }
    }

    public OptionalLong integers_to_longs(boolean parallel, int[] arr) {
        if (parallel) {
            return IntStream.range(0, arr.length).parallel().mapToLong(i -> arr[i]).min();
        } else {
            return IntStream.range(0, arr.length).mapToLong(i -> arr[i]).min();
        }
    }

}
