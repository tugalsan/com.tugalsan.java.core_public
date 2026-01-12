package com.tugalsan.java.core.math.client;

import com.tugalsan.java.core.stream.client.TGS_StreamUtils;
import java.util.*;
import java.util.stream.*;
import com.tugalsan.java.core.tuple.client.*;

public class TGS_MathUtils {

    public static OptionalDouble average_forLong(List<Long> list) {
        return list.stream().mapToLong(v -> v).average();
    }

    public static OptionalDouble median_forLong(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return OptionalDouble.empty();
        }
        var sorted = list.stream().mapToDouble(v -> v).sorted();
        return (list.size() % 2 == 0
                ? sorted.skip((list.size() / 2) - 1).limit(2).average()
                : sorted.skip(list.size() / 2).findFirst());
    }

    public static OptionalDouble average_forDouble(List<Double> list) {
        return list.stream().mapToDouble(v -> v).average();
    }

    public static OptionalDouble median_forDouble(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return OptionalDouble.empty();
        }
        var sorted = list.stream().mapToDouble(v -> v).sorted();
        return (list.size() % 2 == 0
                ? sorted.skip((list.size() / 2) - 1).limit(2).average()
                : sorted.skip(list.size() / 2).findFirst());
    }

    public static OptionalDouble average_forInteger(List<Integer> list) {
        return list.stream().mapToInt(v -> v).average();
    }

    public static OptionalDouble median_forInteger(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return OptionalDouble.empty();
        }
        var sorted = list.stream().mapToDouble(v -> v).sorted();
        return (list.size() % 2 == 0
                ? sorted.skip((list.size() / 2) - 1).limit(2).average()
                : sorted.skip(list.size() / 2).findFirst());
    }

    public static OptionalDouble median_and_sort(double[] numArray) {
        return median_forDouble(TGS_StreamUtils.toLst(DoubleStream.of(numArray).boxed()));
    }

    public static OptionalDouble average(double[] numArray) {
        return average_forDouble(TGS_StreamUtils.toLst(DoubleStream.of(numArray).boxed()));
    }

    public static OptionalDouble median_and_sort(int[] numArray) {
        return median_forInteger(TGS_StreamUtils.toLst(IntStream.of(numArray).boxed()));
    }

    public static OptionalDouble average(int[] numArray) {
        return average_forInteger(TGS_StreamUtils.toLst(IntStream.of(numArray).boxed()));
    }

    public static OptionalDouble median_and_sort(long[] numArray) {
        return median_forLong(TGS_StreamUtils.toLst(LongStream.of(numArray).boxed()));
    }

    public static OptionalDouble average(long[] numArray) {
        return average_forLong(TGS_StreamUtils.toLst(LongStream.of(numArray).boxed()));
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static boolean isOdd(int num) {
        return !isEven(num);
    }

    public static double percentageValueDbl(double divident, double divisor) {
        return 100 * divident / divisor;
    }

    public static int percentageValueInt(double divident, double divisor) {
        return (int) Math.round(percentageValueDbl(divident, divisor));
    }

    public static float RADIAN_ZERO() {
        return (float) Math.toRadians(0);
    }

    public static float RADIAN_PLUS_90() {
        return (float) Math.toRadians(90);
    }

    public static float RADIAN_PLUS_180() {
        return RADIAN_PLUS_90() + RADIAN_PLUS_90();
    }

    public static float RADIAN_PLUS_270() {
        return RADIAN_PLUS_180() + RADIAN_PLUS_90();
    }

    public static float RADIAN_PLUS_360() {
        return RADIAN_PLUS_180() + RADIAN_PLUS_180();
    }

    public static float RADIAN_MINUS_90() {
        return (float) Math.toRadians(-90);
    }

    public static float RADIAN_MINUS_180() {
        return RADIAN_MINUS_90() + RADIAN_MINUS_90();
    }

    public static float RADIAN_MINUS_270() {
        return RADIAN_MINUS_180() + RADIAN_MINUS_90();
    }

    public static float RADIAN_MINUS_360() {
        return RADIAN_MINUS_180() + RADIAN_MINUS_180();
    }

    private static int[] POWERS_OF_10() {
        return new int[]{
            1,//0
            10,//1
            100,//2
            1000,//3
            10000,//4
            100000,//5
            1000000,//6
            10000000,//7
            100000000,//8
            1000000000//9
        };
    }

    public static int findIndex(double[] sortedArray, int indexRangeStartEnclosing, int indexRangeStopNotEnclosing,
            double findValue, Double optionalTolerans, int maxIterations) {
        maxIterations--;
        if (maxIterations == -1) {
            return -1;
        }
        var indexRange = indexRangeStopNotEnclosing - indexRangeStartEnclosing;
        var indexRange_2 = indexRange / 2;
        var indexMid = indexRangeStartEnclosing + indexRange_2;
        var midValue = sortedArray[indexMid];
        if (midValue == findValue) {
            return indexMid;
        }
        if (optionalTolerans != null) {
            if (midValue == findValue + optionalTolerans) {
                return indexMid;
            }
            if (midValue == findValue - optionalTolerans) {
                return indexMid;
            }
        }
        if (midValue < findValue) {
            return findIndex(sortedArray, indexRangeStartEnclosing, indexMid, findValue, optionalTolerans, maxIterations);
        }
        if (midValue > findValue) {
            return findIndex(sortedArray, indexMid, indexRangeStopNotEnclosing, findValue, optionalTolerans, maxIterations);
        }
        return -1;
    }

    public static float radianSimplified(float radiant) {
        while (radiant < RADIAN_ZERO()) {
            radiant += RADIAN_PLUS_360();
        }
        while (radiant > RADIAN_PLUS_360()) {
            radiant -= RADIAN_PLUS_360();
        }
        return radiant;
    }

    public static int getSum(int[] input) {
        return Arrays.stream(input).reduce(0, Integer::sum);
    }

    public static TGS_Tuple2<Integer, Integer> findMax_returns_IdAndValue(int[] input) {
        TGS_Tuple2<Integer, Integer> maxIdAndValue = new TGS_Tuple2(0, input[0]);
        IntStream.range(1, input.length).forEachOrdered(i -> {
            if (maxIdAndValue.value1 < input[i]) {
                maxIdAndValue.value0 = i;
                maxIdAndValue.value1 = input[i];
            }
        });
        return maxIdAndValue;
    }

    public static TGS_Tuple2<Integer, Integer> findMin_returns_IdAndValue(int[] input) {
        TGS_Tuple2<Integer, Integer> minIdAndValue = new TGS_Tuple2(0, input[0]);
        IntStream.range(1, input.length).forEachOrdered(i -> {
            if (minIdAndValue.value1 > input[i]) {
                minIdAndValue.value0 = i;
                minIdAndValue.value1 = input[i];
            }
        });
        return minIdAndValue;
    }

    public static Integer convertWeightedInt(int input, TGS_Tuple2<Integer, Integer> fromMinMax, TGS_Tuple2<Integer, Integer> toMinMax) {
        if (toMinMax == null || fromMinMax == null) {
            return null;
        }
        if (fromMinMax.value0 > fromMinMax.value1) {
            var tmp = fromMinMax.value0;
            fromMinMax.value0 = fromMinMax.value1;
            fromMinMax.value1 = tmp;
        }
        if (input < fromMinMax.value0 || input > fromMinMax.value1) {
            return null;
        }
        var fromRange = fromMinMax.value1 - fromMinMax.value0;
        var fromLeftRange = input - fromMinMax.value0;
        var percent = fromLeftRange * 1f / fromRange;

        if (toMinMax.value0 > toMinMax.value1) {
            var tmp = toMinMax.value0;
            toMinMax.value0 = toMinMax.value1;
            toMinMax.value1 = tmp;
        }
        var toRange = toMinMax.value1 - toMinMax.value0;
        var toLeftRange = percent * toRange;
        var toValue = toMinMax.value0 + Math.round(toLeftRange);
        return toValue > toMinMax.value1 ? toMinMax.value1 : toValue;
    }

    public static Double convertWeightedDbl(double input, TGS_Tuple2<Double, Double> fromMinMax, TGS_Tuple2<Double, Double> toMinMax) {
        if (toMinMax == null || fromMinMax == null) {
            return null;
        }
        if (fromMinMax.value0 > fromMinMax.value1) {
            var tmp = fromMinMax.value0;
            fromMinMax.value0 = fromMinMax.value1;
            fromMinMax.value1 = tmp;
        }
        if (input < fromMinMax.value0 || input > fromMinMax.value1) {
//            System.out.println("convertWeightedDbl" + "RANGE OUT DETECTED");
//            System.out.println("input:" + input);
//            System.out.println("fromMinMax.value0:" + fromMinMax.value0);
//            System.out.println("fromMinMax.value1:" + fromMinMax.value1);
            var inputRange = fromMinMax.value1 - fromMinMax.value0;
            var outputRange = toMinMax.value1 - toMinMax.value0;
            var outputIncrement = outputRange / inputRange;
            if (input < fromMinMax.value0) {
                var inputLess = fromMinMax.value0 - input;
                return toMinMax.value0 - inputLess * outputIncrement;
            } else {//input > fromMinMax.value1)
                var inputMore = input - fromMinMax.value1;
                return toMinMax.value1 + inputMore * outputIncrement;
            }
        }
        var fromRange = fromMinMax.value1 - fromMinMax.value0;
        var fromLeftRange = input - fromMinMax.value0;
        var percent = fromLeftRange * 1f / fromRange;

        if (toMinMax.value0 > toMinMax.value1) {
            var tmp = toMinMax.value0;
            toMinMax.value0 = toMinMax.value1;
            toMinMax.value1 = tmp;
        }
        var toRange = toMinMax.value1 - toMinMax.value0;
        var toLeftRange = percent * toRange;
        var toValue = toMinMax.value0 + toLeftRange;
        return toValue > toMinMax.value1 ? toMinMax.value1 : toValue;
    }

    public static int[] convertWeighted(int[] input, int minWeighted, int preferedSumWeight) {
        var weighted = new int[input.length];//MAY ARRAY
        var sum0 = getSum(input);//input-sum
        IntStream.range(0, weighted.length).parallel().forEach(i -> {
            weighted[i] = Math.round(1f * preferedSumWeight * input[i] / sum0);
            weighted[i] = weighted[i] < minWeighted ? minWeighted : weighted[i];
        });
        while (true) {
            var sum = getSum(weighted);
            var remainer = sum - preferedSumWeight;
            if (remainer == 0) {
                break;
            } else if (remainer < 0) {
                weighted[findMin_returns_IdAndValue(weighted).value0]++;
            } else {
                weighted[findMax_returns_IdAndValue(weighted).value0]--;
            }
        }
        return weighted;
    }

    public static double long2Double(double dbValue, int pow_from0_to9) {
        return dbValue / powerOf10(pow_from0_to9);
    }

    public static long double2Long(double dbValue, int pow_from0_to9) {
        return Math.round(dbValue * powerOf10(pow_from0_to9));
    }

    public static int powerOf10(int pow_from0_to9) {
        return POWERS_OF_10()[pow_from0_to9];
    }

    public static OptionalDouble average(Double skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalDouble average(Long skip, long... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalDouble average(Long skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalDouble average(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .average();
    }

    public static OptionalInt min(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalLong min(Long skip, long... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalDouble min(Long skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalDouble min(Integer skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .min();
    }

    public static OptionalInt max(Integer skip, int... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

    public static OptionalLong max(Long skip, long... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

    public static OptionalDouble max(Long skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

    public static OptionalDouble max(Integer skip, double... val) {
        return Arrays.stream(val)
                .filter(i -> skip == null ? true : skip != i)
                .max();
    }

    public static double directlyPropotional_dbl(double set1_value1, double set2_value1, double set1_value2) {
        return set1_value2 * set2_value1 / set1_value1;
    }

    public static long directlyPropotional_dec(int set1_value1, int set2_value1, int set1_value2) {
        return Math.round(1d * set1_value2 * set2_value1 / set1_value1);
    }
}
