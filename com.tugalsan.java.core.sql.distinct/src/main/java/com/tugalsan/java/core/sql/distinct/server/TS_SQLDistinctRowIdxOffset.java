package com.tugalsan.java.core.sql.distinct.server;

public class TS_SQLDistinctRowIdxOffset {

    public TS_SQLDistinctRowIdxOffset(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    private final TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctRowSizeLimit rowIdxOffsetNone() {
        return new TS_SQLDistinctRowSizeLimit(executor);
    }

    public TS_SQLDistinctRowSizeLimit rowIdxOffset(int rowIdxOffset) {
        executor.rowIdxOffset = rowIdxOffset;
        return new TS_SQLDistinctRowSizeLimit(executor);
    }
}
