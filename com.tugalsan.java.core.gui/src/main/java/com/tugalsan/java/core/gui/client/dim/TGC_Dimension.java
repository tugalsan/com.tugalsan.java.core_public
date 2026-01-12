package com.tugalsan.java.core.gui.client.dim;

import com.tugalsan.java.core.gui.client.browser.TGC_BrowserDimensionUtils;

public class TGC_Dimension {

    final public static TGC_Dimension FULLSCREEN = new TGC_Dimension(null, null, true);

    private final Integer width;
    private final Integer height;
    private final boolean limitToWindowDimensions;

    public TGC_Dimension(Integer width, Integer height, boolean limitToWindowDimensions) {
        this.width = width;
        this.height = height;
        this.limitToWindowDimensions = limitToWindowDimensions;
    }

    public int getWidth() {
        var ww = TGC_BrowserDimensionUtils.width();
        if (width == null) {
            return ww;
        }
        return (limitToWindowDimensions && width > ww) ? ww : width;
    }

    public int getHeight() {
        var wh = TGC_BrowserDimensionUtils.height();
        if (height == null) {
            return wh;
        }
        return (limitToWindowDimensions && height > wh) ? wh : height;
    }

    public static boolean isFullScreen(TGC_Dimension dim) {
        var fullScreen = dim == null;
        if (dim != null) {
            var wFull = TGC_BrowserDimensionUtils.width() <= dim.getWidth();
            var hFull = TGC_BrowserDimensionUtils.height() <= dim.getHeight();
            fullScreen = wFull && hFull;
        }
        return fullScreen;
    }
}
