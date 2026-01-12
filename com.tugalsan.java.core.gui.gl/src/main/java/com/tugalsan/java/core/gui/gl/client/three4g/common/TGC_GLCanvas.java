package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.shape.client.TGS_ShapeRectangle;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;

public class TGC_GLCanvas {

    final public TGC_GLProgramAbstract program;
    final public HTMLDivElement containerDiv;

    public float calculateAspect() {
        var p = currentRect;
        return 1f * p.width / p.height;
    }

    public TGC_GLCanvas(TGC_GLProgramAbstract program) {
        this(program, TGS_ShapeRectangle.of(0, 0, TGC_GLStats.dimension.width, TGC_GLStats.dimension.height));
    }

    public TGC_GLCanvas(TGC_GLProgramAbstract program, TGS_ShapeRectangle<Integer> locDim) {
        this.program = program;
        containerDiv = (HTMLDivElement) DomGlobal.document.createElement("div");
        containerDiv.id = TGC_GLCanvas.class.getSimpleName();
        setRect(locDim);
        TGC_GLDomUtils.addToBody(containerDiv);
    }

    final public void setRect(TGS_ShapeRectangle<Integer> locDim) {
        currentRect = locDim;
        TGC_GLDomUtils.setLocationAndDimension(containerDiv, locDim);
        if (program.renderer != null) {
            program.renderer.updateSize();
        }
    }

    public TGS_ShapeRectangle<Integer> getRect() {
        return currentRect;
    }
    private TGS_ShapeRectangle<Integer> currentRect;

    public void setVisible(boolean visible) {
        TGC_GLDomUtils.setVisible(containerDiv, visible);
    }
}
