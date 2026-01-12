package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC extends /*Runnable, */ TGS_FuncMTC_OutTyped<Void> {

    final public static TGS_FuncMTC empty = () -> {
    };

    @Override
    default Void call() throws Exception {
        run();
        return TGS_FuncMTCUtils.Null();
    }

    abstract public void run() throws Exception;
}
