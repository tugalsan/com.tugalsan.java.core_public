package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.extras.core.Shape;
import org.treblereel.gwt.three4g.geometries.ExtrudeGeometry;
import org.treblereel.gwt.three4g.geometries.parameters.ExtrudeGeometryParameters;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativeShapeExtrude extends TGC_3JSPrimativeAbstract {

    public TGC_3JSPrimativeShapeExtrude(TGC_GLProgramAbstract program, CharSequence name, Shape[] shape,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, shape, new ExtrudeGeometryParameters(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeShapeExtrude(TGC_GLProgramAbstract program, CharSequence name, Shape[] shape, ExtrudeGeometryParameters parameters,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.shape = shape;
        this.parameters = parameters;
        this.doubleSided = doubleSided;
    }
    public Shape[] shape;
    public boolean doubleSided;
    public ExtrudeGeometryParameters parameters;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geo = new ExtrudeGeometry(shape, parameters);
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
        mesh = new Mesh(geo, material);
        return this;
    }
}
