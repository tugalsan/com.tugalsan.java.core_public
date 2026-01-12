package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import org.treblereel.gwt.three4g.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_3JSPrimativeCircle extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS() {
        return 1;
    }

    public static float MIN_THETA_OFFSET() {
        return 0f;
    }

    public static float MAX_THETA_LENGTH() {
        return 360f;
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name, float radius, boolean doubleSided) {
        this(program, name, radius, MIN_SEGMENTS(), doubleSided);
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name, float radius, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, radius, MIN_SEGMENTS(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name, float radius, int segments, boolean doubleSided) {
        this(program, name, radius, segments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), doubleSided);
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name, float radius, int segments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, radius, segments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int segments, float thetaOffset, float thetaLength, boolean doubleSided) {
        this(program, name, radius, segments, thetaOffset, thetaLength, null, null, doubleSided);
    }

    public TGC_3JSPrimativeCircle(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int segments, float thetaOffset, float thetaLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.radius = radius;
        this.segments = segments < MIN_SEGMENTS() ? MIN_SEGMENTS() : segments;
        this.thetaOffset = thetaOffset < MIN_THETA_OFFSET() ? MIN_THETA_OFFSET() : thetaOffset;
        this.thetaLength = thetaLength > MAX_THETA_LENGTH() ? MAX_THETA_LENGTH() : thetaLength;
        this.doubleSided = doubleSided;
    }
    public float radius;
    public int segments;
    public float thetaOffset, thetaLength;
    public boolean doubleSided;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeCircle.class.getSimpleName(), "-> name:", name,
                ", radius:", String.valueOf(radius),
                ", segments:", String.valueOf(segments),
                ", thetaOffset:", String.valueOf(thetaOffset),
                ", thetaLength:", String.valueOf(thetaLength),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new CircleBufferGeometry(radius, segments,
                program.math.getRadians(thetaOffset), program.math.getRadians(thetaLength, false));
        var material = new MeshBasicMaterial();
        if (doubleSided) {
            material.side = THREE.DoubleSide;
        }
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
