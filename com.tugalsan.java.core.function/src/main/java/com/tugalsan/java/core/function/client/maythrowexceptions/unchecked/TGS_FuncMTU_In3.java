package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_In3<A, B, C> extends TGS_FuncMTU_OutTyped_In3<Void, A, B, C> {

    final public static TGS_FuncMTU_In3 empty = (a, b, c) -> {
    };

    @Override
    default Void call(A input0, B input1, C input2) {
        run(input0, input1, input2);
        return TGS_FuncMTUUtils.Null();
    }

    public void run(A input0, B input1, C input2);
}
