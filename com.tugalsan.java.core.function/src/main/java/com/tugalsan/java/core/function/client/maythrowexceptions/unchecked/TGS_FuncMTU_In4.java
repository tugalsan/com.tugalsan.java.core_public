package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_In4<A, B, C, D> extends TGS_FuncMTU_OutTyped_In4<Void, A, B, C, D> {

    final public static TGS_FuncMTU_In4 empty = (a, b, c, d) -> {
    };

    @Override
    default Void call(A input0, B input1, C input2, D input3) {
        run(input0, input1, input2, input3);
        return TGS_FuncMTUUtils.Null();
    }

    public void run(A input0, B input1, C input2, D input3);
}
