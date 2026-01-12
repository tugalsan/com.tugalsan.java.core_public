package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeRing extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS_THETA() {
        return 3;
    }

    public static int MIN_SEGMENTS_PHI() {
        return 1;
    }

    public static float MIN_THETA_OFFSET() {
        return 0f;
    }

    public static float MAX_THETA_LENGTH() {
        return 360f;
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius, boolean doubleSided) {
        this(program, name, innerRadius, outerRadius, MIN_SEGMENTS_THETA(), MIN_SEGMENTS_PHI(), doubleSided);
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, innerRadius, outerRadius, MIN_SEGMENTS_THETA(), MIN_SEGMENTS_PHI(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius,
            int thetaSegments, int phiSegments, boolean doubleSided) {
        this(program, name, innerRadius, outerRadius, thetaSegments, phiSegments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), doubleSided);
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius,
            int thetaSegments, int phiSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        this(program, name, innerRadius, outerRadius, thetaSegments, phiSegments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), texture, environment, doubleSided);
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius,
            int thetaSegments, int phiSegments,
            float thetaOffset, float thetaLength, boolean doubleSided) {
        this(program, name, innerRadius, outerRadius, thetaSegments, phiSegments, thetaOffset, thetaLength, null, null, doubleSided);
    }

    public TGC_3JSPrimativeRing(TGC_GLProgramAbstract program, CharSequence name, float innerRadius, float outerRadius,
            int thetaSegments, int phiSegments,
            float thetaOffset, float thetaLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean doubleSided) {
        super(program, name, texture, environment);
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.thetaSegments = thetaSegments < MIN_SEGMENTS_THETA() ? MIN_SEGMENTS_THETA() : thetaSegments;
        this.phiSegments = phiSegments < MIN_SEGMENTS_PHI() ? MIN_SEGMENTS_PHI() : phiSegments;
        this.thetaOffset = thetaOffset < MIN_THETA_OFFSET() ? MIN_THETA_OFFSET() : thetaOffset;
        this.thetaLength = thetaLength > MAX_THETA_LENGTH() ? MAX_THETA_LENGTH() : thetaLength;
        this.doubleSided = doubleSided;
    }
    public float innerRadius, outerRadius, thetaOffset, thetaLength;
    public int thetaSegments, phiSegments;
    public boolean doubleSided;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeRing.class.getSimpleName(), "-> name:", name,
                ", innerRadius:", String.valueOf(innerRadius),
                ", outerRadius:", String.valueOf(outerRadius),
                ", thetaSegments:", String.valueOf(thetaSegments),
                ", phiSegments:", String.valueOf(phiSegments),
                ", thetaOffset:", String.valueOf(thetaOffset),
                ", thetaLength:", String.valueOf(thetaLength),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new RingBufferGeometry(innerRadius, outerRadius, thetaSegments, phiSegments,
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
