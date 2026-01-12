package com.tugalsan.java.core.serialcom.server;

import module com.tugalsan.java.core.function;

public class TS_SerialComOnPortError {

    private TS_SerialComOnPortError(TS_SerialComParity parity, TGS_FuncMTU portError) {
        this.parity = parity;
        this.portError = portError;
    }
    final protected TS_SerialComParity parity;
    final protected TGS_FuncMTU portError;

    public static TS_SerialComOnPortError of(TS_SerialComParity parity, TGS_FuncMTU portError) {
        return new TS_SerialComOnPortError(parity, portError);
    }

    public TS_SerialComOnSetupError onSetupError(TGS_FuncMTU setupError) {
        return TS_SerialComOnSetupError.of(this, setupError);
    }
}
