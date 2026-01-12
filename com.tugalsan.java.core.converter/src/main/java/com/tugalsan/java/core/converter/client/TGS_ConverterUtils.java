package com.tugalsan.java.core.converter.client;

public class TGS_ConverterUtils {
    
    private TGS_ConverterUtils(){
        
    }

    public static Double castBytes2KB(long bytesLength) {
        return bytesLength / (double) (1024);
    }

    public static Double castBytes2MB(long bytesLength) {
        return bytesLength / (double) (1024 * 1024);
    }

    public static Double castBytes2GB(long bytesLength) {
        return bytesLength / (double) (1024 * 1024 * 1024);
    }

    public static Double castBytes2TB(long bytesLength) {
        return bytesLength / (double) (1024 * 1024 * 1024 * 1024);
    }
}
