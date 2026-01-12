package com.tugalsan.java.core.gui.gl.client.three4g.texture;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;
import org.treblereel.gwt.three4g.loaders.*;
import org.treblereel.gwt.three4g.textures.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.cast.client.*;

public class TGC_GLTextureCube extends TGC_GLLoadable {

    final private static TGC_Log d = TGC_Log.of(TGC_GLTextureCube.class);

    public CubeTexture textureCube = null;
    public String[] urls;

    public TGC_GLTextureCube(TGC_GLProgramAbstract program, CharSequence urlPosX, CharSequence urlNegX, CharSequence urlPosY, CharSequence urlNegY, CharSequence urlPosZ, CharSequence urlNegZ) {
        this(program, new String[]{urlPosX.toString(), urlNegX.toString(), urlPosY.toString(), urlNegY.toString(), urlPosZ.toString(), urlNegZ.toString()});
    }

    public TGC_GLTextureCube(TGC_GLProgramAbstract program, String[] urls) {
        super(program);
        this.urls = urls;
    }

    @Override
    public String toString() {
        return TGS_CastUtils.toString(urls);
    }

    @Override
    public void lazyLoad() {
        if (status != TGC_GLLoadable.STATUS_INIT()) {
            return;
        }

        status = TGC_GLLoadable.STATUS_LOADING();
        OnLoadCallback olc = o -> {
            if (o instanceof CubeTexture) {
                textureCube = (CubeTexture) o;
                status = TGC_GLLoadable.STATUS_LOADED();
                d.ci("lazyLoad", "onLoad", "urls", urls);
                return;
            }
            errorMessage = "o is not instanceof CubeTexture for url: [" + toString() + "]";
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
        var cubeTextureLoader = new CubeTextureLoader();
        cubeTextureLoader.load(urls, olc, opc, oec);
    }
}
