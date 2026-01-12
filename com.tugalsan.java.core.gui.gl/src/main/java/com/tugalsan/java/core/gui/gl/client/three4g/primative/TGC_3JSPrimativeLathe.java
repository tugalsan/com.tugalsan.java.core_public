package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import java.lang.Math;
import org.treblereel.gwt.three4g.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.math.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeLathe extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS() {
        return 3;
    }

    public static float MIN_PHI_OFFSET() {
        return 0f;
    }

    public static float MAX_PHI_LENGTH() {
        return 360f;
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name, Vector2[] points) {
        this(program, name, points, MIN_SEGMENTS());
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name, Vector2[] points, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, points, MIN_SEGMENTS(), texture, environment);
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name, Vector2[] points, int segments) {
        this(program, name, points, segments, MIN_PHI_OFFSET(), MAX_PHI_LENGTH());
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name, Vector2[] points, int segments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, points, segments, MIN_PHI_OFFSET(), MAX_PHI_LENGTH(), texture, environment);
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name,
            Vector2[] points, int segments, float phiStart, float phiLength) {
        this(program, name, points, segments, phiStart, phiLength, null, null);
    }

    public TGC_3JSPrimativeLathe(TGC_GLProgramAbstract program, CharSequence name,
            Vector2[] points, int segments, float phiStart, float phiLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.points = points;
        this.segments = segments < MIN_SEGMENTS() ? MIN_SEGMENTS() : segments;
        this.phiStart = phiStart < MIN_PHI_OFFSET() ? MIN_PHI_OFFSET() : phiStart;
        this.phiLength = phiLength > MAX_PHI_LENGTH() ? MAX_PHI_LENGTH() : phiLength;
    }
    public Vector2[] points;
    public int segments;
    public float phiStart, phiLength;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeLathe.class.getSimpleName(), "-> name:", name,
                ", points:", String.valueOf(points),
                ", segments:", String.valueOf(segments),
                ", phiStart:", String.valueOf(phiStart),
                ", phiLength:", String.valueOf(phiLength),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new LatheBufferGeometry(points, segments,
                program.math.getRadians(phiStart), program.math.getRadians(phiLength, false));
        var material = new MeshBasicMaterial();
        material.side = THREE.DoubleSide;
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
