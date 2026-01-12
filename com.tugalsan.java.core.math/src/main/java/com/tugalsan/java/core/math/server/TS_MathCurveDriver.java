package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.tuple;
import module commons.math3;
import java.util.*;

public class TS_MathCurveDriver {

    final public double[] input_values;
    final public double[] output_values;
    final public List<TGS_Tuple2<Double, Double>> calculated_results = TGS_ListUtils.of();
    final private double[] indexes;
    final public double indexMin;
    final public double indexMax;
    final private PolynomialSplineFunction funcInput;
    final private PolynomialSplineFunction funcOutput;

    public TS_MathCurveDriver(double[] input_values, double[] output_values) {
        this.input_values = input_values;
        this.output_values = output_values;
        indexes = TGS_ListUtils.createDouble(0, 1, output_values.length);
        indexMin = indexes[0];
        indexMax = indexes[indexes.length - 1];
        funcInput = new SplineInterpolator().interpolate(indexes, input_values);
        funcOutput = new SplineInterpolator().interpolate(indexes, output_values);
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("indexes[" + i + "]: " + indexes[i]));
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("input_values[" + i + "]: " + input_values[i]));
//        IntStream.range(0, input_values.length).forEachOrdered(i -> System.out.println("output_values[" + i + "]: " + output_values[i]));
    }

    public TGS_Tuple3<Double, Double, Double> calc_return_idx_input_output(Double idx) {
        if (idx == null || idx < indexMin || idx > indexMax) {
            return null;
        }
        return new TGS_Tuple3(idx, funcInput.value(idx), funcOutput.value(idx));
    }

    public List<TGS_Tuple3<Double, Double, Double>> calc_return_table_of_idx_input_output(double indexStep) {
        List<TGS_Tuple3<Double, Double, Double>> table = TGS_ListUtils.of();
        for (var idx = indexMin; idx <= indexMax; idx += indexStep) {
            table.add(calc_return_idx_input_output(idx));
        }
        if (table.get(table.size() - 1).value0 != indexMax) {//JAVA DOUBLE FIX
            table.add(calc_return_idx_input_output(indexMax));
        }
        return table;
    }
}
