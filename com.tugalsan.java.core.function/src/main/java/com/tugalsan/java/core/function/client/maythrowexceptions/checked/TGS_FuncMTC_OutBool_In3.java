package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutBool_In3<A, B, C> extends TGS_FuncMTC_OutTyped_In3<Boolean, A, B, C> {

    @Override
    @Deprecated
    default Boolean call(A input0, B input1, C input2) throws Exception {
        return validate(input0, input1, input2);
    }

    public boolean validate(A input0, B input1, C input2) throws Exception;
}
