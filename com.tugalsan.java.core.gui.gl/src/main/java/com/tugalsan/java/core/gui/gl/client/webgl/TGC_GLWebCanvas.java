package com.tugalsan.java.core.gui.gl.client.webgl;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.RootPanel;
import com.shc.webgl4j.client.WebGL10;
import com.shc.webgl4j.client.WebGL20;
import com.shc.webgl4j.client.WebGLContext;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In2;
import com.tugalsan.java.core.log.client.TGC_Log;

 
public class TGC_GLWebCanvas {
 
    final private static TGC_Log d = TGC_Log.of(TGC_GLWebCanvas.class);

    final public void setWidget(TGC_GLWeb gl, int xOffSet, int yOffSet, int width, int height, Integer qualityPercent_default100) {
        this.gl = gl;
        canvas.setSize(width + "px", height + "px");
        RootPanel.get().add(canvas, xOffSet, yOffSet);
        this.width = width;
        this.height = height;
        if (canvas == null) {
            d.ce("setWidget", "ERROR.TK_WebGLCanvas: cannot create canvas.");
        } else {
            qualityPercent_default100 = qualityPercent_default100 == null ? 100 : qualityPercent_default100;
            performansWidth = (int) Math.floor(1F * width * qualityPercent_default100 / 100);
            performansHeight = (int) Math.floor(1F * height * qualityPercent_default100 / 100);
            performansHeight = performansHeight == 0 ? 1 : performansHeight;
            canvas.setCoordinateSpaceWidth(performansWidth);
            canvas.setCoordinateSpaceHeight(performansHeight);
        }
    }
    public int width, height, performansWidth, performansHeight;
    public Integer qualityPercent_default100;
    public TGC_GLWeb gl;
    public TGS_FuncMTU_In2<Integer, Boolean> onFocused;

    public TGC_GLWebCanvas(int id, TGC_GLWeb gl, int xOffSet, int yOffSet, int width, int height, Integer qualityPercent_default100, TGC_GLWebRender render, TGS_FuncMTU_In2<Integer, Boolean> onFocused) {
        this.onFocused = onFocused;
        this.canvasId = id;
        canvas = Canvas.createIfSupported();
        this.render = render;
        render.canvas = this;
        render.camera = new TGC_GLWebCamera();
        render.gl = gl;
        if (canvas != null) { 
            isWebGL20 = WebGL20.isSupported();
            attributes = WebGLContext.Attributes.create();
            attributes.setAlpha(true);//default true
            attributes.setDepth(true);//default true    
            attributes.setAntialias(true);//default true
            attributes.setStencil(false);//default false
            attributes.setPremultipliedAlpha(true);//default true
            attributes.setPreserveDrawingBuffer(false);//default false
            context = isWebGL20 ? WebGL10.createContext(canvas, attributes) : WebGL20.createContext(canvas, attributes);
            canvas.addFocusHandler(e -> onFocused.run(id, true));
            canvas.addBlurHandler(e -> onFocused.run(id, false));
        }
        setWidget(gl, xOffSet, yOffSet, width, height, qualityPercent_default100);
    }
    public TGC_GLWebRender render;
    public Canvas canvas = null;
    public boolean isWebGL20;
    public WebGLContext context;
    public WebGLContext.Attributes attributes;
    public int canvasId;
}
