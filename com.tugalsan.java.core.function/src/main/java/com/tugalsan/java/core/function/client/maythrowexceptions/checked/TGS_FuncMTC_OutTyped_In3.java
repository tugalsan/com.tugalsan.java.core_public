package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutTyped_In3<R, A, B, C> {

    public R call(A input0, B input1, C input2) throws Exception;

    default R Void() {
        return TGS_FuncMTCUtils.Null();
    }
}
