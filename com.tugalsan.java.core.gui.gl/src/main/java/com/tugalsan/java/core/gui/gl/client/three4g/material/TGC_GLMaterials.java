package com.tugalsan.java.core.gui.gl.client.three4g.material;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.materials.LineBasicMaterial;
import org.treblereel.gwt.three4g.math.Color;

public class TGC_GLMaterials {

    final private TGC_GLProgramAbstract program;

    public TGC_GLMaterials(TGC_GLProgramAbstract program) {
        this.program = program;
    }

    public LineBasicMaterial getLineBasicMaterial(Color color, boolean transparent, float opacy) {
        var material = new LineBasicMaterial();
        material.color = color;
        material.transparent = transparent;
        material.opacity = opacy;
        return material;
    }

    public LineBasicMaterial getLineBasicMaterial(Color color) {
        return getLineBasicMaterial(program.color.getWhite_0xFFFFFF(), false, 1f);
    }

    public LineBasicMaterial getLineBasicMaterial_WireFrame() {
        return getLineBasicMaterial(program.color.getWhite_0xFFFFFF(), true, 0.5f);
    }
}
