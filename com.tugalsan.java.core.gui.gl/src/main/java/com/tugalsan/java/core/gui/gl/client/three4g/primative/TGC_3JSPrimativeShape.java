package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.extras.core.Shape;
import org.treblereel.gwt.three4g.geometries.ShapeBufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativeShape extends TGC_3JSPrimativeAbstract {

    public static int DEF_SEGMENTS() {
        return 12;
    }

    public TGC_3JSPrimativeShape(TGC_GLProgramAbstract program, CharSequence name, Shape[] shapes,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, shapes, DEF_SEGMENTS(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeShape(TGC_GLProgramAbstract program, CharSequence name, Shape[] shapes, int segments,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.shapes = shapes;
        this.segments = segments;
        this.doubleSided = doubleSided;
    }
    public Shape[] shapes;
    public int segments;
    public boolean doubleSided;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geo = new ShapeBufferGeometry(shapes, segments);
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

    public static Shape createShapeHearth(float offsetX, float offsetY) {
        var s = new Shape();
        s.moveTo(offsetX + 5, offsetY + 5);
        s.bezierCurveTo(offsetX + 5, offsetY + 5, offsetX + 4, offsetY, offsetX, offsetY);
        s.bezierCurveTo(offsetX - 6, offsetY, offsetX - 6, offsetY + 7, offsetX - 6, offsetY + 7);
        s.bezierCurveTo(offsetX - 6, offsetY + 11, offsetX - 3, offsetY + 15.4f, offsetX + 5, offsetY + 19);
        s.bezierCurveTo(offsetX + 12, offsetY + 15.4f, offsetX + 16, offsetY + 11, offsetX + 16, offsetY + 7);
        s.bezierCurveTo(offsetX + 16, offsetY + 7, offsetX + 16, offsetY, offsetX + 10, offsetY);
        s.bezierCurveTo(offsetX + 7, offsetY, offsetX + 5, offsetY + 5, offsetX + 5, offsetY + 5);
        return s;
    }

    public static Shape createShapePlane(float width, float length) {
        var s = new Shape();
        s.moveTo(0, 0);
        s.lineTo(0, width);
        s.lineTo(length, width);
        s.lineTo(length, 0);
        s.lineTo(0, 0);
        return s;
    }
}
