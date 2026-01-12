package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativePlane extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS() {
        return 1;
    }

    public TGC_3JSPrimativePlane(TGC_GLProgramAbstract program, CharSequence name, float width, float height, TGC_GLTexture2DAbstract texture, boolean doubleSided) {
        this(program, name, width, height, texture, null, doubleSided);
    }

    public TGC_3JSPrimativePlane(TGC_GLProgramAbstract program, CharSequence name, float width, float height, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, width, height, null, environment, doubleSided);
    }

    public TGC_3JSPrimativePlane(TGC_GLProgramAbstract program, CharSequence name, float width, float height, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, width, height, MIN_SEGMENTS(), MIN_SEGMENTS(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativePlane(TGC_GLProgramAbstract program, CharSequence name, float width, float height, int widthSegments, int heightSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.width = width;
        this.height = height;
        this.widthSegments = widthSegments < MIN_SEGMENTS() ? MIN_SEGMENTS() : widthSegments;
        this.heightSegments = heightSegments < MIN_SEGMENTS() ? MIN_SEGMENTS() : heightSegments;
        this.doubleSided = doubleSided;
    }
    public float width, height;
    public int widthSegments, heightSegments;
    public boolean doubleSided;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geo = new PlaneBufferGeometry(width, height, widthSegments, heightSegments);
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
