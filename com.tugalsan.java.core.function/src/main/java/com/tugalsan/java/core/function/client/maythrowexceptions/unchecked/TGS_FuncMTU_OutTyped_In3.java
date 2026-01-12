package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutTyped_In3<R, A, B, C> {

    public R call(A input0, B input1, C input2);

    default R Void() {
        return TGS_FuncMTUUtils.Null();
    }
}
