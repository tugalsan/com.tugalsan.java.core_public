package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;

public class TGC_GLRenderer {

    final public TGC_GLProgramAbstract program;
    final public WebGLRendererParameters rendererParameters;
    final public WebGLRenderer renderer;
    final public boolean antialias, gammaInput, shadow, alpha;
    final public float gammaFactor;
    final public TGC_GLRenderAnimator animator;

    public TGC_GLRenderer(TGC_GLProgramAbstract program, boolean antialias, boolean gammaInput, float gammaFactor,
            boolean shadow, boolean alpha) {
        this.program = program;
        this.antialias = antialias;
        this.gammaInput = gammaInput;
        this.gammaFactor = gammaFactor;
        this.shadow = shadow;
        this.alpha = alpha;
        rendererParameters = new WebGLRendererParameters();
        rendererParameters.antialias = antialias;
        rendererParameters.alpha = alpha;//MAKES BG TRANSPARENT BUT NOT WORKING
        renderer = new WebGLRenderer(rendererParameters);
        renderer.setPixelRatio(program.window.getPixelRatio());
        renderer.gammaInput = gammaInput;
        renderer.gammaFactor = gammaFactor;
        renderer.shadowMap.enabled = shadow;
        renderer.autoClear = true;//clear color buffer
        updateSize();
        program.canvas.containerDiv.appendChild(renderer.domElement);
        this.animator = new TGC_GLRenderAnimator(program);
        renderer.autoClear = false;//for sprites
    }

    final public void updateSize() {
        var p = program.canvas.getRect();
        renderer.setSize(p.width, p.height);
    }
}
