package com.tugalsan.java.core.union.client;

public class TGS_UnionException extends RuntimeException {

    private TGS_UnionException(Throwable t) {
        super(t);
    }

    public static TGS_UnionException of(TGS_UnionExcuse e) {
        return new TGS_UnionException(e.excuse());
    }

    public static TGS_UnionException of(TGS_UnionExcuseVoid e) {
        return new TGS_UnionException(e.excuse());
    }
}
