package com.tugalsan.java.core.thread.server.async.run;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.thread;
import java.time.*;

//USE TS_ThreadAsyncBuilder with killTrigger if possible
public class TS_ThreadAsyncRun {

    private TS_ThreadAsyncRun() {

    }

    final private static TS_Log d = TS_Log.of(false, TS_ThreadAsyncRun.class);

    public static Thread now(TS_ThreadSyncTrigger killTrigger, TGS_FuncMTU_In1<TS_ThreadSyncTrigger> exe) {
        return Thread.startVirtualThread(() -> exe.run(killTrigger));
    }

    public static Thread until(TS_ThreadSyncTrigger killTrigger, Duration until, TGS_FuncMTU_In1<TS_ThreadSyncTrigger> exe) {
        if (until == null) {
            return now(killTrigger, exe);
        }
        return Thread.startVirtualThread(() -> {
            TS_ThreadAsyncAwait.runUntil(killTrigger, until, kt_wt -> exe.run(kt_wt));
        });
    }
}
