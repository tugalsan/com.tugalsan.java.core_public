package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;

public class TGC_3JSPrimativeTube extends TGC_3JSPrimativeAbstract {

    public static int DEF_tubularSegments() {
        return 64;
    }

    public static int DEF_radialSegments() {
        return 8;
    }

//    public TGC_3JSPrimativeTube(TGC_3JSProgramAbstract program, String name,
//            Curve path, float radius, boolean closed,
//            TGC_3JSTexture texture, TGC_3JSTextureCube environment) {
//        this(program, name, path, radius, DEF_tubularSegments, DEF_radialSegments, closed, texture, environment);
//    }
    public TGC_3JSPrimativeTube(TGC_GLProgramAbstract program, CharSequence name,
            //            Curve path, float radius, int tubularSegments, int radialSegments, boolean closed,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
//        this.path = path;
//        this.tubularSegments = tubularSegments;
//        this.radialSegments = radialSegments;
//        this.radius = radius;
//        this.closed = closed;
    }
//    public Curve path;
//    public int tubularSegments, radialSegments;
//    public float radius;
//    public boolean closed;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
//        TubeBufferGeometry geometry = new TubeBufferGeometry(path, tubularSegments, radius, radialSegments, closed);
        var material = new MeshBasicMaterial();
        if (texture != null) {
            material.map = texture.texture;
        }
        if (environment != null) {
            material.envMap = environment.textureCube;
        }
//        mesh = new Mesh(geometry, material);
        return this;
    }

}
