package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.math;
import module com.tugalsan.java.core.tuple;
import java.util.*;

public class TS_MathCurve {

    public static double DEFAULT_INDEX_STEPS() {
        return 0.1d;
    }

    final public TS_MathCurveDriver curve;
    final public List<TGS_Tuple3<Double, Double, Double>> table_of_idx_input_output;
    final public int tableSize;

    public TS_MathCurve(double[] input_values, double[] output_values) {
        this(input_values, output_values, DEFAULT_INDEX_STEPS());
    }

    public TS_MathCurve(double[] input_values, double[] output_values, double indexSteps) {
        curve = new TS_MathCurveDriver(input_values, output_values);
        table_of_idx_input_output = curve.calc_return_table_of_idx_input_output(indexSteps);
        tableSize = table_of_idx_input_output.size();
    }

    public double getIndexMin() {
        return table_of_idx_input_output.get(0).value0;
    }

    public double getIndexMax() {
        return table_of_idx_input_output.get(tableSize - 1).value0;
    }

    public double getInputMin() {
        return table_of_idx_input_output.get(0).value1;
    }

    public double getInputMax() {
        return table_of_idx_input_output.get(tableSize - 1).value1;
    }

    public double getOutputForInputMin() {
        return table_of_idx_input_output.get(0).value2;
    }

    public double getOutputForInputMax() {
        return table_of_idx_input_output.get(tableSize - 1).value2;
    }

    public Double getOutputByClosestAverage(double forInputValue) {
        if (forInputValue > getInputMax()) {
            var pack0 = table_of_idx_input_output.get(tableSize - 2);
            var pack1 = table_of_idx_input_output.get(tableSize - 1);
            var fromMinMax = new TGS_Tuple2(pack0.value1, pack1.value1);
            var toMinMax = new TGS_Tuple2(pack0.value2, pack1.value2);
            return TGS_MathUtils.convertWeightedDbl(forInputValue, fromMinMax, toMinMax);
        }
        if (forInputValue < getInputMin()) {
            var pack0 = table_of_idx_input_output.get(0);
            var pack1 = table_of_idx_input_output.get(1);
            var fromMinMax = new TGS_Tuple2(pack0.value1, pack1.value1);
            var toMinMax = new TGS_Tuple2(pack0.value2, pack1.value2);
            return TGS_MathUtils.convertWeightedDbl(forInputValue, fromMinMax, toMinMax);
        }

        if (table_of_idx_input_output.get(0).value1 == forInputValue) {
            return table_of_idx_input_output.get(0).value2;
        }
        if (table_of_idx_input_output.get(tableSize - 1).value1 == forInputValue) {
            return table_of_idx_input_output.get(tableSize - 1).value2;
        }

        var closestTableIdx = 0;
        for (var tableIdx = 1; tableIdx < tableSize; tableIdx++) {
            var tableRow = table_of_idx_input_output.get(tableIdx);
            var distance = tableRow.value1 - forInputValue;
            if (distance == 0) {
                return tableRow.value2;
            }
            if (distance > 0) {
                break;
            }
            closestTableIdx = tableIdx;
        }

        if (closestTableIdx == tableSize - 1) {
            return table_of_idx_input_output.get(tableSize - 1).value2;
        }

        return TGS_MathUtils.convertWeightedDbl(
                forInputValue,
                new TGS_Tuple2(
                        table_of_idx_input_output.get(closestTableIdx).value1,
                        table_of_idx_input_output.get(closestTableIdx + 1).value1
                ),
                new TGS_Tuple2(
                        table_of_idx_input_output.get(closestTableIdx).value2,
                        table_of_idx_input_output.get(closestTableIdx + 1).value2
                )
        );
    }

//    public static void main(String... s) {
//        var input_values = new double[]{
//            1d, 20d, 50d
//        };
//        var output_values = new double[]{
//            100d, 200d, 500d
//        };
//        var approximator = new TGS_MathApproximator(input_values, output_values);
//        var table = approximator.table_of_idx_input_output;
//        System.out.println("TABLE.INDEX:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value0));
//        System.out.println("TABLE.INPUT:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value1));
//        System.out.println("TABLE.OUTPUT:");
//        table.stream().forEachOrdered(row -> System.out.println(row.value2));
//
//        List<TGS_Tuple2<Double, Double>> closest_input_output = TGS_ListUtils.of();
//        for (var i = -10; i <= 60; i++) {
//            var closestOutput = approximator.getOutputByClosestAverage(i, true);
//            TGS_Tuple2<Double, Double> pack = new TGS_Tuple2((double) i, closestOutput);
//            closest_input_output.add(pack);
//        }
//        System.out.println("CLOSEST.INPUT:");
//        closest_input_output.stream().forEachOrdered(row -> System.out.println(row.value0));
//        System.out.println("CLOSEST.OUTPUT:");
//        closest_input_output.stream().forEachOrdered(row -> System.out.println(row.value1));
//    }
}
