package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutTyped_In5<R, A, B, C, D, E> {

    public R call(A input0, B input1, C input2, D input3, E input4) throws Exception;

    default R Void() {
        return TGS_FuncMTCUtils.Null();
    }
}
