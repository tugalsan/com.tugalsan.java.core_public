package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import com.tugalsan.java.core.file.html.client.*;

public class TGC_FileHtmlEscape implements TGS_FuncMTU_OutTyped_In1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TGC_FileHtmlUtils.toSafeHtml(unsafeHtmlText);
    }
}
