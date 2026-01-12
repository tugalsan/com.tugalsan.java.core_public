package com.tugalsan.java.core.file.html.server.element;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.file.html;

public class TS_FileHtmlEscape implements TGS_FuncMTU_OutTyped_In1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TS_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
