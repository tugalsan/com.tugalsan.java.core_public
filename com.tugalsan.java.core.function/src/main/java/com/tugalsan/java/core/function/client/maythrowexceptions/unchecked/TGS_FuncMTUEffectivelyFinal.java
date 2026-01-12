package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import java.util.*;

public class TGS_FuncMTUEffectivelyFinal<T> {

    //CONSTRUCTOR
    private T bufferedValue;

    public TGS_FuncMTUEffectivelyFinal(T initVal) {
        bufferedValue = initVal;
    }

    public static <T> TGS_FuncMTUEffectivelyFinal<T> of(Class<T> clazz) {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    public static <T> TGS_FuncMTUEffectivelyFinal<List<T>> ofListArray(Class<T> clazz) {
        return new TGS_FuncMTUEffectivelyFinal(new ArrayList());
    }

    public static <T> TGS_FuncMTUEffectivelyFinal<T> of(T initialValue) {
        return new TGS_FuncMTUEffectivelyFinal(initialValue);
    }

    public static TGS_FuncMTUEffectivelyFinal<String> ofStr() {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    public static TGS_FuncMTUEffectivelyFinal<Long> ofLng() {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    public static TGS_FuncMTUEffectivelyFinal<Integer> ofInt() {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    public static TGS_FuncMTUEffectivelyFinal<Double> ofDbl() {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    public static TGS_FuncMTUEffectivelyFinal<Boolean> ofBool() {
        return new TGS_FuncMTUEffectivelyFinal(null);
    }

    //LOADERS
    private static enum Type {
        SKIPPER, STOPPER
    }

    private class Act<T> {

        public Act(TGS_FuncMTU_OutTyped_In1<T, T> setter, TGS_FuncMTU_OutBool_In1<T> validator, Type type) {
            this.setter = setter;
            this.validator = validator;
            this.type = type;
        }
        public TGS_FuncMTU_OutTyped_In1<T, T> setter;
        public TGS_FuncMTU_OutBool_In1<T> validator;
        public Type type;

    }
    private final List<Act<T>> pack = new ArrayList();

    public TGS_FuncMTUEffectivelyFinal<T> anoint(TGS_FuncMTU_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, null, Type.SKIPPER));
        return this;
    }

    public TGS_FuncMTUEffectivelyFinal<T> coronateIf(TGS_FuncMTU_OutBool_In1<T> validate) {
        pack.add(new Act(null, validate, Type.STOPPER));
        return this;
    }

    public TGS_FuncMTUEffectivelyFinal<T> anointIf(TGS_FuncMTU_OutBool_In1<T> validate, TGS_FuncMTU_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, validate, Type.SKIPPER));
        return this;
    }

    public TGS_FuncMTUEffectivelyFinal<T> anointAndCoronateIf(TGS_FuncMTU_OutBool_In1<T> validate, TGS_FuncMTU_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, validate, Type.STOPPER));
        return this;
    }

//    //TODO coronateWithException EXCEPTION HANDLING, NOT TESTED
//    @Deprecated
//    public TGS_Tuple2<T, Exception> coronateWithException() {
//        return TGS_FuncMTCUtils.call(() -> TGS_Tuple2.of(coronate(), null), e -> TGS_Tuple2.of(null, e));
//    }
    public T coronateAs(TGS_FuncMTU_OutTyped_In1<T, T> setter) {
        pack.add(new Act(setter, null, Type.STOPPER));
        return coronate();
    }

//FETCHER
    public T coronate() {
        for (var comp : pack) {
            if (comp.validator != null && !comp.validator.validate(bufferedValue)) {
                continue;
            }
            if (comp.setter != null) {
                bufferedValue = comp.setter.call(bufferedValue);
            }
            if (comp.type == Type.STOPPER) {
                return bufferedValue;
            }
        }
        return bufferedValue;
    }
}
