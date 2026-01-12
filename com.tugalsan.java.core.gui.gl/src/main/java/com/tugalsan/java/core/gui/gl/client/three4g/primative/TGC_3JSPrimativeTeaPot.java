package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import org.treblereel.gwt.three4g.extensions.resources.js.TGC_GLResourceUtils;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.core.BufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativeTeaPot extends TGC_3JSPrimativeAbstract {

    public static double MIN_DETAIL() {
        return 1;
    }

    public TGC_3JSPrimativeTeaPot(TGC_GLProgramAbstract program, CharSequence name, int size,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, size, MIN_DETAIL(), texture, environment);
    }

    public TGC_3JSPrimativeTeaPot(TGC_GLProgramAbstract program, CharSequence name, int size, double detail,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, size, detail, true, true, true, false, false, texture, environment);
    }

    public TGC_3JSPrimativeTeaPot(TGC_GLProgramAbstract program, CharSequence name, int size, double detail,
            boolean bottom, boolean lid, boolean body, boolean fitLid, boolean nonblinn,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getTeapotBufferGeometry());
        this.size = size;
        this.detail = detail < MIN_DETAIL() ? MIN_DETAIL() : detail;
        this.bottom = bottom;
        this.lid = lid;
        this.body = body;
        this.fitLid = fitLid;
        this.nonblinn = nonblinn;
        this.texture = texture;
        this.environment = environment;
    }

    public int size;
    public double detail;
    public double[] POS_TESS = new double[]{2, 3, 4, 5, 6, 8, 10, 15, 20, 30, 40, 50};
    public boolean bottom, lid, body, fitLid, nonblinn;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new TGC_3JSTeapotBufferGeometryNative(
                size, detail, bottom, lid, body, fitLid, !nonblinn);
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

    /**
     * @author Dmitrii Tikhomirov <chani@me.com>
     * Created by treblereel on 7/12/18.
     */
    @JsType(isNative = true, namespace = "THREE", name = "TeapotBufferGeometry")
    private static class TGC_3JSTeapotBufferGeometryNative extends BufferGeometry {

        public TGC_3JSTeapotBufferGeometryNative(int teapotSize, double detail, boolean bottom, boolean lid, boolean body, boolean fitLid, boolean blinn) {
        }
    }
}
