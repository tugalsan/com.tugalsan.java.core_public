package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutTyped_In1<R, A> /*extends Function<A, R>*/ {

//    @Override
//    @Deprecated
//    default R apply(A input0) throws Exception {
//        return call(input0);
//    }

    public R call(A input0)throws Exception;

    default R Void() {
        return TGS_FuncMTCUtils.Null();
    }
}
