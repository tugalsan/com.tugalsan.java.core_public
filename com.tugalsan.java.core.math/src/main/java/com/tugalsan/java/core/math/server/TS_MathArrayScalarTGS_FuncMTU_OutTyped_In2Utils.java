package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import java.util.stream.*;

public class TS_MathArrayScalarTGS_FuncMTU_OutTyped_In2Utils {

    private TS_MathArrayScalarTGS_FuncMTU_OutTyped_In2Utils() {

    }

    public static void bytes(boolean parallel,
            byte[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void shorts(boolean parallel,
            short[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutTyped_In2<Short, Short, Short> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void integers(boolean parallel,
            int[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutTyped_In2<Integer, Integer, Integer> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void longs(boolean parallel,
            long[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutTyped_In2<Long, Long, Long> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void floats(boolean parallel,
            float[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutTyped_In2<Float, Float, Float> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void doubles(boolean parallel,
            double[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutTyped_In2<Double, Double, Double> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

}
