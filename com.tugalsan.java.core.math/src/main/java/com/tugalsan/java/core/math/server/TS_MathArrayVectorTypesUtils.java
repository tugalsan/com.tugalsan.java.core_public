package com.tugalsan.java.core.math.server;

import module jdk.incubator.vector;

public class TS_MathArrayVectorTypesUtils {

    private TS_MathArrayVectorTypesUtils() {

    }

    public static VectorSpecies<Byte> bytes(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                ByteVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                ByteVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                ByteVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                ByteVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                ByteVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                ByteVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                ByteVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                ByteVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                ByteVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                ByteVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                ByteVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                ByteVector.SPECIES_PREFERRED;
        };
    }

    public static VectorSpecies<Short> shorts(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                ShortVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                ShortVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                ShortVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                ShortVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                ShortVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                ShortVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                ShortVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                ShortVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                ShortVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                ShortVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                ShortVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                ShortVector.SPECIES_PREFERRED;
        };
    }

    public static VectorSpecies<Integer> integers(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                IntVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                IntVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                IntVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                IntVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                IntVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                IntVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                IntVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                IntVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                IntVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                IntVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                IntVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                IntVector.SPECIES_PREFERRED;
        };
    }

    public static VectorSpecies<Long> longs(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                LongVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                LongVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                LongVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                LongVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                LongVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                LongVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                LongVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                LongVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                LongVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                LongVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                LongVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                LongVector.SPECIES_PREFERRED;
        };
    }

    public static VectorSpecies<Float> floats(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                FloatVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                FloatVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                FloatVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                FloatVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                FloatVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                FloatVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                FloatVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                FloatVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                FloatVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                FloatVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                FloatVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                FloatVector.SPECIES_PREFERRED;
        };
    }

    public static VectorSpecies<Double> doubles(TS_MathArrayVectorTypes vectorType) {
        return switch (vectorType) {
            case TS_MathArrayVectorTypes._0064 ->
                DoubleVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0064_WITH_MASK ->
                DoubleVector.SPECIES_64;
            case TS_MathArrayVectorTypes._0128 ->
                DoubleVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0128_WITH_MASK ->
                DoubleVector.SPECIES_128;
            case TS_MathArrayVectorTypes._0256 ->
                DoubleVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0256_WITH_MASK ->
                DoubleVector.SPECIES_256;
            case TS_MathArrayVectorTypes._0512 ->
                DoubleVector.SPECIES_512;
            case TS_MathArrayVectorTypes._0512_WITH_MASK ->
                DoubleVector.SPECIES_512;
            case TS_MathArrayVectorTypes._MAX ->
                DoubleVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._MAX_WITH_MASK ->
                DoubleVector.SPECIES_MAX;
            case TS_MathArrayVectorTypes._PREFERRED ->
                DoubleVector.SPECIES_PREFERRED;
            case TS_MathArrayVectorTypes._PREFERRED_WITH_MASK ->
                DoubleVector.SPECIES_PREFERRED;
        };
    }
}
