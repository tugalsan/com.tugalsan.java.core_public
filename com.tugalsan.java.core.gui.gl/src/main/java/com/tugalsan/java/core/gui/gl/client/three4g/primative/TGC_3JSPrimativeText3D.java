package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import com.tugalsan.java.core.gui.gl.client.three4g.font.TGC_GLFont;
import org.treblereel.gwt.three4g.geometries.TextBufferGeometry;
import org.treblereel.gwt.three4g.geometries.parameters.TextGeometryParameters;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_3JSPrimativeText3D extends TGC_3JSPrimativeAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_3JSPrimativeText3D.class);

    public TGC_3JSPrimativeText3D(TGC_GLProgramAbstract program, CharSequence name, int fontIndex, boolean centerPivot,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, fontIndex, centerPivot, new TextGeometryParameters(), texture, environment);
    }

    public TGC_3JSPrimativeText3D(TGC_GLProgramAbstract program, CharSequence name, int fontIndex, boolean centerPivot, TextGeometryParameters parameters,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.fontIndex = fontIndex;
        if (fontIndex > -1 && fontIndex < program.fonts.size()) {
            this.font = program.fonts.get(fontIndex);
        } else {
            font = null;
            d.ce("constructor", "ERROR: fontIndex: " + fontIndex);
        }
        this.parameters = parameters;
        this.centerPivot = centerPivot;
    }
    public TextGeometryParameters parameters;
    public int fontIndex;
    public TGC_GLFont font;
    public boolean centerPivot;

    @Override
    protected boolean additionalLoadValidation() {
        if (font != null && font.status == TGC_GLLoadable.STATUS_LOADED()) {
            parameters.font = font.font;
            return true;
        }
        return false;
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geo = new TextBufferGeometry(name, parameters);
        var material = new MeshPhongMaterial();
        if (texture != null) {
            material.map = texture.texture;
        }
        if (environment != null) {
            material.envMap = environment.textureCube;
        }
        mesh = new Mesh(geo, material);

        if (centerPivot) {
            var center = calculatePositionCenter();
            mesh.geometry.translate(center.x, center.y, center.z);
        }
        return this;
    }
}
