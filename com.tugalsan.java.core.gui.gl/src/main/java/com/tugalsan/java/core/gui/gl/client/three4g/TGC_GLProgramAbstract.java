package com.tugalsan.java.core.gui.gl.client.three4g;

import com.shc.webgl4j.client.*;
import org.treblereel.gwt.three4g.loaders.managers.*;
import com.tugalsan.java.core.shape.client.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;
import com.tugalsan.java.core.gui.gl.client.three4g.color.*;
import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.light.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.gui.gl.client.three4g.font.*;
import com.tugalsan.java.core.gui.gl.client.three4g.model.*;

abstract public class TGC_GLProgramAbstract {//MINIMAL PROGRAM 

    final private static TGC_Log d = TGC_Log.of(true, TGC_GLProgramAbstract.class);

    public TGC_GLMath math;
    public TGC_GLColor color;
    public TGC_GLCanvas canvas;
    public TGC_GLCamera camera;
    public TGC_GLScene sceneWorld;
    public TGC_GLRenderer renderer;
    public TGC_GLLight lights;
    public TGC_GLStats stats;
    public TGC_GLWindow window;
    public TGC_GLFonts fonts;
    public LoadingManager loadingManager;

    public TGC_GLProgramAbstract(CharSequence optional_dracoLoaderDecoderPath) {
        TGC_GLModel.DracoLoaderDecoderPath = optional_dracoLoaderDecoderPath == null ? null : optional_dracoLoaderDecoderPath.toString();
        d.ci("TGC_GLModel.DracoLoaderDecoderPath", TGC_GLModel.DracoLoaderDecoderPath);
        math = new TGC_GLMath(this);
        window = new TGC_GLWindow(this);
        fonts = new TGC_GLFonts(this);
        stats = new TGC_GLStats(this, new TGS_ShapeLocation(0, 0), false);
        color = new TGC_GLColor(this);
        canvas = new TGC_GLCanvas(this);
        camera = new TGC_GLCamera(this, 70, 0.1f, 10000);
        sceneWorld = new TGC_GLScene(this, "sceneWorld");
        renderer = new TGC_GLRenderer(this, true, true, 1.2f, true, false);
        lights = new TGC_GLLight(this);
        loadingManager = new LoadingManager(() -> TGC_GLProgramAbstract.this.onLoad());
    }

    public void onLoad() {
    }

    public void lazyUpdate() {
        if (stats != null) {
            stats.rePaint();
        }
        if (background != null) {
            if (background.textureCube != sceneWorld.scene.background) {
                if (background.status == TGC_GLLoadable.STATUS_INIT()) {
                    background.lazyLoad();
                }
                if (background.status == TGC_GLLoadable.STATUS_LOADED()) {
                    sceneWorld.scene.background = background.textureCube;
                    d.ci("lazyUpdate", "background.toString()" + background.toString());
                }
            }
        }
        fonts.lazyLoad();
        sceneWorld.lazyLoad();
    }
    public TGC_GLTextureCube background = null;

    abstract public void animate(float clockDelta, double timestampCurrent, int maxFPS, double minMS);

    abstract public void post_animate(float clockDelta, double timestampCurrent, int maxFPS, double minMS);

    public static boolean isWEBGL1Supported() {
        return WebGL10.isSupported();
    }

    public static boolean isWEBGL2Supported() {
        return WebGL20.isSupported();
    }

    public void onWindowResize() {
        d.ci("onWindowResize", "print w,h", window.getWidth(), window.getHeight());
    }
}
