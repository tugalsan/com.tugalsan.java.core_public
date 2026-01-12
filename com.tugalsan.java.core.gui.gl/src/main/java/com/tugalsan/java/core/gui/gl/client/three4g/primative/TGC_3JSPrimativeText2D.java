package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import com.tugalsan.java.core.gui.gl.client.three4g.font.TGC_GLFont;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.extras.core.Shape;
import org.treblereel.gwt.three4g.geometries.ShapeBufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_3JSPrimativeText2D extends TGC_3JSPrimativeAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_3JSPrimativeText2D.class);
    public static int DEF_SEGMENTS() {
        return 12;
    }

    public TGC_3JSPrimativeText2D(TGC_GLProgramAbstract program, CharSequence name, int fontIndex, int size, boolean centerPivot,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, fontIndex, size, centerPivot, DEF_SEGMENTS(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeText2D(TGC_GLProgramAbstract program, CharSequence name, int fontIndex, int size, boolean centerPivot, int segments,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.size = size;
        this.fontIndex = fontIndex;
        if (fontIndex > -1 && fontIndex < program.fonts.size()) {
            this.font = program.fonts.get(fontIndex);
        } else {
            font = null;
            d.ci("constructor", "fontIndex", fontIndex);
        }
        this.segments = segments;
        this.centerPivot = centerPivot;
        this.doubleSided = doubleSided;
    }
    public TGC_GLFont font;
    public float size;
    public Shape[] shapes = null;
    public int fontIndex, segments;
    public boolean centerPivot, doubleSided;

    @Override
    protected boolean additionalLoadValidation() {
        return font != null && font.status == TGC_GLLoadable.STATUS_LOADED();
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        shapes = font.font.generateShapes(name, size);
        var geometry = new ShapeBufferGeometry(shapes, segments);
        var material = new MeshPhongMaterial();
        if (texture != null) {
            material.map = texture.texture;
        }
        if (environment != null) {
            material.envMap = environment.textureCube;
        }
        if (doubleSided) {
            material.side = THREE.DoubleSide;
        }
        mesh = new Mesh(geometry, material);

        if (centerPivot) {
            var center = calculatePositionCenter();
            mesh.geometry.translate(center.x, center.y, center.z);
        }
        return this;
    }
}
