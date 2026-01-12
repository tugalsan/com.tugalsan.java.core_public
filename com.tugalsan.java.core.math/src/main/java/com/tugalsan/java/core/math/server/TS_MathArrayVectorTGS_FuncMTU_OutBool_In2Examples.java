package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import module jdk.incubator.vector;

public class TS_MathArrayVectorTGS_FuncMTU_OutBool_In2Examples {

    private TS_MathArrayVectorTGS_FuncMTU_OutBool_In2Examples() {

    }

    public static class FuncByte {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Byte>, ByteVector, ByteVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }

    public static class FuncShort {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Short>, ShortVector, ShortVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }

    public static class FuncInteger {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Integer>, IntVector, IntVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }

    public static class FuncLong {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Long>, LongVector, LongVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }

    public static class FuncFloat {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Float>, FloatVector, FloatVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }

    public static class FuncDouble {

        public static TGS_FuncMTU_OutTyped_In2<VectorMask<Double>, DoubleVector, DoubleVector> func_eq() {
            return (va, vb) -> va.eq(vb);
        }
    }
}
