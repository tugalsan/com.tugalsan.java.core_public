package com.tugalsan.java.core.gui.client.card;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_Card {

    public CharSequence optional_urlImgForeground;
    public CharSequence optional_urlImgBackground;
    public CharSequence htmlContent;
    public TGS_FuncMTU optionalAction;
    public boolean span_nocolor = false;

    public TGC_Card() {
        span_nocolor = true;
    }

    public TGC_Card(CharSequence optional_urlImgForeground, CharSequence optional_urlImgBackground, CharSequence htmlContent) {
        this.optional_urlImgForeground = optional_urlImgForeground;
        this.optional_urlImgBackground = optional_urlImgBackground;
        this.htmlContent = htmlContent;
    }

    public TGC_Card(CharSequence optional_urlImgForeground, CharSequence optional_urlImgBackground, CharSequence htmlContent, TGS_FuncMTU optionalAction) {
        this(optional_urlImgForeground, optional_urlImgBackground, htmlContent);
        this.optionalAction = optionalAction;
    }
}
