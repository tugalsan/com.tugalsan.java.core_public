package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutTyped_In2<R, A, B> /*extends BiFunction<A, B, R>*/{

//    @Override
//    @Deprecated
//    default R apply(A input0, B input1) throws Exception{
//        return call(input0, input1);
//    }

    public R call(A input0, B input1) throws Exception;

    default R Void() {
        return TGS_FuncMTCUtils.Null();
    }
}
