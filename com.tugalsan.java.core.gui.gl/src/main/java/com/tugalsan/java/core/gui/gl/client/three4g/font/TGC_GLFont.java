package com.tugalsan.java.core.gui.gl.client.three4g.font;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import org.treblereel.gwt.three4g.extras.core.Font;
import org.treblereel.gwt.three4g.loaders.FontLoader;
import org.treblereel.gwt.three4g.loaders.OnErrorCallback;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.loaders.OnProgressCallback;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_GLFont extends TGC_GLLoadable {

    final private static TGC_Log d = TGC_Log.of(true, TGC_GLFont.class);

    public TGC_GLFont(TGC_GLProgramAbstract program, CharSequence url) {
        super(program);
        name = this.url = url.toString();
        var li = name.lastIndexOf("/");
        if (li != -1 && name.length() > li) {
            name = name.substring(li + 1);
        }
        li = name.lastIndexOf("/");
        if (li != -1 && name.length() > li) {
            name = name.substring(0, li);
        }
    }
    public String url;
    public String name;
    public Font font = null;

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_LOADED()) {
            return;
        }

        if (status != TGC_GLLoadable.STATUS_INIT()) {
            return;
        }

        OnLoadCallback olc = (Object o) -> {
            d.ci("lazyLoad", "onLoad", url, url);
            if (o instanceof Font) {
                font = (Font) o;
                d.ci("lazyLoad", "onLoad", "is instanceof Font", url);
                status = TGC_GLLoadable.STATUS_LOADED();
            } else {
                errorMessage = "ERROR: " + TGC_GLFont.class.getSimpleName() + ".o is not instanceof Font";
                d.ce("lazyLoad", "onLoad", errorMessage, url);
                status = TGC_GLLoadable.STATUS_ERROR();
            }
        };
        OnProgressCallback opc = ope -> {
            if (ope.lengthComputable) {
                progress = 100 * ope.loaded / ope.total;
                d.ci("onProgress", "url", url, ope.loaded, ope.total);
            }
        };
        OnErrorCallback oec = e -> {
            errorMessage = e.toString();
            d.ce("lazyLoad", "onError", errorMessage, url);
            status = TGC_GLLoadable.STATUS_ERROR();
        };
        var loader = new FontLoader(program.loadingManager);
        d.ci("lazyLoad", "LOADING", "url", url);
        loader.crossOrigin = null;//WARNING: When URL not reachable, the error shows as cross origin
        loader.load(url, olc, opc, oec);
        status = TGC_GLLoadable.STATUS_LOADING();
    }
}
