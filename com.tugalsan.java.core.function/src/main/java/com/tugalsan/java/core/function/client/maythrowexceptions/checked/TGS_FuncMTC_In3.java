package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_In3<A, B, C> extends TGS_FuncMTC_OutTyped_In3<Void, A, B, C> {

    final public static TGS_FuncMTC_In3 empty = (a, b, c) -> {
    };

    @Override
	@Deprecated
    default Void call(A input0, B input1, C input2) throws Exception{
        run(input0, input1, input2);
        return TGS_FuncMTCUtils.Null();
    }

    public void run(A input0, B input1, C input2) throws Exception;
}
