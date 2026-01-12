package com.tugalsan.java.core.serialcom.server;


import module com.tugalsan.java.core.serialcom;
import module com.tugalsan.java.core.function;

public class TS_SerialComParity {

    private TS_SerialComParity(TS_SerialComStopBits stopBits, TS_SerialComUtils.PARITY parity) {
        this.stopBits = stopBits;
        this.parity = parity;
    }
    final protected TS_SerialComStopBits stopBits;
    final protected TS_SerialComUtils.PARITY parity;

    public static TS_SerialComParity of(TS_SerialComStopBits stopBits, TS_SerialComUtils.PARITY parity) {
        return new TS_SerialComParity(stopBits, parity);
    }

    public TS_SerialComOnPortError onPortError(TGS_FuncMTU portError) {
        return TS_SerialComOnPortError.of(this, portError);
    }
}
