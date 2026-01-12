package com.tugalsan.java.core.profile.client;

import com.tugalsan.java.core.url.client.TGS_Url;
import com.tugalsan.java.core.url.client.parser.TGS_UrlParser;

public class TGS_ProfileServletUtils {

    final public static String SERVLET_NAME = "monitoring";//HARD-CODED IN LIB, THIS CANNOT BE CHANGED!

    public static TGS_UrlParser URL_SERVLET(TGS_Url url) {
        var urlMonitoring = TGS_UrlParser.of(url);
        urlMonitoring.path.fileOrServletName = TGS_ProfileServletUtils.SERVLET_NAME;
        var appName = urlMonitoring.path.paths.get(0);
        urlMonitoring.path.paths.clear();
        urlMonitoring.path.paths.add(appName);
        urlMonitoring.quary.params.clear();
        urlMonitoring.anchor.value = null;
        return urlMonitoring;
    }
}
