package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutBool_In1<A> extends TGS_FuncMTU_OutTyped_In1<Boolean, A> {

    @Override
    default Boolean call(A input0) {
        return validate(input0);
    }

    public boolean validate(A input0);
}
