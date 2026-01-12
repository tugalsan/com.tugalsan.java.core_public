package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import java.util.function.*;

public interface TGS_FuncMTU_OutTyped_In2<R, A, B> extends BiFunction<A, B, R>{

    @Override
    @Deprecated
    default R apply(A input0, B input1) {
        return call(input0, input1);
    }

    public R call(A input0, B input1);

    default R Void() {
        return TGS_FuncMTUUtils.Null();
    }
}
