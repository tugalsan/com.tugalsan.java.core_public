package com.tugalsan.java.core.function.client;

import java.util.Optional;
import java.util.concurrent.TimeoutException;
import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTC;
import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTC_OutTyped;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTUUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;

public class TGS_FuncUtils {
    
    private TGS_FuncUtils(){
        
    }

    //-------------------- INTERRUPTED EXCEPTION ----------------
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void _throwAsUncheckedException(Throwable exception) throws T {
        throw (T) exception;
    }

    @Deprecated //only internalUse
    private static void throwAsUncheckedException(Throwable exception) {
        TGS_FuncUtils.<RuntimeException>_throwAsUncheckedException(exception);
    }

    public static <R> R throwIfInterruptedException(Throwable t) {
        if (isInterruptedException(t)) {
            Thread.currentThread().interrupt();
            throwAsUncheckedException(t);
        }
        return null;
    }

    public static Optional<TimeoutException> getTimeoutException(Throwable t) {
        if (t instanceof TimeoutException) {
            return Optional.of((TimeoutException) t);
        }
        if (t.getCause() != null) {
            return getTimeoutException(t.getCause());
        }
        return Optional.empty();
    }

    public static Optional<InterruptedException> getInterruptedException(Throwable t) {
        if (t instanceof InterruptedException) {
            return Optional.of((InterruptedException) t);
        }
        if (t.getCause() != null) {
            return getInterruptedException(t.getCause());
        }
        return Optional.empty();
    }

    public static boolean isInterruptedException(Throwable t) {
        if (t instanceof InterruptedException) {
            return true;
        }
        if (t.getCause() != null) {
            return isInterruptedException(t.getCause());
        }
        return false;
    }

    public static <R> R thrw(Throwable t) {
        throwIfInterruptedException(t);
        throwAsUncheckedException(t);
        return null;
    }

    //RUN
    public static void run(TGS_FuncMTU exe) {
        TGS_FuncMTUUtils.run(exe);
    }

    public static void run(TGS_FuncMTU exe, TGS_FuncMTU_In1<Exception> exception) {
        TGS_FuncMTUUtils.run(exe, exception);
    }

    public static void run(TGS_FuncMTU exe, TGS_FuncMTU_In1<Exception> exception, TGS_FuncMTU finalExe) {
        TGS_FuncMTUUtils.run(exe, exception, finalExe);
    }

    public static void run(TGS_FuncMTC exe) {
        TGS_FuncMTCUtils.run(exe);
    }

    public static void run(TGS_FuncMTC exe, TGS_FuncMTU_In1<Exception> exception) {
        TGS_FuncMTCUtils.run(exe, exception);
    }

    public static void run(TGS_FuncMTC exe, TGS_FuncMTU_In1<Exception> exception, TGS_FuncMTU finalExe) {
        TGS_FuncMTCUtils.run(exe, exception, finalExe);
    }

    //CALL
    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp) {
        return TGS_FuncMTUUtils.call(cmp);
    }

    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception) {
        return TGS_FuncMTUUtils.call(cmp, exception);
    }

    public static <R> R call(TGS_FuncMTU_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception, TGS_FuncMTU finalExe) {
        return TGS_FuncMTUUtils.call(cmp, exception, finalExe);
    }

    public static <R> R call(TGS_FuncMTC_OutTyped<R> cmp) {
        return TGS_FuncMTCUtils.call(cmp);
    }

    public static <R> R call(TGS_FuncMTC_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception) {
        return TGS_FuncMTCUtils.call(cmp, exception);
    }

    public static <R> R call(TGS_FuncMTC_OutTyped<R> cmp, TGS_FuncMTU_OutTyped_In1<R, Exception> exception, TGS_FuncMTU finalExe) {
        return TGS_FuncMTCUtils.call(cmp, exception, finalExe);
    }
}
