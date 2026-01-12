package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import module jdk.incubator.vector;

public class TS_MathArrayVectorFunc_OutTyped_In2Utils {

    private TS_MathArrayVectorFunc_OutTyped_In2Utils() {

    }

    public static void bytes(TS_MathArrayVectorTypes vectorType,
            byte[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                bytes(false, TS_MathArrayVectorTypesUtils.bytes(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                bytes(true, TS_MathArrayVectorTypesUtils.bytes(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void bytes(boolean useMask, VectorSpecies<Byte> species,
            byte[] output, byte[] inputA, byte[] inputB,
            TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = ByteVector.fromArray(species, inputA, i, mask);
                var vectorB = ByteVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = ByteVector.fromArray(species, inputA, i);
                var vectorB = ByteVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }

    public static void shorts(TS_MathArrayVectorTypes vectorType,
            short[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Short, Short, Short> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                shorts(false, TS_MathArrayVectorTypesUtils.shorts(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                shorts(true, TS_MathArrayVectorTypesUtils.shorts(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void shorts(boolean useMask, VectorSpecies<Short> species,
            short[] output, short[] inputA, short[] inputB,
            TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Short, Short, Short> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = ShortVector.fromArray(species, inputA, i, mask);
                var vectorB = ShortVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = ShortVector.fromArray(species, inputA, i);
                var vectorB = ShortVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }

    public static void integers(TS_MathArrayVectorTypes vectorType,
            int[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Integer, Integer, Integer> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                integers(false, TS_MathArrayVectorTypesUtils.integers(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                integers(true, TS_MathArrayVectorTypesUtils.integers(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void integers(boolean useMask, VectorSpecies<Integer> species,
            int[] output, int[] inputA, int[] inputB,
            TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Integer, Integer, Integer> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = IntVector.fromArray(species, inputA, i, mask);
                var vectorB = IntVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = IntVector.fromArray(species, inputA, i);
                var vectorB = IntVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }

    public static void longs(TS_MathArrayVectorTypes vectorType,
            long[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Long, Long, Long> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                longs(false, TS_MathArrayVectorTypesUtils.longs(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                longs(true, TS_MathArrayVectorTypesUtils.longs(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void longs(boolean useMask, VectorSpecies<Long> species,
            long[] output, long[] inputA, long[] inputB,
            TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Long, Long, Long> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = LongVector.fromArray(species, inputA, i, mask);
                var vectorB = LongVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = LongVector.fromArray(species, inputA, i);
                var vectorB = LongVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }

    public static void floats(TS_MathArrayVectorTypes vectorType,
            float[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Float, Float, Float> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                floats(false, TS_MathArrayVectorTypesUtils.floats(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                floats(true, TS_MathArrayVectorTypesUtils.floats(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void floats(boolean useMask, VectorSpecies<Float> species,
            float[] output, float[] inputA, float[] inputB,
            TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Float, Float, Float> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = FloatVector.fromArray(species, inputA, i, mask);
                var vectorB = FloatVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = FloatVector.fromArray(species, inputA, i);
                var vectorB = FloatVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }

    public static void doubles(TS_MathArrayVectorTypes vectorType,
            double[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Double, Double, Double> exeScalar) {
        switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                doubles(false, TS_MathArrayVectorTypesUtils.doubles(vectorType), output, inputA, inputB, exeVector, exeScalar);
            default ->
                doubles(true, TS_MathArrayVectorTypesUtils.doubles(vectorType), output, inputA, inputB, exeVector, exeScalar);
        }
    }

    public static void doubles(boolean useMask, VectorSpecies<Double> species,
            double[] output, double[] inputA, double[] inputB,
            TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> exeVector,
            TGS_FuncMTU_OutTyped_In2<Double, Double, Double> exeScalar) {
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(inputA.length); i += species.length()) {
                var mask = species.indexInRange(i, inputA.length);
                var vectorA = DoubleVector.fromArray(species, inputA, i, mask);
                var vectorB = DoubleVector.fromArray(species, inputB, i, mask);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i, mask);
            }
        } else {
            var upperBound = species.loopBound(inputA.length);
            for (; i < upperBound; i += species.length()) {
                var vectorA = DoubleVector.fromArray(species, inputA, i);
                var vectorB = DoubleVector.fromArray(species, inputB, i);
                var vectorOutput = exeVector.call(vectorA, vectorB);
                vectorOutput.intoArray(output, i);
            }
        }
        for (; i < inputA.length; i++) {
            output[i] = exeScalar.call(inputA[i], inputB[i]);
        }
    }
}
