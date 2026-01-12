package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutBool_In4<A, B, C, D> extends TGS_FuncMTC_OutTyped_In4<Boolean, A, B, C, D> {

    @Override
    @Deprecated
    default Boolean call(A input0, B input1, C input2, D input3) throws Exception{
        return validate(input0, input1, input2,input3);
    }

    public boolean validate(A input0, B input1, C input2, D input3) throws Exception;
}
