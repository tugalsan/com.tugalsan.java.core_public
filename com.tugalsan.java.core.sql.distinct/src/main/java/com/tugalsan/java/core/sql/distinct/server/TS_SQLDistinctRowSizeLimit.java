package com.tugalsan.java.core.sql.distinct.server;

public class TS_SQLDistinctRowSizeLimit {

    public TS_SQLDistinctRowSizeLimit(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    private final TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctExecutor rowSizeLimitNone() {
        return executor;
    }

    public TS_SQLDistinctExecutor rowSizeLimit(int rowSizeLimit) {
        executor.rowSizeLimit = rowSizeLimit;
        return executor;
    }
}
