package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;

public class TGS_FileHtmlLink extends TGS_FileHtmlElement {

    public void setHref_Properties0(CharSequence href) {
        properties.get(0).value = href.toString();
    }

    public String getHref_Properties0() {
        return properties.get(0).value;
    }

    public TGS_FileHtmlLink(TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence href) {
        super(escapeHTML, "a", nameAndId);
        properties.add(new TGS_FileHtmlProperty("href", href));
    }
}
