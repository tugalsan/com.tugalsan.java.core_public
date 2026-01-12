package com.tugalsan.java.core.file.html.client;

import com.tugalsan.java.core.network.client.*;
import java.util.*;

public class TGS_FileHtmlText {

    public static String getDefaultCustomCssForBlackText() {
        return "var(--colorTextPrimary)";
    }

    public String customCssForBlackText = getDefaultCustomCssForBlackText();

    public static TGS_FileHtmlText textWhite() {
        return new TGS_FileHtmlText().setBold(true).setHexcolor("FFFFFF");
    }

    public static TGS_FileHtmlText textInfo() {
        return new TGS_FileHtmlText().setBold(true).setHexcolor("999999");
    }

    public static TGS_FileHtmlText textResults() {
        return new TGS_FileHtmlText().setBold(true).setHexcolor("00AA00");
    }

    public static TGS_FileHtmlText textError() {
        return new TGS_FileHtmlText().setBold(true).setHexcolor("FF0000");
    }

    public static TGS_FileHtmlText textLink() {
        return new TGS_FileHtmlText().setBold(true).setHexcolor("0000FF");
    }

    public static String charLn() {
        return "<br>";
    }

    public static String charSpace() {
        return TGS_NetworkHTMLUtils.HTML_SPACE();
    }

    public static String charRightArrow() {
        return "&gt;";
    }

    public static String charLeftArrow() {
        return "&lt;";
    }

    private String text = "";
    private boolean italicized;
    private boolean bold;
    private boolean strikethrough;
    private boolean underlined;
    private boolean code;
    private String hexcolor;

    public boolean isBold() {
        return bold;
    }

    public TGS_FileHtmlText setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public boolean isCode() {
        return code;
    }

    public TGS_FileHtmlText setCode(boolean code) {
        this.code = code;
        return this;
    }

    public String getHexcolor() {
        return hexcolor;
    }

    public TGS_FileHtmlText setHexcolor(CharSequence hexcolor) {
        this.hexcolor = hexcolor.toString();
        return this;
    }

    public boolean isItalicized() {
        return italicized;
    }

    public TGS_FileHtmlText setItalicized(boolean italicized) {
        this.italicized = italicized;
        return this;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public TGS_FileHtmlText setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public String getText() {
        return text;
    }

    public TGS_FileHtmlText setText(CharSequence text) {
        this.text = text.toString();
        return this;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public TGS_FileHtmlText setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public TGS_FileHtmlText() {
        reset();
    }

    public final TGS_FileHtmlText reset() {
        text = "";
        italicized = false;
        bold = false;
        strikethrough = false;
        underlined = false;
        code = false;
        hexcolor = null;
        return this;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.setLength(0);
        if (hexcolor != null) {
            if (customCssForBlackText != null && Objects.equals(hexcolor, "000000")) {
                sb.append("<font color='").append(customCssForBlackText).append("'>");
            } else {
                sb.append("<font color='#").append(hexcolor).append("'>");
            }
        }
        if (isBold()) {
            sb.append("<b>");
        }
        if (isCode()) {
            sb.append("<code>");
        }
        if (isItalicized()) {
            sb.append("<i>");
        }
        if (isStrikethrough()) {
            sb.append("<strike>");
        }
        if (isUnderlined()) {
            sb.append("<u>");
        }
        sb.append(text);
        if (isUnderlined()) {
            sb.append("</u>");
        }
        if (isStrikethrough()) {
            sb.append("</strike>");
        }
        if (isItalicized()) {
            sb.append("</i>");
        }
        if (isCode()) {
            sb.append("</code>");
        }
        if (isBold()) {
            sb.append("</b>");
        }
        if (hexcolor != null) {
            sb.append("</font>");
        }
        return sb.toString();
    }
}
