package com.tugalsan.java.core.serialcom.server;

import module com.tugalsan.java.core.serialcom;

public class TS_SerialComStopBits {

    private TS_SerialComStopBits(TS_SerialComDataBits dataBits, TS_SerialComUtils.STOP_BITS stopBits) {
        this.dataBits = dataBits;
        this.stopBits = stopBits;
    }
    final protected TS_SerialComDataBits dataBits;
    final protected TS_SerialComUtils.STOP_BITS stopBits;

    public static TS_SerialComStopBits of(TS_SerialComDataBits dataBits, TS_SerialComUtils.STOP_BITS stopBits) {
        return new TS_SerialComStopBits(dataBits, stopBits);
    }

    public TS_SerialComParity parity(TS_SerialComUtils.PARITY parity) {
        return TS_SerialComParity.of(this, parity);
    }

    public TS_SerialComParity parityNone() {
        return TS_SerialComParity.of(this, TS_SerialComUtils.PARITY.NO_PARITY);
    }

    public TS_SerialComParity parityEven() {
        return TS_SerialComParity.of(this, TS_SerialComUtils.PARITY.EVEN_PARITY);
    }

    public TS_SerialComParity parityOdd() {
        return TS_SerialComParity.of(this, TS_SerialComUtils.PARITY.ODD_PARITY);
    }
}
