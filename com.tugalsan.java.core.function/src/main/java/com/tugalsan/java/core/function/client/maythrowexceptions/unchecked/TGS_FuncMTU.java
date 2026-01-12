package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU extends Runnable, TGS_FuncMTU_OutTyped<Void> {

    final public static TGS_FuncMTU empty = () -> {
    };

    @Override
    default Void call() {
        run();
        return TGS_FuncMTUUtils.Null();
    }

    @Override
    public void run();
}
