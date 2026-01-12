package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

import com.tugalsan.java.core.function.client.TGS_FuncUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import java.util.concurrent.*;

public class TGS_FuncMTCUtils {
    
    private TGS_FuncMTCUtils(){
        
    }

    //-------------------THROW-----------------
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void _throwAsUncheckedException(Throwable exception) throws T {
        throw (T) exception;
    }

    @Deprecated //only internalUse
    private static void throwAsUncheckedException(Throwable exception) {
        TGS_FuncMTCUtils.<RuntimeException>_throwAsUncheckedException(exception);
    }

    //NULL AND DO NOTHING
    public static <T> T Null() {
        return (T) null;
    }

    public static TGS_FuncMTC_OutTyped doNothing0() {
        return () -> Null();
    }

    //RUN
    public static void run(TGS_FuncMTC exe) {
        run(exe, null);
    }

    public static void run(TGS_FuncMTC exe, TGS_FuncMTU_In1<Exception> exception) {
        run(exe, exception, null);
    }

    public static void run(TGS_FuncMTC exe, TGS_FuncMTU_In1<Exception> exception, TGS_FuncMTU finalExe) {
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
    public static <R> R call(Callable<R> cmp) {
        return call(cmp, null);
    }

    public static <R> R call(Callable<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception) {
        return call(cmp, exception, null);
    }

    public static <R> R call(Callable<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception, TGS_FuncMTU finalExe) {
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

    public static TGS_FuncMTC_OutTyped_In1 doNothing1() {
        return w1 -> Null();
    }

    public static TGS_FuncMTC_OutTyped_In2 doNothing2() {
        return (w1, w2) -> Null();
    }

    public static TGS_FuncMTC_OutTyped_In3 doNothing3() {
        return (w1, w2, w3) -> Null();
    }

    public static TGS_FuncMTC_OutTyped_In4 doNothing4() {
        return (w1, w2, w3, w4) -> Null();
    }

    public static TGS_FuncMTC_OutTyped_In5 doNothing5() {
        return (w1, w2, w3, w4, w5) -> Null();
    }

    public static <R> TGS_FuncMTC toVoid(TGS_FuncMTC_OutTyped<R> call) {
        return () -> call.call();
    }

    public static <R> TGS_FuncMTC_OutTyped<R> toR(TGS_FuncMTC call) {
        return () -> {
            call.call();
            return null;
        };
    }

    public static <R, A> TGS_FuncMTC_In1<A> toVoid(TGS_FuncMTC_OutTyped_In1<R, A> call) {
        return a -> call.call(a);
    }

    public static <R, A> TGS_FuncMTC_OutTyped_In1<R, A> toR(TGS_FuncMTC_In1<A> call) {
        return a -> {
            call.call(a);
            return null;
        };
    }

    public static <R, A, B> TGS_FuncMTC_In2<A, B> toVoid(TGS_FuncMTC_OutTyped_In2<R, A, B> call) {
        return (a, b) -> call.call(a, b);
    }

    public static <R, A, B> TGS_FuncMTC_OutTyped_In2<R, A, B> toR(TGS_FuncMTC_In2<A, B> call) {
        return (a, b) -> {
            call.call(a, b);
            return null;
        };
    }

    public static <R, A, B, C> TGS_FuncMTC_In3<A, B, C> toVoid(TGS_FuncMTC_OutTyped_In3<R, A, B, C> call) {
        return (a, b, c) -> call.call(a, b, c);
    }

    public static <R, A, B, C> TGS_FuncMTC_OutTyped_In3<R, A, B, C> toR(TGS_FuncMTC_In3<A, B, C> call) {
        return (a, b, c) -> {
            call.call(a, b, c);
            return null;
        };
    }

    public static <R, A, B, C, D> TGS_FuncMTC_In4<A, B, C, D> toVoid(TGS_FuncMTC_OutTyped_In4<R, A, B, C, D> call) {
        return (a, b, c, d) -> call.call(a, b, c, d);
    }

    public static <R, A, B, C, D> TGS_FuncMTC_OutTyped_In4<R, A, B, C, D> toR(TGS_FuncMTC_In4<A, B, C, D> call) {
        return (a, b, c, d) -> {
            call.call(a, b, c, d);
            return null;
        };
    }

    public static <R, A, B, C, D, E> TGS_FuncMTC_In5<A, B, C, D, E> toVoid(TGS_FuncMTC_OutTyped_In5<R, A, B, C, D, E> call) {
        return (a, b, c, d, e) -> call.call(a, b, c, d, e);
    }

    public static <R, A, B, C, D, E> TGS_FuncMTC_OutTyped_In5<R, A, B, C, D, E> toR(TGS_FuncMTC_In5<A, B, C, D, E> call) {
        return (a, b, c, d, e) -> {
            call.call(a, b, c, d, e);
            return null;
        };
    }
}
