package com.tugalsan.java.core.file.tug.server;

import module com.tugalsan.java.core.crypto;
import module com.tugalsan.java.core.file.txt;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.url;
import java.nio.file.*;

public class TS_LibRepFileDocData {

    public static boolean DEFAULT_ISTEXT() {
        return true;
    }

    public static String LINEBREAK() {
        return "LINEBREAK";
    }

    public boolean isText_notImage;
    public String data;//text or image_loc
    public int imageWith = 200;
    public int imageHeight = 75;
    public String imageBase64 = null;

    public TS_LibRepFileDocData(String data) {
        this(DEFAULT_ISTEXT(), data);
    }

    public TS_LibRepFileDocData(boolean isText_notImage, String data) {
        this.isText_notImage = isText_notImage;
        this.data = data;

        if (!isText_notImage) {
            var url = TGS_Url.of(data);
            if (TGS_UrlUtils.isValidUrl(url)) {
                imageBase64 = TS_UrlDownloadUtils.toBase64(url);
                if (imageBase64 == null) {
                    imageBase64 = "null";
                }
            } else {
                var path = Path.of(data);
                imageBase64 = TGS_CryptUtils.encrypt64(TS_FileTxtUtils.toString(path));
            }
        }
    }

    @Override
    public String toString() {
        if (isText_notImage) {
            return TGS_StringUtils.cmn().concat(data, "\n");
        } else {
            return TGS_StringUtils.cmn().concat("<img style='display:block; width:", String.valueOf(imageWith), "px;height:", String.valueOf(imageHeight), "px;' src='data:image/jpeg;base64, ", String.valueOf(imageBase64), "' />\n");
        }
    }
}
