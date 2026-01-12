package com.tugalsan.java.core.servlet.gwt.webapp.client;

import com.google.gwt.user.client.rpc.*;

public interface TGS_SGWTServiceInterfaceAsync {

    public void call(TGS_SGWTFuncBase funcBase, AsyncCallback<TGS_SGWTFuncBase> callback);
}
