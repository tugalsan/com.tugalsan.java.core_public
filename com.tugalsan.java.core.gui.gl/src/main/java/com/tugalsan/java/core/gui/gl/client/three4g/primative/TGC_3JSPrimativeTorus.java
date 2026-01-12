package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeTorus extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS_RADIAL() {
        return 3;
    }

    public static int MIN_SEGMENTS_TUBULAR() {
        return 5;
    }

    public static float MAX_ARC_LENGTH() {
        return 360f;
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube) {
        this(program, name, radiusTorus, radiusTube, null, null);
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radiusTorus, radiusTube, MIN_SEGMENTS_RADIAL(), MIN_SEGMENTS_TUBULAR(), texture, environment);
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, null, null);
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, MAX_ARC_LENGTH(), texture, environment);
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, float arcLength) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, arcLength, null, null);
    }

    public TGC_3JSPrimativeTorus(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, float arcLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.radiusTorus = radiusTorus;
        this.radiusTube = radiusTube;
        this.radialSegments = radialSegments < MIN_SEGMENTS_RADIAL() ? MIN_SEGMENTS_RADIAL() : radialSegments;
        this.tubularSegments = tubularSegments < MIN_SEGMENTS_TUBULAR() ? MIN_SEGMENTS_TUBULAR() : tubularSegments;
        this.arcLength = arcLength > MAX_ARC_LENGTH() ? MAX_ARC_LENGTH() : arcLength;
    }
    public float radiusTorus, radiusTube;
    public int radialSegments, tubularSegments;
    public float arcLength;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeTorus.class.getSimpleName(), "-> name:", name,
                ", radiusTorus:", String.valueOf(radiusTorus),
                ", radiusTube:", String.valueOf(radiusTube),
                ", radialSegments:", String.valueOf(radialSegments),
                ", tubularSegments:", String.valueOf(tubularSegments),
                ", arcLength:", String.valueOf(arcLength),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new TorusBufferGeometry(radiusTorus, radiusTube, radialSegments, tubularSegments,
                program.math.getRadians(arcLength, false)
        );
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
