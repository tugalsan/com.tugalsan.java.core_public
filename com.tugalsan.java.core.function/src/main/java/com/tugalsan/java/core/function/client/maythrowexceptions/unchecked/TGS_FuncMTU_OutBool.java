package com.tugalsan.java.core.function.client.maythrowexceptions.unchecked;

public interface TGS_FuncMTU_OutBool extends /*Validateable,*/ TGS_FuncMTU_OutTyped<Boolean> {

    @Override
    default Boolean call() {
        return validate();
    }

    public boolean validate();
}
