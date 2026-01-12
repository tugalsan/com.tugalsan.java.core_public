package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeSphereStandard extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS_WIDTH() {
        return 3;
    }

    public static int DEF_SEGMENTS_WIDTH() {
        return 8;
    }

    public static int MIN_SEGMENTS_HEIGHT() {
        return 4;
    }

    public static int DEF_SEGMENTS_HEIGHT() {
        return 6;
    }

    public static float MIN_PHI_OFFSET() {
        return 0f;
    }

    public static float MAX_PHI_LENGTH() {
        return 360f;
    }

    public static float MIN_THETA_OFFSET() {
        return 0f;
    }

    public static float MAX_THETA_LENGTH() {
        return 180f;
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name, float radius) {
        this(program, name, radius, MIN_SEGMENTS_WIDTH(), MIN_SEGMENTS_HEIGHT());
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name, float radius, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radius, MIN_SEGMENTS_WIDTH(), MIN_SEGMENTS_HEIGHT(), texture, environment);
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name, float radius, int widthSegments, int heightSegments) {
        this(program, name, radius, widthSegments, heightSegments, MIN_PHI_OFFSET(), MAX_PHI_LENGTH());
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name, float radius, int widthSegments, int heightSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radius, widthSegments, heightSegments, MIN_PHI_OFFSET(), MAX_PHI_LENGTH(), texture, environment);
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int widthSegments, int heightSegments,
            float phiOffset, float phiLength) {
        this(program, name, radius, widthSegments, heightSegments, phiOffset, phiLength, MIN_THETA_OFFSET(), MAX_THETA_LENGTH());
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int widthSegments, int heightSegments,
            float phiOffset, float phiLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, radius, widthSegments, heightSegments, phiOffset, phiLength, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), texture, environment);
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int widthSegments, int heightSegments,
            float phiOffset, float phiLength,
            float thetaOffset, float thetaLength) {
        this(program, name, radius, widthSegments, heightSegments, phiOffset, phiLength, thetaOffset, thetaLength, null, null);
    }

    public TGC_3JSPrimativeSphereStandard(TGC_GLProgramAbstract program, CharSequence name,
            float radius, int widthSegments, int heightSegments,
            float phiOffset, float phiLength,
            float thetaOffset, float thetaLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.radius = radius;
        this.widthSegments = widthSegments < MIN_SEGMENTS_WIDTH() ? MIN_SEGMENTS_WIDTH() : widthSegments;
        this.heightSegments = heightSegments < MIN_SEGMENTS_HEIGHT() ? MIN_SEGMENTS_HEIGHT() : heightSegments;
        this.phiOffset = phiOffset < MIN_PHI_OFFSET() ? MIN_PHI_OFFSET() : phiOffset;
        this.phiLength = phiLength > MAX_PHI_LENGTH() ? MAX_PHI_LENGTH() : phiLength;
        this.thetaOffset = thetaOffset < MIN_THETA_OFFSET() ? MIN_THETA_OFFSET() : thetaOffset;
        this.thetaLength = thetaLength > MAX_THETA_LENGTH() ? MAX_THETA_LENGTH() : thetaLength;
    }
    public float radius;
    public int widthSegments, heightSegments;
    public float phiOffset, phiLength, thetaOffset, thetaLength;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeSphereStandard.class.getSimpleName(), "-> name:", name,
                ", radius:", String.valueOf(radius),
                ", widthSegments:", String.valueOf(widthSegments),
                ", heightSegments:", String.valueOf(heightSegments),
                ", phiOffset:", String.valueOf(phiOffset),
                ", phiLength:", String.valueOf(phiLength),
                ", thetaOffset:", String.valueOf(thetaOffset),
                ", thetaLength:", String.valueOf(thetaLength),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new SphereBufferGeometry(radius, widthSegments, heightSegments,
                program.math.getRadians(phiOffset), program.math.getRadians(phiLength, false),
                program.math.getRadians(thetaOffset), program.math.getRadians(thetaLength));
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
