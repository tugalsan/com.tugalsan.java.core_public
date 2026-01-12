package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import com.tugalsan.java.core.string.client.TGS_StringUtils;

public class TGS_FileHtmlSpan extends TGS_FileHtmlElement {

    public static int counter = 0;
    public boolean pureCode = false;

    public void setStyle_Properties0(CharSequence style) {
        properties.get(0).value = style.toString();
    }

    public String getStyle_Properties0() {
        return properties.get(0).value;
    }

    public TGS_FileHtmlSpan(TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence spanText, CharSequence style) {
        super(escapeHTML, "span", nameAndId);
        counter++;
        super.slotText = spanText.toString();
        properties.add(new TGS_FileHtmlProperty("style", style));
    }

    @Override
    public String toString(boolean addNameAndId, boolean addProperties, boolean addChilderenAndCloseTag) {
        var sb = new StringBuilder();
        {
            sb.append("<").append(tag);
            if (addNameAndId) {
                sb.append(" id='").append(nameAndId).append("'");
                sb.append(" name='").append(nameAndId).append("'");
            }
            if (getStyleClassName() != null) {
                sb.append(" class='").append(getStyleClassName()).append("'");
            }
            for (var i = 0; addProperties && i < properties.size(); i++) {
                if (properties.get(i).value.isEmpty()) {
                    continue;
                }
                sb.append(" ").append(properties.get(i).name).append("='").append(properties.get(i).value).append("'");
            }
            sb.append(addChilderenAndCloseTag ? "" : "/").append(">\n");
        }
        {
            var slotTextNotNull = TGS_StringUtils.cmn().toEmptyIfNull(slotText);
            if (pureCode) {//html span
//                System.out.println("TGS_FileHtmlSpan.pureCode.slotTextNotNull:" + slotTextNotNull);
                sb.append(slotTextNotNull);
            } else {//normal span
//                System.out.println("TGS_FileHtmlSpan.normal.slotTextNotNull:" + slotTextNotNull);
                sb.append(escapeHTML == null ? slotTextNotNull : escapeHTML.call(slotTextNotNull));
            }
        }
        {
            sb.append("</").append(tag).append(">\n");
        }
        return sb.toString();
    }
}
