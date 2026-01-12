package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.shape.client.TGS_ShapeDimension;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class TGC_GLWindow {

    public TGC_GLWindow(TGC_GLProgramAbstract program) {
        this.program = program;
        window = (TGC_3JSWindowNative) DomGlobal.window;
        window.addEventListener("resize", el -> program.onWindowResize());

    }
    final public TGC_GLProgramAbstract program;
    final public TGC_3JSWindowNative window;

    public TGS_ShapeLocation<Integer> calculateCenter(TGS_ShapeDimension<Integer> dimension) {
        TGS_ShapeLocation<Integer> center = new TGS_ShapeLocation(0, 0);
        center.x = Math.round((getWidth() - dimension.width) / 2);
        center.y = Math.round((getHeight() - dimension.height) / 2);
        return center;
    }

    public float getWidth() {
        return (float) window.innerWidth;
    }

    public float getPixelRatio() {
        return (float) window.devicePixelRatio;
    }

    public float getHeight() {
        return (float) window.innerHeight;
    }

    /**
     * @author Dmitrii Tikhomirov <chani@me.com>
     * Created by treblereel on 7/12/18.
     */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Window")
    private static class TGC_3JSWindowNative extends elemental2.dom.Window {//EXTEND WINDOW OBJECT WITH devicePixelRatio

        public double devicePixelRatio;

        //DO NOT TRY TO ADD TOO MUCH FUNCTIONS, IT IS A NATIVE CLASS!
    }
}
