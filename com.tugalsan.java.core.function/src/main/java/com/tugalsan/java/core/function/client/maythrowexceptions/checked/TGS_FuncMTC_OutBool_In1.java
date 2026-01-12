package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutBool_In1<A> extends TGS_FuncMTC_OutTyped_In1<Boolean, A> {

    @Override
    @Deprecated
    default Boolean call(A input0) throws Exception {
        return validate(input0);
    }

    public boolean validate(A input0) throws Exception;
}
