package com.tugalsan.java.core.gui.visualization.server;

import com.tugalsan.java.core.string.client.TGS_StringUtils;

public class TS_VisualOrgChart_ConfigBalloon {

    private TS_VisualOrgChart_ConfigBalloon(String id, String parentId, String tooltip, String htmlHeader, String htmlText) {
        this.id = id;
        this.parentId = withoutThroublingChars(parentId);
        this.tooltip = withoutThroublingChars(tooltip);
        this.htmlHeader = withoutThroublingChars(htmlHeader);
        this.htmlText = withoutThroublingChars(htmlText);
    }
    public String id, parentId, tooltip, htmlHeader, htmlText;

    public TS_VisualOrgChart_ConfigBalloon cloneIt() {
        return of(id, parentId, tooltip, htmlHeader, htmlText);
    }

    private String withoutThroublingChars(String text) {
        return text.replace("\n", " ");
    }

    public static TS_VisualOrgChart_ConfigBalloon of(String id, String parentId, String tooltip, String htmlHeader, String htmlText) {
        return new TS_VisualOrgChart_ConfigBalloon(id, parentId, tooltip, htmlHeader, htmlText);
    }

    @Override
    public String toString() {
        return TS_VisualOrgChart_ConfigBalloon.class.getSimpleName() + "{" + "id=" + id + ", parentId=" + parentId + ", tooltip=" + tooltip + ", htmlHeader=" + htmlHeader + ", htmlText=" + htmlText + '}';
    }
}
