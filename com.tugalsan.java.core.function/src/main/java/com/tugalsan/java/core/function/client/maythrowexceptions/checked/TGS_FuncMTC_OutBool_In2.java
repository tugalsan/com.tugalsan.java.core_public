package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutBool_In2<A, B> extends TGS_FuncMTC_OutTyped_In2<Boolean, A, B> {

    @Override
    @Deprecated
    default Boolean call(A input0, B input1) throws Exception {
        return validate(input0, input1);
    }

    public boolean validate(A input0, B input1) throws Exception;
}
