package com.tugalsan.java.core.math.server;

import module com.tugalsan.java.core.function;

public class TS_MathArrayScalarTGS_FuncMTU_OutTyped_In2Examples {

    private TS_MathArrayScalarTGS_FuncMTU_OutTyped_In2Examples() {

    }

    public static class FuncByte {
//    public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_pow() {
//        return (a, b) -> (int) Math.pow(a, b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_div() {
            return (a, b) -> (byte) (a / b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_mul() {
            return (a, b) -> (byte) (a * b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_sub() {
            return (a, b) -> (byte) (a - b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_add() {
            return (a, b) -> (byte) (a + b);
        }
//    public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_normal() {
//        return (a, b) -> (int) Math.sqrt(a * a + b * b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Byte, Byte, Byte> func_unknown() {
            return (a, b) -> (byte) ((a * a + b * b) * -1);
        }
    }

    public static class FuncShort {
//    public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_pow() {
//        return (a, b) -> (int) Math.pow(a, b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_div() {
            return (a, b) -> (short) (a / b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_mul() {
            return (a, b) -> (short) (a * b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_sub() {
            return (a, b) -> (short) (a - b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_add() {
            return (a, b) -> (short) (a + b);
        }
//    public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_normal() {
//        return (a, b) -> (int) Math.sqrt(a * a + b * b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Short, Short, Short> func_unknown() {
            return (a, b) -> (short) ((a * a + b * b) * -1);
        }
    }

    public static class FuncInteger {
//    public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_pow() {
//        return (a, b) -> (int) Math.pow(a, b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_div() {
            return (a, b) -> a / b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_mul() {
            return (a, b) -> a * b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_sub() {
            return (a, b) -> a - b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_add() {
            return (a, b) -> a + b;
        }

//    public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_normal() {
//        return (a, b) -> (int) Math.sqrt(a * a + b * b);
//    }
        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_unknown() {
            return (a, b) -> (a * a + b * b) * -1.0f;
        }
    }

    public static class FuncLong {
//    public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_pow() {
//        return (a, b) -> (int) Math.pow(a, b);
//    }

        public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_div() {
            return (a, b) -> a / b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_mul() {
            return (a, b) -> a * b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_sub() {
            return (a, b) -> a - b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_add() {
            return (a, b) -> a + b;
        }

//    public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_normal() {
//        return (a, b) -> (int) Math.sqrt(a * a + b * b);
//    }
        public static TGS_FuncMTU_OutTyped_In2<Long, Long, Long> func_unknown() {
            return (a, b) -> (a * a + b * b) * -1L;
        }
    }

    public static class FuncFloat {

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_pow() {
            return (a, b) -> (float) Math.pow(a, b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_div() {
            return (a, b) -> a / b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_mul() {
            return (a, b) -> a * b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_sub() {
            return (a, b) -> a - b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_add() {
            return (a, b) -> a + b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_normal() {
            return (a, b) -> (float) Math.sqrt(a * a + b * b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Float, Float, Float> func_unknown() {
            return (a, b) -> (a * a + b * b) * -1.0f;
        }
    }

    public static class FuncDouble {

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_pow() {
            return (a, b) -> (double) Math.pow(a, b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_div() {
            return (a, b) -> a / b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_mul() {
            return (a, b) -> a * b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_sub() {
            return (a, b) -> a - b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_add() {
            return (a, b) -> a + b;
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_normal() {
            return (a, b) -> (double) Math.sqrt(a * a + b * b);
        }

        public static TGS_FuncMTU_OutTyped_In2<Double, Double, Double> func_unknown() {
            return (a, b) -> (a * a + b * b) * -1.0f;
        }
    }
}
