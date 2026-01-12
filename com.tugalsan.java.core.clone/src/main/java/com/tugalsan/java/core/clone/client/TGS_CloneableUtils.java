package com.tugalsan.java.core.clone.client;

import java.util.*;
import java.util.stream.*;

public class TGS_CloneableUtils {

    private TGS_CloneableUtils() {

    }

    public static List<TGS_Cloneable> cloneIt(List<TGS_Cloneable> rowData) {
        return rowData.stream().collect(Collectors.toCollection(ArrayList::new));
    }
}
