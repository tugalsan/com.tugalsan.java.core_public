package com.tugalsan.java.core.math.server;

import module combinatoricslib3;
import java.util.*;
import java.util.stream.*;

public class TS_MathCombinationUtils {

    private TS_MathCombinationUtils() {

    }

    public static <T> List<List<T>> combinations(List<T> arr, int innerItemCount) {
        return Generator.combination(arr)
                .simple(innerItemCount)
                .stream()
                .collect(Collectors.toList());
    }
}
