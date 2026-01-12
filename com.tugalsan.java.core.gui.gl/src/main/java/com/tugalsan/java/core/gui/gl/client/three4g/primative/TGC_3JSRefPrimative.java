package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLRefAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import org.treblereel.gwt.three4g.geometries.EdgesGeometry;
import org.treblereel.gwt.three4g.materials.LineBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.objects.LineSegments;
import org.treblereel.gwt.three4g.objects.Mesh;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_3JSRefPrimative extends TGC_GLRefAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_3JSRefPrimative.class);

    public TGC_3JSRefPrimative(CharSequence name, TGC_3JSPrimativeAbstract primative) {
        super(primative.program, name);
        this.primative = primative;
    }
    public TGC_3JSPrimativeAbstract primative;
    public Mesh childMesh;

    public boolean edgeLineVisible = false;
    private LineSegments edgeLine = null;

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_INIT()) {
            if (primative.status == TGC_GLLoadable.STATUS_LOADED() && !primative.isLazyTransformNeeded()) {
                childMesh = primative.getMesh();
                primative.mesh.matrixAutoUpdate = false;
                childMesh.matrixAutoUpdate = false;
                if (childMesh == null) {
                    errorMessage = primative.cloneError;
                    status = TGC_GLLoadable.STATUS_ERROR();
                    d.ce("lazyLoad", "name", name, "childMesh == null", errorMessage);
                } else {
                    scene.add(childMesh);
                    status = TGC_GLLoadable.STATUS_LOADED();
                    d.ci("lazyLoad", "name", name, "LOADED");
                }
            }
        } else if (status == TGC_GLLoadable.STATUS_LOADED()) {
            if (setLazyMaterialWireframe != null) {
                if (childMesh.material instanceof MeshBasicMaterial) {
                    ((MeshBasicMaterial) childMesh.material).wireframe = setLazyMaterialWireframe;
                    setLazyMaterialWireframe = null;
                }
            }
            if (edgeLineVisible) {
                if (edgeLine == null) {
                    var geometry = new EdgesGeometry(childMesh.geometry);
                    var material = new LineBasicMaterial();
                    material.color = program.color.getWhite_0xFFFFFF();
                    edgeLine = new LineSegments(geometry, material);
                    edgeLine.position.copy(childMesh.position);
                    edgeLine.rotation.copy(childMesh.rotation);
                    edgeLine.scale.copy(childMesh.scale);
                    scene.add(edgeLine);
                }
                if (edgeLine.visible == false) {
                    edgeLine.visible = true;
                }
            } else {
                if (edgeLine != null && edgeLine.visible == true) {
                    edgeLine.visible = false;
                }
            }
            extraAnimations.animate();
        }
        primative.lazyLoad();
    }
    public Boolean setLazyMaterialWireframe = null;

    public void destroy() {
        if (childMesh != null) {
            if (primative.mesh.equals(childMesh)) {
                primative.setMeshUsedBefore_false();
            }
            scene.remove(childMesh);
            childMesh = null;
        }
    }
}
