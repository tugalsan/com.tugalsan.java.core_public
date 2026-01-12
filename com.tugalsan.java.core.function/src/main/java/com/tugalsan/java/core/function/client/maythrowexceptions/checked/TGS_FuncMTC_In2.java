package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_In2<A, B> extends TGS_FuncMTC_OutTyped_In2<Void, A, B> /*, BiConsumer<A>*/ {

    final public static TGS_FuncMTC_In2 empty = (a, b) -> {
    };
    
//    @Override | Clashes with BiFunction
//    default void apply(A input0, B input1){
//        return run(input0, input1);
//    }
    @Override
	@Deprecated
    default Void call(A input0, B input1) throws Exception {
        run(input0, input1);
        return TGS_FuncMTCUtils.Null();
    }

    public void run(A input0, B input1) throws Exception ;
}
