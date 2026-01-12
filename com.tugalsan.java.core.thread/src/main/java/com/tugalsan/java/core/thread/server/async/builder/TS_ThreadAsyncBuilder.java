package com.tugalsan.java.core.thread.server.async.builder;

import module com.tugalsan.java.core.thread;

public class TS_ThreadAsyncBuilder {

    private TS_ThreadAsyncBuilder() {

    }

    public static <T> TS_ThreadAsyncBuilder0Kill<T> of(TS_ThreadSyncTrigger killTrigger) {
        return new TS_ThreadAsyncBuilder0Kill(killTrigger);
    }
}
