package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_In4<A, B, C, D> extends TGS_FuncMTC_OutTyped_In4<Void, A, B, C, D> {

    final public static TGS_FuncMTC_In4 empty = (a, b, c, d) -> {
    };

    @Override
	@Deprecated
    default Void call(A input0, B input1, C input2, D input3) throws Exception{
        run(input0, input1, input2, input3);
        return TGS_FuncMTCUtils.Null();
    }

    public void run(A input0, B input1, C input2, D input3) throws Exception;
}
