package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import java.util.stream.*;

public class TS_MathArrayScalarTGS_FuncMTU_OutBool_In2Utils {

    private TS_MathArrayScalarTGS_FuncMTU_OutBool_In2Utils() {

    }

    public static void bytes(boolean parallel,
            boolean[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutBool_In2<Byte, Byte> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void shorts(boolean parallel,
            boolean[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutBool_In2<Short, Short> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void integers(boolean parallel,
            boolean[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutBool_In2<Integer, Integer> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void longs(boolean parallel,
            boolean[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutBool_In2<Long, Long> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void floats(boolean parallel,
            boolean[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutBool_In2<Float, Float> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

    public static void doubles(boolean parallel,
            boolean[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutBool_In2<Double, Double> exeScalar) {
        if (parallel) {
            IntStream.range(0, inputA.length).parallel().forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        } else {
            IntStream.range(0, inputA.length).forEach(i -> output[i] = exeScalar.call(inputA[i], inputB[i]));
        }
    }

}
