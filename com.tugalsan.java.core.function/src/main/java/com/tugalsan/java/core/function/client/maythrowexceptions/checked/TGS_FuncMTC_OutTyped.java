package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

import java.util.concurrent.*;

public interface TGS_FuncMTC_OutTyped<R> extends Callable<R>/*, Supplier<R>*/ {

//    @Override
//    default R get() throws Exception {
//        return call();
//    }
    @Override
    public R call() throws Exception;

    default R Void() {
        return TGS_FuncMTCUtils.Null();
    }

    public static <T> TGS_FuncMTC_OutTyped<T> of(Callable<T> callable) {
        TGS_FuncMTC_OutTyped<T> f = () -> callable.call();
        return f;
//            
//            try {
//                if (callable != null) {
//                    return callable.call();
//                }
//            } catch (Exception e) {
//                throwIfInterruptedException(e);
//                throwAsUncheckedException(e);
//            }
//            return TGS_FuncMTCUtils.Null();;
//        };
//        return f;
    }
}
