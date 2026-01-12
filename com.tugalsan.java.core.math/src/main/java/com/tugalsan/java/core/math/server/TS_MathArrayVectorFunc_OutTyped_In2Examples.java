package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;
import module jdk.incubator.vector;

public class TS_MathArrayVectorFunc_OutTyped_In2Examples {

    private TS_MathArrayVectorFunc_OutTyped_In2Examples() {

    }

    public static class FuncByte {
//    public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_pow() {
//        return (va, vb) -> va.pow(vb);
//    }

        public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

//    public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_normal() {
//        return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
//    }
        public static TGS_FuncMTU_OutTyped_In2<ByteVector, ByteVector, ByteVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }

    }

    public static class FuncShort {
//    public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_pow() {
//        return (va, vb) -> va.pow(vb);
//    }
//

        public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

//    public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_normal() {
//        return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
//    }
        public static TGS_FuncMTU_OutTyped_In2<ShortVector, ShortVector, ShortVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }

    }

    public static class FuncInteger {
//    public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_pow() {
//        return (va, vb) -> va.pow(vb);
//    }

        public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

//    public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_normal() {
//        return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
//    }
        public static TGS_FuncMTU_OutTyped_In2<IntVector, IntVector, IntVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }
    }

    public static class FuncLong {
//    public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_pow() {
//        return (va, vb) -> va.pow(vb);
//    }

        public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

//    public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_normal() {
//        return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
//    }
        public static TGS_FuncMTU_OutTyped_In2<LongVector, LongVector, LongVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }
    }

    public static class FuncFloat {

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_pow() {
            return (va, vb) -> va.pow(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_normal() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
        }

        public static TGS_FuncMTU_OutTyped_In2<FloatVector, FloatVector, FloatVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }
    }

    public static class FuncDouble {

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_pow() {
            return (va, vb) -> va.pow(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_div() {
            return (va, vb) -> va.div(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_mul() {
            return (va, vb) -> va.mul(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_sub() {
            return (va, vb) -> va.sub(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_add() {
            return (va, vb) -> va.add(vb);
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_normal() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).sqrt();
        }

        public static TGS_FuncMTU_OutTyped_In2<DoubleVector, DoubleVector, DoubleVector> func_unknown() {
            return (va, vb) -> va.mul(va).add(vb.mul(vb)).neg();
        }
    }
}
