package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeTorusKnot extends TGC_3JSPrimativeAbstract {

    public static int MIN_WINDS_AXIS() {
        return 2;
    }

    public static int MIN_WINDS_CIRCLE() {
        return 3;
    }

    public static int MIN_SEGMENTS_TUBULAR() {
        return 2;
    }

    public static int MIN_SEGMENTS_RADIAL() {
        return MIN_SEGMENTS_TUBULAR() * MIN_SEGMENTS_TUBULAR();
    }

    public float radiusTorus, radiusTube;
    public int radialSegments, tubularSegments;
    public int circleWind, axisWind;

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube) {
        this(program, name, radiusTorus, radiusTube, null, null);
    }

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radiusTorus, radiusTube, MIN_SEGMENTS_RADIAL(), MIN_SEGMENTS_TUBULAR(), texture, environment);
    }

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, null, null);
    }

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, MIN_WINDS_AXIS(), MIN_WINDS_CIRCLE(), texture, environment);
    }

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, int axisWind, int circleWind) {
        this(program, name, radiusTorus, radiusTube, radialSegments, tubularSegments, axisWind, circleWind, null, null);
    }

    public TGC_3JSPrimativeTorusKnot(TGC_GLProgramAbstract program, CharSequence name, float radiusTorus, float radiusTube,
            int radialSegments, int tubularSegments, int axisWind, int circleWind,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.radiusTorus = radiusTorus;
        this.radiusTube = radiusTube;
        this.radialSegments = radialSegments < MIN_SEGMENTS_RADIAL() ? MIN_SEGMENTS_RADIAL() : radialSegments;
        this.tubularSegments = tubularSegments < MIN_SEGMENTS_TUBULAR() ? MIN_SEGMENTS_TUBULAR() : tubularSegments;
        this.axisWind = axisWind < MIN_WINDS_AXIS() ? MIN_WINDS_AXIS() : axisWind;
        this.circleWind = circleWind < MIN_WINDS_CIRCLE() ? MIN_WINDS_CIRCLE() : circleWind;
    }

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeTorusKnot.class.getSimpleName(), "-> name:", name,
                ", radiusTorus:", String.valueOf(radiusTorus),
                ", radiusTube:", String.valueOf(radiusTube),
                ", radialSegments:", String.valueOf(radialSegments),
                ", tubularSegments:", String.valueOf(tubularSegments),
                ", axisWind:", String.valueOf(axisWind),
                ", circleWind:", String.valueOf(circleWind),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new TorusKnotBufferGeometry(radiusTorus, radiusTube, tubularSegments, radialSegments,
                axisWind, circleWind
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
