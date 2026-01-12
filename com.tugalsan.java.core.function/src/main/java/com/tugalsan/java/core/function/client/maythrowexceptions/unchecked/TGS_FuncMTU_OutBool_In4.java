package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutBool_In4<A, B, C, D> extends TGS_FuncMTU_OutTyped_In4<Boolean, A, B, C, D> {

    @Override
    default Boolean call(A input0, B input1, C input2, D input3) {
        return validate(input0, input1, input2,input3);
    }

    public boolean validate(A input0, B input1, C input2, D input3);
}
