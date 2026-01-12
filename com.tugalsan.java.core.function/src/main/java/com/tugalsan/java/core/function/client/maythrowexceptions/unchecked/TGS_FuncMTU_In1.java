package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import java.util.function.*;

public interface TGS_FuncMTU_In1<A> extends TGS_FuncMTU_OutTyped_In1<Void, A>, Consumer<A> {

    final public static TGS_FuncMTU_In1 empty = a -> {
    };

    @Override
    default void accept(A input0) {
        run(input0);
    }

    @Override
    default Void call(A input0) {
        run(input0);
        return TGS_FuncMTUUtils.Null();
    }

    public void run(A input0);
}
