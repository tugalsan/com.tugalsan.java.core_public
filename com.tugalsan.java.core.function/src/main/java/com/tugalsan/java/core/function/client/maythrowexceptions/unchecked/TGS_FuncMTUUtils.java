package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

import com.tugalsan.java.core.function.client.TGS_FuncUtils;

public class TGS_FuncMTUUtils {

    private TGS_FuncMTUUtils() {

    }

    //-------------------THROW-----------------
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void _throwAsUncheckedException(Throwable exception) throws T {
        throw (T) exception;
    }

    @Deprecated //only internalUse
    private static void throwAsUncheckedException(Throwable exception) {
        TGS_FuncMTUUtils.<RuntimeException>_throwAsUncheckedException(exception);
    }

    public static <R> R thrw(CharSequence className, CharSequence funcName, Object errorContent) {
        throw toRuntimeException(className, funcName, errorContent);
    }

    public static <R> R thrw(Throwable t) {
        TGS_FuncUtils.throwIfInterruptedException(t);
        throwAsUncheckedException(t);
        return null;
    }

    //-------------------RUNTIME EXCEPTION
    public static RuntimeException toRuntimeException(CharSequence className, CharSequence funcName, Object errorContent) {
        return new RuntimeException(TGS_FuncUtils.class + ".toRuntimeException->CLASS[" + className + "] -> FUNC[" + funcName + "] -> ERR: " + errorContent);
    }

    //---------------------RUN---------------------
    public static void run(TGS_FuncMTU exe) {
        run(exe, null);
    }

    public static void run(TGS_FuncMTU exe, TGS_FuncMTU_In1<Exception> exception) {
        run(exe, exception, null);
    }

    public static void run(TGS_FuncMTU exe, TGS_FuncMTU_In1<Exception> exception, TGS_FuncMTU finalExe) {
        try {
            if (exe != null) {
                exe.run();
            }
        } catch (Exception e) {
            TGS_FuncUtils.throwIfInterruptedException(e);
            if (exception == null) {
                throwAsUncheckedException(e);
            }
            exception.run(e);
        } finally {
            if (finalExe != null) {
                finalExe.run();
            }
        }
    }

    //---------------------CALL---------------------
    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp) {
        return call(cmp, null);
    }

    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception) {
        return call(cmp, exception, null);
    }

    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception, TGS_FuncMTU finalExe) {
        try {
            return cmp.call();
        } catch (Exception e) {
            TGS_FuncUtils.throwIfInterruptedException(e);
            if (exception == null) {
                throwAsUncheckedException(e);
            }
            return exception.call(e);
        } finally {
            if (finalExe != null) {
                finalExe.run();
            }
        }
    }

    //-------------------------UTILS
    public static <T> T Null() {
        return (T) null;
    }

    public static TGS_FuncMTU_OutTyped doNothing0() {
        return () -> Null();
    }

    public static TGS_FuncMTU_OutTyped_In1 doNothing1() {
        return w1 -> Null();
    }

    public static TGS_FuncMTU_OutTyped_In2 doNothing2() {
        return (w1, w2) -> Null();
    }

    public static TGS_FuncMTU_OutTyped_In3 doNothing3() {
        return (w1, w2, w3) -> Null();
    }

    public static TGS_FuncMTU_OutTyped_In4 doNothing4() {
        return (w1, w2, w3, w4) -> Null();
    }

    public static TGS_FuncMTU_OutTyped_In5 doNothing5() {
        return (w1, w2, w3, w4, w5) -> Null();
    }

    public static <R> TGS_FuncMTU toVoid(TGS_FuncMTU_OutTyped<R> call) {
        return () -> call.call();
    }

    public static <R> TGS_FuncMTU_OutTyped<R> toR(TGS_FuncMTU call) {
        return () -> {
            call.call();
            return null;
        };
    }

    public static <R, A> TGS_FuncMTU_In1<A> toVoid(TGS_FuncMTU_OutTyped_In1<R, A> call) {
        return a -> call.call(a);
    }

    public static <R, A> TGS_FuncMTU_OutTyped_In1<R, A> toR(TGS_FuncMTU_In1<A> call) {
        return a -> {
            call.call(a);
            return null;
        };
    }

    public static <R, A> TGS_FuncMTU_OutTyped_In1<R, A> toR(R objectAsInVoidButNotNull, TGS_FuncMTU_In1<A> call) {
        return a -> {
            call.call(a);
            return objectAsInVoidButNotNull;
        };
    }

    public static <R, A, B> TGS_FuncMTU_In2<A, B> toVoid(TGS_FuncMTU_OutTyped_In2<R, A, B> call) {
        return (a, b) -> call.call(a, b);
    }

    public static <R, A, B> TGS_FuncMTU_OutTyped_In2<R, A, B> toR(TGS_FuncMTU_In2<A, B> call) {
        return (a, b) -> {
            call.call(a, b);
            return null;
        };
    }

    public static <R, A, B, C> TGS_FuncMTU_In3<A, B, C> toVoid(TGS_FuncMTU_OutTyped_In3<R, A, B, C> call) {
        return (a, b, c) -> call.call(a, b, c);
    }

    public static <R, A, B, C> TGS_FuncMTU_OutTyped_In3<R, A, B, C> toR(TGS_FuncMTU_In3<A, B, C> call) {
        return (a, b, c) -> {
            call.call(a, b, c);
            return null;
        };
    }

    public static <R, A, B, C, D> TGS_FuncMTU_In4<A, B, C, D> toVoid(TGS_FuncMTU_OutTyped_In4<R, A, B, C, D> call) {
        return (a, b, c, d) -> call.call(a, b, c, d);
    }

    public static <R, A, B, C, D> TGS_FuncMTU_OutTyped_In4<R, A, B, C, D> toR(TGS_FuncMTU_In4<A, B, C, D> call) {
        return (a, b, c, d) -> {
            call.call(a, b, c, d);
            return null;
        };
    }

    public static <R, A, B, C, D, E> TGS_FuncMTU_In5<A, B, C, D, E> toVoid(TGS_FuncMTU_OutTyped_In5<R, A, B, C, D, E> call) {
        return (a, b, c, d, e) -> call.call(a, b, c, d, e);
    }

    public static <R, A, B, C, D, E> TGS_FuncMTU_OutTyped_In5<R, A, B, C, D, E> toR(TGS_FuncMTU_In5<A, B, C, D, E> call) {
        return (a, b, c, d, e) -> {
            call.call(a, b, c, d, e);
            return null;
        };
    }
}
