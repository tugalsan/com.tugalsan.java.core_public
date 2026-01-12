package com.tugalsan.java.core.gwt.jdk.client;

import java.util.function.Supplier;

public class TGC_StableSupplier<T> implements Supplier<T> {

    private volatile Supplier<T> supplier;
    private volatile T value;

    public TGC_StableSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {//GWT IS SINGLE THREAD
        if (value == null) {
//            synchronized (this) {
//                if (value == null) {
            value = supplier.get();
//                }
//            }
        }
        return value;
    }
}
