package com.tugalsan.java.core.function.client.maythrowexceptions.checked;

public interface TGS_FuncMTC_OutBool extends /*Validateable,*/ TGS_FuncMTC_OutTyped<Boolean> {

//    @Override
    @Deprecated
    default Boolean call() throws Exception {
        return validate();
    }

    public boolean validate() throws Exception;
}
