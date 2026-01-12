package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_In5<A, B, C, D, E> extends TGS_FuncMTU_OutTyped_In5<Void, A, B, C, D, E> {

    final public static TGS_FuncMTU_In5 empty = (a, b, c, d, e) -> {
    };

    @Override
    default Void call(A input0, B input1, C input2, D input3, E input4) {
        run(input0, input1, input2, input3, input4);
        return TGS_FuncMTUUtils.Null();
    }

    public void run(A input0, B input1, C input2, D input3, E input4);
}
