package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeSphereIcosahedron extends TGC_3JSPrimativeAbstract {

    public static int MIN_DETAIL() {
        return 0;
    }

    public TGC_3JSPrimativeSphereIcosahedron(TGC_GLProgramAbstract program, CharSequence name, float radius) {
        this(program, name, radius, null, null);
    }

    public TGC_3JSPrimativeSphereIcosahedron(TGC_GLProgramAbstract program, CharSequence name, float radius, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radius, MIN_DETAIL(), texture, environment);
    }

    public TGC_3JSPrimativeSphereIcosahedron(TGC_GLProgramAbstract program, CharSequence name, float radius, int detail) {
        this(program, name, radius, detail, null, null);
    }

    public TGC_3JSPrimativeSphereIcosahedron(TGC_GLProgramAbstract program, CharSequence name, float radius, int detail, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.radius = radius;
        this.detail = detail < MIN_DETAIL() ? MIN_DETAIL() : detail;
    }
    public float radius;
    public int detail;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeSphereIcosahedron.class.getSimpleName(), "-> name:", name,
                ", radius:", String.valueOf(radius),
                ", detail:", String.valueOf(detail));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new IcosahedronBufferGeometry(radius, detail);
        var material = new MeshBasicMaterial();
        if (texture != null) {
            material.map = texture.texture;
        }
        if (environment != null) {
            material.envMap = environment.textureCube;
        }
        mesh = new Mesh(geometry, material);
        return this;
    }
}
