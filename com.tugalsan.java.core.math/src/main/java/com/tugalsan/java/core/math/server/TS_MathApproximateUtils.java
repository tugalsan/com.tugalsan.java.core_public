package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.math;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.string;
import java.util.stream.*;

public class TS_MathApproximateUtils {

    private TS_MathApproximateUtils() {

    }

    final private static TS_Log d = TS_Log.of(TS_MathApproximateUtils.class);

    public static void testApprox() {
        var known_input_values = new double[]{10, 20, 30};
        var known_output_values = new double[]{8, 18, 50};
        var request_input_values = new double[]{5, 6, 7, 8, 9, 10, 11, 15, 20, 25, 30, 35, 40, 45, 50, 51, 52, 53, 54, 55};
        var calc = TS_MathApproximateUtils.calc(known_input_values, known_output_values, request_input_values);
        d.ce("test", "TS_MathApproximateUtils.known_input_values", TGS_StringUtils.cmn().toString(known_input_values, ", "));
        d.ce("test", "TS_MathApproximateUtils.known_output_values", TGS_StringUtils.cmn().toString(known_output_values, ", "));
        d.ce("test", "TS_MathApproximateUtils.req ", TGS_StringUtils.cmn().toString(request_input_values, ", "));
        d.ce("test", "TS_MathApproximateUtils.calc", TGS_StringUtils.cmn().toString(calc, ", "));
    }

    public static double[] calc(double[] known_input_values, double[] known_output_values, double request_input_values[]) {
        var requested_output_values = new double[request_input_values.length];
        if (known_input_values.length == 1) {
            var offset = known_output_values[0] - known_input_values[0];
            IntStream.range(0, request_input_values.length).parallel().forEach(i -> {
                requested_output_values[i] = request_input_values[i] + offset;
            });
            return requested_output_values;
        }

        TGS_ListSortUtils.sortPrimativeDouble2(known_input_values, known_output_values);

        if (known_input_values.length == 2) {
            var fromMinMax = new TGS_Tuple2(known_input_values[0], known_input_values[1]);
            var toMinMax = new TGS_Tuple2(known_output_values[0], known_output_values[1]);
            IntStream.range(0, request_input_values.length).parallel().forEach(i -> {
                requested_output_values[i] = TGS_MathUtils.convertWeightedDbl(request_input_values[i], fromMinMax, toMinMax);
            });
            return requested_output_values;
        }

        var curve = new TS_MathCurve(known_input_values, known_output_values);
        IntStream.range(0, request_input_values.length).parallel().forEach(i -> {
            requested_output_values[i] = curve.getOutputByClosestAverage(request_input_values[i]);
        });
        return requested_output_values;
    }
}
