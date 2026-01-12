package com.tugalsan.java.core.math.server;

import module combinatoricslib3;
import java.util.*;
import java.util.stream.*;

public class TS_MathPermutationUtils {
    
    private TS_MathPermutationUtils(){
        
    }

    public static <T> List<List<T>> permutation(List<T> arr) {
        return Generator.permutation(arr)
                .simple()
                .stream()
                .collect(Collectors.toList());
    }
}
