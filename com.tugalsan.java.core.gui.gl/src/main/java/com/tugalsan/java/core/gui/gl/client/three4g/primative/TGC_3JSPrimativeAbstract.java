package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.core.BufferGeometry;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import com.tugalsan.java.core.log.client.TGC_Log;


abstract public class TGC_3JSPrimativeAbstract extends TGC_GLLoadable {

    final private static TGC_Log d = TGC_Log.of(TGC_3JSPrimativeAbstract.class);

    public TGC_3JSPrimativeAbstract(TGC_GLProgramAbstract program, CharSequence name, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program);
        this.name = name.toString();
        this.texture = texture;
        this.environment = environment;
    }
    public String name;
    public TGC_GLTexture2DAbstract texture;
    public TGC_GLTextureCube environment;
    protected Mesh mesh = null;

    public boolean isLazyTransformNeeded() {
        return lazyTransformNeeded;
    }
    protected boolean lazyTransformNeeded = false;

    public TGC_3JSPrimativeAbstract setLazyPivot(float x, float y, float z) {
        lazyPivot.set(x, y, z);
        lazyTransformNeeded = true;
        return this;
    }
    protected Vector3 lazyPivot = new Vector3();

    public TGC_3JSPrimativeAbstract setLazyScale(float x, float y, float z) {
        lazyScale.set(x, y, z);
        lazyTransformNeeded = true;
        return this;
    }
    protected Vector3 lazyScale = new Vector3();

    public Mesh getMesh() {
        return TGS_FuncMTCUtils.call(() -> {
            if (meshUsedBefore) {
                return (Mesh) mesh.clone();
            } else {
                meshUsedBefore = true;
                return mesh;
            }
        }, e -> {
            cloneError = e.getMessage();
            d.ce("getMesh", "name", name, "EXCEPTION", e);
            return null;
        });
    }
    public String cloneError = null;

    public boolean isMeshUsedBefore() {
        return meshUsedBefore;
    }
    protected boolean meshUsedBefore = false;

    public TGC_3JSPrimativeAbstract setMeshUsedBefore_false() {
        meshUsedBefore = false;
        return this;
    }

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_LOADED()) {
            if (lazyTransformNeeded) {
                mesh.position.set(lazyPivot.x, lazyPivot.y, lazyPivot.z);
                mesh.scale.set(lazyScale.x, lazyScale.y, lazyScale.z);
                mesh.updateMatrix();
                lazyTransformNeeded = false;
                d.ci("lazyLoad", "transformed.name", name);
            }
            return;
        }

        if (status != TGC_GLLoadable.STATUS_INIT()) {
            return;
        }

        if (texture != null && texture.status != TGC_GLLoadable.STATUS_LOADED()) {
            texture.lazyLoad();
            return;
        }

        if (environment != null && environment.status != TGC_GLLoadable.STATUS_LOADED()) {
            environment.lazyLoad();
            return;
        }

        if (!additionalLoadValidation()) {
            return;
        }

        createMesh();
        status = TGC_GLLoadable.STATUS_LOADED();
        d.ci("name", name, "LOADED");
    }

    protected boolean additionalLoadValidation() {
        return true;
    }

    abstract protected TGC_3JSPrimativeAbstract createMesh();

    public TGC_3JSPrimativeAbstract destroy(boolean keepMaterial) {
        if (mesh != null) {
            mesh.geometry.dispose();
            if (!keepMaterial) {
                mesh.material.dispose();
            }
            mesh = null;
        }
        return this;
    }

    public Vector3 calculatePositionCenter() {
        mesh.geometry.computeBoundingBox();
        var b = ((BufferGeometry) mesh.geometry).boundingBox;
        var xMid = -0.5f * (b.max.x - b.min.x);
        var yMid = -0.5f * (b.max.y - b.min.y);
        var zMid = -0.5f * (b.max.z - b.min.z);
        return new Vector3(xMid, yMid, zMid);
    }
}
