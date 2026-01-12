package com.tugalsan.java.core.file.html.client.element;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.string.client.TGS_StringUtils;
import java.util.*;

public class TGS_FileHtmlElement {

    public boolean DEFAULT_isNameAndIdEnabled = false;

    protected String nameAndId;
    protected String tag;
    protected List<TGS_FileHtmlElement> childeren;
    protected List<TGS_FileHtmlProperty> properties;
    protected String slotText;
    protected TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML;

    public TGS_FileHtmlElement(TGS_FuncMTU_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence tag, CharSequence nameAndId) {
        this.escapeHTML = escapeHTML;
        this.nameAndId = nameAndId == null ? null : nameAndId.toString();
        this.tag = tag == null ? null : tag.toString();
        childeren = TGS_ListUtils.of();
        properties = TGS_ListUtils.of();
    }

    public String getStyleClassName() {
        return syleClassName;
    }

    final public void setSyleClassName(String syleClassName) {
        this.syleClassName = syleClassName;
    }
    private String syleClassName;

    @Override
    public String toString() {
        return toString(DEFAULT_isNameAndIdEnabled, true, true);
    }

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
        if (tag.equals("span")) {
            var slotTextNotNull = TGS_StringUtils.cmn().toEmptyIfNull(slotText);
//            System.out.println("TGS_FileHtmlElement.customSpan.slotTextNotNull:" + slotTextNotNull);
            sb.append(escapeHTML == null ? slotTextNotNull : escapeHTML.call(slotTextNotNull));
            sb.append("</").append(tag).append(">\n");
        } else if (addChilderenAndCloseTag) {
            childeren.stream().forEachOrdered(s -> sb.append("  ").append(s));
            sb.append("</").append(tag).append(">\n");
        }
        return sb.toString();
    }
}
