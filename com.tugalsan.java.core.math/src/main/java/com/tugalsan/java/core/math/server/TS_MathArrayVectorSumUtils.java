package com.tugalsan.java.core.math.server;

import module jdk.incubator.vector;

public class TS_MathArrayVectorSumUtils {

    private TS_MathArrayVectorSumUtils() {

    }

    public static byte bytes(TS_MathArrayVectorTypes vectorType, byte[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                bytes(false, TS_MathArrayVectorTypesUtils.bytes(vectorType), input);
            default ->
                bytes(true, TS_MathArrayVectorTypesUtils.bytes(vectorType), input);
        };
    }

    public static byte bytes(boolean useMask, VectorSpecies<Byte> species, byte[] input) {
        byte output = 0;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = ByteVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = ByteVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static long bytes_to_longs(TS_MathArrayVectorTypes vectorType, byte[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                bytes_to_longs(false, TS_MathArrayVectorTypesUtils.bytes(vectorType), input);
            default ->
                bytes_to_longs(true, TS_MathArrayVectorTypesUtils.bytes(vectorType), input);
        };
    }

    public static long bytes_to_longs(boolean useMask, VectorSpecies<Byte> species, byte[] input) {
        var output = 0L;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = ByteVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = ByteVector.fromArray(species, input, i);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static short shorts(TS_MathArrayVectorTypes vectorType, short[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                shorts(false, TS_MathArrayVectorTypesUtils.shorts(vectorType), input);
            default ->
                shorts(true, TS_MathArrayVectorTypesUtils.shorts(vectorType), input);
        };
    }

    public static short shorts(boolean useMask, VectorSpecies<Short> species, short[] input) {
        short output = 0;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = ShortVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = ShortVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static long shorts_to_longs(TS_MathArrayVectorTypes vectorType, short[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                shorts_to_longs(false, TS_MathArrayVectorTypesUtils.shorts(vectorType), input);
            default ->
                shorts_to_longs(true, TS_MathArrayVectorTypesUtils.shorts(vectorType), input);
        };
    }

    public static long shorts_to_longs(boolean useMask, VectorSpecies<Short> species, short[] input) {
        var output = 0L;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = ShortVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = ShortVector.fromArray(species, input, i);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static int integers(TS_MathArrayVectorTypes vectorType, int[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                integers(false, TS_MathArrayVectorTypesUtils.integers(vectorType), input);
            default ->
                integers(true, TS_MathArrayVectorTypesUtils.integers(vectorType), input);
        };
    }

    public static int integers(boolean useMask, VectorSpecies<Integer> species, int[] input) {
        var output = 0;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = IntVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = IntVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static long integers_to_longs(TS_MathArrayVectorTypes vectorType, int[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                integers_to_longs(false, TS_MathArrayVectorTypesUtils.integers(vectorType), input);
            default ->
                integers_to_longs(true, TS_MathArrayVectorTypesUtils.integers(vectorType), input);
        };
    }

    public static long integers_to_longs(boolean useMask, VectorSpecies<Integer> species, int[] input) {
        var output = 0L;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = IntVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = IntVector.fromArray(species, input, i);
                output += vectorInput.reduceLanesToLong(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static long longs(TS_MathArrayVectorTypes vectorType, long[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                longs(false, TS_MathArrayVectorTypesUtils.longs(vectorType), input);
            default ->
                longs(true, TS_MathArrayVectorTypesUtils.longs(vectorType), input);
        };
    }

    public static long longs(boolean useMask, VectorSpecies<Long> species, long[] input) {
        var output = 0L;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = LongVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = LongVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static float floats(TS_MathArrayVectorTypes vectorType, float[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                floats(false, TS_MathArrayVectorTypesUtils.floats(vectorType), input);
            default ->
                floats(true, TS_MathArrayVectorTypesUtils.floats(vectorType), input);
        };
    }

    public static float floats(boolean useMask, VectorSpecies<Float> species, float[] input) {
        var output = 0.0f;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = FloatVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = FloatVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }

    public static double doubles(TS_MathArrayVectorTypes vectorType, double[] input) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064, TS_MathArrayVectorTypes._0128, TS_MathArrayVectorTypes._0256, TS_MathArrayVectorTypes._0512, TS_MathArrayVectorTypes._MAX, TS_MathArrayVectorTypes._PREFERRED ->
                doubles(false, TS_MathArrayVectorTypesUtils.doubles(vectorType), input);
            default ->
                doubles(true, TS_MathArrayVectorTypesUtils.doubles(vectorType), input);
        };
    }

    public static double doubles(boolean useMask, VectorSpecies<Double> species, double[] input) {
        var output = 0.0d;
        var i = 0;
        if (useMask) {
            for (; i < species.loopBound(input.length); i += species.length()) {
                var mask = species.indexInRange(i, input.length);
                var vectorInput = DoubleVector.fromArray(species, input, i, mask);
                output += vectorInput.reduceLanes(VectorOperators.ADD, mask);
            }
        } else {
            var upperBound = species.loopBound(input.length);
            for (; i < upperBound; i += species.length()) {
                var vectorInput = DoubleVector.fromArray(species, input, i);
                output += vectorInput.reduceLanes(VectorOperators.ADD);
            }
        }
        for (; i < input.length; i++) {
            output += input[i];
        }
        return output;
    }
}
