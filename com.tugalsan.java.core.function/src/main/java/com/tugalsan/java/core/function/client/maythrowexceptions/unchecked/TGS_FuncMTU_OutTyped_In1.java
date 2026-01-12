package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import java.util.function.*;

public interface TGS_FuncMTU_OutTyped_In1<R, A> extends Function<A, R> {

    @Override
    @Deprecated
    default R apply(A input0) {
        return call(input0);
    }

    public R call(A input0);

    default R Void() {
        return TGS_FuncMTUUtils.Null();
    }

}
