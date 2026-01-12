package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import module jdk.incubator.vector;

public class TS_MathArrayVectorTGS_FuncMTU_OutBool_In2Utils {

    private TS_MathArrayVectorTGS_FuncMTU_OutBool_In2Utils() {

    }

    public static void bytes(TS_MathArrayVectorTypes vectorType,
            boolean[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Byte>, ByteVector, ByteVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Byte, Byte> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                bytes(false, TS_MathArrayVectorTypesUtils.bytes(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                bytes(true, TS_MathArrayVectorTypesUtils.bytes(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void bytes(boolean useMask, VectorSpecies<Byte> species,
            boolean[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Byte>, ByteVector, ByteVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Byte, Byte> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = ByteVector.fromArray(species, inputA, i, mask);
                var v2 = ByteVector.fromArray(species, inputB, i, mask);
                VectorMask<Byte> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = ByteVector.fromArray(species, inputA, i);
                var v2 = ByteVector.fromArray(species, inputB, i);
                VectorMask<Byte> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

    public static void shorts(TS_MathArrayVectorTypes vectorType,
            boolean[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Short>, ShortVector, ShortVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Short, Short> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                shorts(false, TS_MathArrayVectorTypesUtils.shorts(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                shorts(true, TS_MathArrayVectorTypesUtils.shorts(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void shorts(boolean useMask, VectorSpecies<Short> species,
            boolean[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Short>, ShortVector, ShortVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Short, Short> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = ShortVector.fromArray(species, inputA, i, mask);
                var v2 = ShortVector.fromArray(species, inputB, i, mask);
                VectorMask<Short> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = ShortVector.fromArray(species, inputA, i);
                var v2 = ShortVector.fromArray(species, inputB, i);
                VectorMask<Short> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

    public static void integers(TS_MathArrayVectorTypes vectorType,
            boolean[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Integer>, IntVector, IntVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Integer, Integer> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                integers(false, TS_MathArrayVectorTypesUtils.integers(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                integers(true, TS_MathArrayVectorTypesUtils.integers(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void integers(boolean useMask, VectorSpecies<Integer> species,
            boolean[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Integer>, IntVector, IntVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Integer, Integer> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = IntVector.fromArray(species, inputA, i, mask);
                var v2 = IntVector.fromArray(species, inputB, i, mask);
                VectorMask<Integer> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = IntVector.fromArray(species, inputA, i);
                var v2 = IntVector.fromArray(species, inputB, i);
                VectorMask<Integer> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

    public static void longs(TS_MathArrayVectorTypes vectorType,
            boolean[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Long>, LongVector, LongVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Long, Long> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                longs(false, TS_MathArrayVectorTypesUtils.longs(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                longs(true, TS_MathArrayVectorTypesUtils.longs(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void longs(boolean useMask, VectorSpecies<Long> species,
            boolean[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Long>, LongVector, LongVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Long, Long> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = LongVector.fromArray(species, inputA, i, mask);
                var v2 = LongVector.fromArray(species, inputB, i, mask);
                VectorMask<Long> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = LongVector.fromArray(species, inputA, i);
                var v2 = LongVector.fromArray(species, inputB, i);
                VectorMask<Long> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

    public static void floats(TS_MathArrayVectorTypes vectorType,
            boolean[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Float>, FloatVector, FloatVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Float, Float> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                floats(false, TS_MathArrayVectorTypesUtils.floats(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                floats(true, TS_MathArrayVectorTypesUtils.floats(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void floats(boolean useMask, VectorSpecies<Float> species,
            boolean[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Float>, FloatVector, FloatVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Float, Float> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = FloatVector.fromArray(species, inputA, i, mask);
                var v2 = FloatVector.fromArray(species, inputB, i, mask);
                VectorMask<Float> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = FloatVector.fromArray(species, inputA, i);
                var v2 = FloatVector.fromArray(species, inputB, i);
                VectorMask<Float> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

    public static void doubles(TS_MathArrayVectorTypes vectorType,
            boolean[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Double>, DoubleVector, DoubleVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Double, Double> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                doubles(false, TS_MathArrayVectorTypesUtils.doubles(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                doubles(true, TS_MathArrayVectorTypesUtils.doubles(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void doubles(boolean useMask, VectorSpecies<Double> species,
            boolean[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutTyped_In2<VectorMask<Double>, DoubleVector, DoubleVector> exeVector,
            TGS_FuncMTU_OutBool_In2<Double, Double> exeScalar) {
        var i = 0;
        if (useMask) {
            var result = new boolean[inputA.length + species.length()];
            for (; i < inputA.length; i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var v1 = DoubleVector.fromArray(species, inputA, i, mask);
                var v2 = DoubleVector.fromArray(species, inputB, i, mask);
                VectorMask<Double> v3 = exeVector.call(v1, v2);
                v3.intoArray(result, i);
            }
            System.arraycopy(result, 0, output, 0, inputA.length);
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var v1 = DoubleVector.fromArray(species, inputA, i);
                var v2 = DoubleVector.fromArray(species, inputB, i);
                VectorMask<Double> v3 = exeVector.call(v1, v2);
                v3.intoArray(output, i);
            }
            for (; i < inputA.length; i++) {
                output[i] = exeScalar.call(inputA[i], inputB[i]);
            }
        }
    }

}
