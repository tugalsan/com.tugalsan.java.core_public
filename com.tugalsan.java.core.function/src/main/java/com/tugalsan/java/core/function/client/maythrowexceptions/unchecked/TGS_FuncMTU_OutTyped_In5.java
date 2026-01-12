package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutTyped_In5<R, A, B, C, D, E> {

    public R call(A input0, B input1, C input2, D input3, E input4);

    default R Void() {
        return TGS_FuncMTUUtils.Null();
    }
}
