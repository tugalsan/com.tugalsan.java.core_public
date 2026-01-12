package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

import java.util.*;

public class TGS_FuncMTCEffectivelyFinal<T> {

    //CONSTRUCTOR
    private T bufferedValue;

    public TGS_FuncMTCEffectivelyFinal(T initVal) {
        bufferedValue = initVal;
    }

    public static <T> TGS_FuncMTCEffectivelyFinal<T> of(Class<T> clazz) {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    public static <T> TGS_FuncMTCEffectivelyFinal<T> of(T initialValue) {
        return new TGS_FuncMTCEffectivelyFinal(initialValue);
    }

    public static TGS_FuncMTCEffectivelyFinal<String> ofStr() {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    public static TGS_FuncMTCEffectivelyFinal<Long> ofLng() {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    public static TGS_FuncMTCEffectivelyFinal<Integer> ofInt() {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    public static TGS_FuncMTCEffectivelyFinal<Double> ofDbl() {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    public static TGS_FuncMTCEffectivelyFinal<Boolean> ofBool() {
        return new TGS_FuncMTCEffectivelyFinal(null);
    }

    //LOADERS
    private static enum Type {
        SKIPPER, STOPPER
    }

    private class Act<T> {

        public Act(TGS_FuncMTC_OutTyped_In1<T, T> setter, TGS_FuncMTC_OutBool_In1<T> validator, Type type) {
            this.setter = setter;
            this.validator = validator;
            this.type = type;
        }
        public TGS_FuncMTC_OutTyped_In1<T, T> setter;
        public TGS_FuncMTC_OutBool_In1<T> validator;
        public Type type;

    }
    private final List<Act<T>> pack = new ArrayList();

    public TGS_FuncMTCEffectivelyFinal<T> anoint(TGS_FuncMTC_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, null, Type.SKIPPER));
        return this;
    }

    public TGS_FuncMTCEffectivelyFinal<T> coronateIf(TGS_FuncMTC_OutBool_In1<T> validate) {
        pack.add(new Act(null, validate, Type.STOPPER));
        return this;
    }

    public TGS_FuncMTCEffectivelyFinal<T> anointIf(TGS_FuncMTC_OutBool_In1<T> validate, TGS_FuncMTC_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, validate, Type.SKIPPER));
        return this;
    }

    public TGS_FuncMTCEffectivelyFinal<T> anointAndCoronateIf(TGS_FuncMTC_OutBool_In1<T> validate, TGS_FuncMTC_OutTyped_In1<T, T> val) {
        pack.add(new Act(val, validate, Type.STOPPER));
        return this;
    }

//    //TODO coronateWithException EXCEPTION HANDLING, NOT TESTED
//    @Deprecated
//    public TGS_Tuple2<T, Exception> coronateWithException() {
//        return TGS_FuncMTCUtils.call(() -> TGS_Tuple2.of(coronate(), null), e -> TGS_Tuple2.of(null, e));
//    }
    public T coronateAs(TGS_FuncMTC_OutTyped_In1<T, T> setter) throws Exception {
        pack.add(new Act(setter, null, Type.STOPPER));
        return coronate();
    }

//FETCHER
    public T coronate() throws Exception {
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
