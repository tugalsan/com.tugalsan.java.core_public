package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_In1<A> extends TGS_FuncMTC_OutTyped_In1<Void, A>/*, Consumer<A>*/ {

    final public static TGS_FuncMTC_In1 empty = a -> {
    };

//    @Override
//    default void accept(A input0) {
//        run(input0);
//    }

    @Override
	@Deprecated
    default Void call(A input0) throws Exception {
        run(input0);
        return TGS_FuncMTCUtils.Null();
    }

    public void run(A input0) throws Exception ;
}
