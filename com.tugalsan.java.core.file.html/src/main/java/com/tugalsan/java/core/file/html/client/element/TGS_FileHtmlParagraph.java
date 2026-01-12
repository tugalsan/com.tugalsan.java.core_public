package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import java.util.List;

public class TGS_FileHtmlParagraph extends TGS_FileHtmlElement {

    public static int counter = 0;

    public void setStyle_Properties0(CharSequence style) {
        properties.get(0).value = style.toString();
    }

    public String getStyle_Properties0() {
        return properties.get(0).value;
    }

    public TGS_FileHtmlParagraph(TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence style) {
        super(escapeHTML, "p", nameAndId);
        counter++;
        properties.add(new TGS_FileHtmlProperty("style", style));
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }

}
