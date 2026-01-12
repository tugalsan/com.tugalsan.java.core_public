package com.tugalsan.java.core.gui.gl.client.three4g.texture;

import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.loaders.OnErrorCallback;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.loaders.OnProgressCallback;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.textures.Texture;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_GLTexture2DUrl extends TGC_GLTexture2DAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_GLTexture2DUrl.class);

    public TGC_GLTexture2DUrl(TGC_GLProgramAbstract program, CharSequence url) {
        super(program);
        this.url = url.toString();
    }
    public String url;

    @Override
    public void lazyLoad() {
        if (status != TGC_GLLoadable.STATUS_INIT()) {
            return;
        }

        status = TGC_GLLoadable.STATUS_LOADING();
        OnLoadCallback olc = o -> {
            if (o instanceof Texture) {
                texture = (Texture) o;
                status = TGC_GLLoadable.STATUS_LOADED();
                d.ci("lazyLoad", "onLoad", "url", url);
                return;
            }
            errorMessage = "o is not instanceof Texture for url: [" + url + "]";
            status = TGC_GLLoadable.STATUS_ERROR();
        };
        OnProgressCallback opc = ope -> {
            if (ope.lengthComputable) {
                progress = 100 * ope.loaded / ope.total;
            }
        };
        OnErrorCallback oec = e -> {
            errorMessage = e.toString();
            status = TGC_GLLoadable.STATUS_ERROR();
        };
        var textureLoader = new TextureLoader();
        texture = textureLoader.load(url, olc, opc, oec);
    }
}
