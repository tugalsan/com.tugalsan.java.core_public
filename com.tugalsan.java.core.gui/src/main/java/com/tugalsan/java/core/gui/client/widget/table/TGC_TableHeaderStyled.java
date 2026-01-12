package com.tugalsan.java.core.gui.client.widget.table;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_TableHeaderStyled extends ClickableTextCell {

    String style;

    public TGC_TableHeaderStyled() {
        super();
        style = "TGC_TableHeaderStyled";
    }

    @Override
    protected void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.appendHtmlConstant("<div class=\"" + style + "\">");
            sb.append(value);
            sb.appendHtmlConstant("</div>");
        }
    }

    public void addStyleName(CharSequence style) {
        this.style = style.toString();
    }
}
