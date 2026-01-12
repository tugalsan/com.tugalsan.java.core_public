package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import java.util.concurrent.*;
import java.util.function.*;

public interface TGS_FuncMTU_OutTyped<R> extends /*Callable<R>,*/ Supplier<R> {

    @Override
    @Deprecated
    default R get() {
        return call();
    }

//    @Override
    public R call();

    default R Void() {
        return TGS_FuncMTUUtils.Null();
    }

    public static <T> TGS_FuncMTU_OutTyped<T> of(Callable<T> callable) {
        TGS_FuncMTU_OutTyped<T> f = () -> {
            try {
                if (callable != null) {
                    return callable.call();
                }
            } catch (Exception e) {
                TGS_FuncMTUUtils.thrw(e);
            }
            return TGS_FuncMTUUtils.Null();
        };
        return f;
    }
}
