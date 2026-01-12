package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import com.tugalsan.java.core.string.client.*;
import org.treblereel.gwt.three4g.*;
import org.treblereel.gwt.three4g.geometries.*;
import org.treblereel.gwt.three4g.materials.*;
import org.treblereel.gwt.three4g.objects.*;

public class TGC_3JSPrimativeCone extends TGC_3JSPrimativeAbstract {

    public static int MIN_SEGMENTS_RADIAL() {
        return 3;
    }

    public static int MIN_SEGMENTS_HEIGHT() {
        return 1;
    }

    public static float MIN_THETA_OFFSET() {
        return 0f;
    }

    public static float MAX_THETA_LENGTH() {
        return 360f;
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            boolean openEnded) {
        this(program, name, radius, height, null, null, openEnded);
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean openEnded) {
        this(program, name, radius, height, MIN_SEGMENTS_RADIAL(), MIN_SEGMENTS_HEIGHT(), texture, environment, openEnded);
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            int radialSegments, int heightSegments, boolean openEnded) {
        this(program, name, radius, height, radialSegments, heightSegments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), openEnded);
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            int radialSegments, int heightSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean openEnded) {
        this(program, name, radius, height, radialSegments, heightSegments, MIN_THETA_OFFSET(), MAX_THETA_LENGTH(), texture, environment, openEnded);
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            int radialSegments, int heightSegments, float thetaOffset, float thetaLength, boolean openEnded) {
        this(program, name, radius, height, radialSegments, heightSegments, thetaOffset, thetaLength, null, null, openEnded);
    }

    public TGC_3JSPrimativeCone(TGC_GLProgramAbstract program, CharSequence name, float radius, float height,
            int radialSegments, int heightSegments, float thetaOffset, float thetaLength,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment, boolean openEnded) {
        super(program, name, texture, environment);
        this.radius = radius;
        this.radialSegments = radialSegments < MIN_SEGMENTS_RADIAL() ? MIN_SEGMENTS_RADIAL() : radialSegments;
        this.heightSegments = heightSegments < MIN_SEGMENTS_HEIGHT() ? MIN_SEGMENTS_HEIGHT() : heightSegments;
        this.thetaOffset = thetaOffset < MIN_THETA_OFFSET() ? MIN_THETA_OFFSET() : thetaOffset;
        this.thetaLength = thetaLength > MAX_THETA_LENGTH() ? MAX_THETA_LENGTH() : thetaLength;
        this.texture = texture;
    }
    public float radius, height;
    public int radialSegments, heightSegments;
    public float thetaOffset, thetaLength;
    public boolean openEnded;

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(TGC_3JSPrimativeCone.class.getSimpleName(), "-> name:", name,
                ", radius:", String.valueOf(radius),
                ", radialSegments:", String.valueOf(radialSegments),
                ", heightSegments:", String.valueOf(heightSegments),
                ", thetaOffset:", String.valueOf(thetaOffset),
                ", thetaLength:", String.valueOf(thetaLength),
                ", openEnded:", String.valueOf(openEnded),
                ", Math.PI:", String.valueOf(Math.PI));
    }

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new ConeBufferGeometry(radius, height,
                radialSegments, heightSegments, openEnded,
                program.math.getRadians(thetaOffset), program.math.getRadians(thetaLength, false));
        var material = new MeshBasicMaterial();
        if (openEnded) {
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
