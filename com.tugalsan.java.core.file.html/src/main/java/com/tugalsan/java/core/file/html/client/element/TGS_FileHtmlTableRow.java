package com.tugalsan.java.core.file.html.client.element;

import java.util.List;

public class TGS_FileHtmlTableRow extends TGS_FileHtmlElement {

    public static int counter = 0;

    public TGS_FileHtmlTableRow(CharSequence nameAndId) {
        super(null, "tr", nameAndId);
        counter++;
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }

    private boolean isHeader = false;

    public boolean IsHeader() {
        return isHeader;
    }

    public void setHeader(boolean isHeader) {
        this.isHeader = isHeader;
        getChilderen().forEach(e -> {
            if (e instanceof TGS_FileHtmlTableRowCell) {
                var c = (TGS_FileHtmlTableRowCell) e;
                c.setHeader(isHeader);
            }
        });
    }
    
    
    @Override
    public String toString(boolean addNameAndId, boolean addProperties, boolean addChilderenAndCloseTag) {
        var sb = new StringBuilder();
        if (isHeader) {
            sb.append("<thead>");
        }
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
                if (properties.get(i).value.isEmpty()){
                    continue;
                }
                sb.append(" ").append(properties.get(i).name).append("='").append(properties.get(i).value).append("'");
            }
            sb.append(addChilderenAndCloseTag ? "" : "/").append(">\n");
        }
        if (addChilderenAndCloseTag) {
            childeren.stream().forEachOrdered(s -> sb.append("  ").append(s));
            sb.append("</").append(tag).append(">\n");
        }
        if (isHeader) {
            sb.append("</thead>");
        }
        return sb.toString();
    }
}
