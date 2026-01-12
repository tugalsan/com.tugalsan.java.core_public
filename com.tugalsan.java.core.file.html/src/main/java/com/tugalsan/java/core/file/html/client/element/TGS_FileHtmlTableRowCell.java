package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.cast.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.*;
import com.tugalsan.java.core.string.client.*;
import java.util.*;
import java.util.stream.*;

public class TGS_FileHtmlTableRowCell extends TGS_FileHtmlElement {

    public static int counter = 0;

    public void setRowspan_Properties0(CharSequence rowspan) {
        properties.get(0).value = rowspan.toString();
    }

    public String getRowspan_Properties0() {
        return properties.get(0).value;
    }

    public void setColspan_Properties1(CharSequence colspan) {
        properties.get(1).value = colspan.toString();
    }

    public String getColspan_Properties1() {
        return properties.get(1).value;
    }

    public void setStyle_Properties2(CharSequence style) {
        properties.get(2).value = style.toString();
    }

    public String getStyle_Properties2() {
        return properties.get(2).value;
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }

    public TGS_FileHtmlTableRowCell(boolean header, TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence rowspan, CharSequence colspan, CharSequence style) {
        super(escapeHTML, header ? "th" : "td", nameAndId);
        counter++;
        properties.add(new TGS_FileHtmlProperty("rowspan", String.valueOf(TGS_CastUtils.toInteger(rowspan, 1))));
        properties.add(new TGS_FileHtmlProperty("colspan", String.valueOf(TGS_CastUtils.toInteger(colspan, 1))));
        properties.add(new TGS_FileHtmlProperty("style", TGS_StringUtils.cmn().toEmptyIfNull(style)));
    }

    private boolean header = false;

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
        tag = header ? "th" : "td";
    }

    @Override
    public String toString(boolean addNameAndId, boolean addProperties, boolean addChilderenAndCloseTag) {
        var sb = new StringBuilder();
        sb.append("<").append(tag);
        if (addNameAndId) {
            sb.append(" id='").append(nameAndId).append("'");
            sb.append(" name='").append(nameAndId).append("'");
        }
        var htmlTDFix = "overflow-wrap:normal;word-wrap:break-word;";
        properties.forEach(p -> {
            if ("1".equals(p.value)) {
                if ("rowspan".equals(p.name) || "colspan".equals(p.name)) {
//                    System.out.println("TGS_FileHtmlTableRowCell.name:" + p.name + ", p.value:" + p.value);
                    return;
                }
            }
            //OLD CODE
            if ("style".equals(p.name)) {
                var pStr = TGS_StringUtils.cmn().concat(" ", p.name, "='", htmlTDFix, p.value, "'");
                sb.append(pStr);
                return;
            }
            if (p.value.isEmpty()) {
                return;
            }
            {
                var pStr = TGS_StringUtils.cmn().concat(" ", p.name, "='", p.value, "'");
                sb.append(pStr);
            }
        });
        sb.append(">\n");
        childeren.stream().forEachOrdered(s -> sb.append(s));
        sb.append("</").append(tag).append(">\n");
        return sb.toString();
    }
}
