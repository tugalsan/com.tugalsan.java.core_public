package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;

abstract public class TGC_GLLoadable {

    public static int STATUS_INIT() {
        return 0;
    }

    public static int STATUS_LOADING() {
        return 1;
    }

    public static int STATUS_LOADED() {
        return 2;
    }

    public static int STATUS_ERROR() {
        return 3;
    }

    public TGC_GLProgramAbstract program;

    public Integer progress = null;
    public int status = STATUS_INIT();
    public String errorMessage = null;

    public TGC_GLLoadable(TGC_GLProgramAbstract program) {
        this.program = program;
    }

    abstract public void lazyLoad();
}
