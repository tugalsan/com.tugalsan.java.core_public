package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativeBox extends TGC_3JSPrimativeAbstract {

    public final static int MIN_SEGMENTS() {
        return 1;
    }

    public TGC_3JSPrimativeBox(TGC_GLProgramAbstract program, CharSequence name, float length) {
        this(program, name, length, length, length, null, null);
    }

    public TGC_3JSPrimativeBox(TGC_GLProgramAbstract program, CharSequence name, float width, float height, float depth) {
        this(program, name, width, height, depth, null, null);
    }

    public TGC_3JSPrimativeBox(TGC_GLProgramAbstract program, CharSequence name, float width, float height, float depth, TGC_GLTexture2DAbstract texture) {
        this(program, name, width, height, depth, texture, null);
    }

    public TGC_3JSPrimativeBox(TGC_GLProgramAbstract program, CharSequence name, float width, float height, float depth, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, width, height, depth, MIN_SEGMENTS(), MIN_SEGMENTS(), MIN_SEGMENTS(), texture, environment);
    }

    public TGC_3JSPrimativeBox(TGC_GLProgramAbstract program, CharSequence name, float width, float height, float depth, int widthSegments, int heightSegments, int depthSegments, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.widthSegments = widthSegments < MIN_SEGMENTS() ? MIN_SEGMENTS() : widthSegments;
        this.heightSegments = heightSegments < MIN_SEGMENTS() ? MIN_SEGMENTS() : heightSegments;
        this.depthSegments = depthSegments < MIN_SEGMENTS() ? MIN_SEGMENTS() : depthSegments;
    }
    public float width, height, depth;
    public int widthSegments, heightSegments, depthSegments;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new BoxBufferGeometry(width, height, depth, widthSegments, heightSegments, depthSegments);
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
