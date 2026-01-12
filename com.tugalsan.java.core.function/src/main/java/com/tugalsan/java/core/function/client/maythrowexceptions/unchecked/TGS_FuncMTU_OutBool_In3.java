package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutBool_In3<A, B, C> extends TGS_FuncMTU_OutTyped_In3<Boolean, A, B, C> {

    @Override
    default Boolean call(A input0, B input1, C input2) {
        return validate(input0, input1, input2);
    }

    public boolean validate(A input0, B input1, C input2);
}
