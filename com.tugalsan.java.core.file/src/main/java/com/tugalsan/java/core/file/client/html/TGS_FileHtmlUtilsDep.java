package com.tugalsan.java.core.file.client.html;

public class TGS_FileHtmlUtilsDep {

    private TGS_FileHtmlUtilsDep() {

    }

    @Deprecated //IS IT REALLY WORKING?
    public static String toReadableText(CharSequence html) {
        if (html == null) {
            return null;
        }
        var result = html.toString();
        result = result.replace("&amp;", "&");
        result = result.replace("&lt;", "<");
        result = result.replace("&gt;", ">");
        result = result.replace("&quot;", "\"");
        result = result.replace("&apos;", "'");
        int codePoint, semi, idx = 0;
        String entity, ch;
        while ((idx = result.indexOf("&#", idx)) != -1) {
            semi = result.indexOf(';', idx);
            if (semi > idx) {
                entity = result.substring(idx + 2, semi);
                try {
                    if (entity.startsWith("x") || entity.startsWith("X")) {
                        codePoint = Integer.parseInt(entity.substring(1), 16);
                    } else {
                        codePoint = Integer.parseInt(entity);
                    }
                    ch = new String(Character.toChars(codePoint));
                    result = result.substring(0, idx) + ch + result.substring(semi + 1);
                } catch (NumberFormatException e) {
                    // leave it unchanged
                }
            }
            idx++;
        }
        return result;
    }

}
