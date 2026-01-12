package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_In2<A, B> extends TGS_FuncMTU_OutTyped_In2<Void, A, B> /*, BiConsumer<A>*/ {

    final public static TGS_FuncMTU_In2 empty = (a, b) -> {
    };
    
//    @Override | Clashes with BiFunction
//    default void apply(A input0, B input1){
//        return run(input0, input1);
//    }
    @Override
    default Void call(A input0, B input1) {
        run(input0, input1);
        return TGS_FuncMTUUtils.Null();
    }

    public void run(A input0, B input1);
}
