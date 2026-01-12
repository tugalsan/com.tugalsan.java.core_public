package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.primative.TGC_3JSPrimativeAbstract;
import java.util.List;
import org.treblereel.gwt.three4g.core.Object3D;

public class TGC_GLLocationGrid {

    public static void addToScene(TGC_GLProgramAbstract program, int offsetXInit, int offsetZ, List<TGC_GLLoadable> objects, boolean wireframe, boolean edgeLineVisible) {
        var g = new TGC_GLLocationGrid(offsetXInit, offsetZ);
        for (var i = 0; i < objects.size(); i++) {
            var o = objects.get(i);
            if (o instanceof TGC_3JSPrimativeAbstract) {
                var p = (TGC_3JSPrimativeAbstract) o;
                var r = program.sceneWorld.childPrimatives.add(p.name + " r0:" + i, p);
                g.setPosition(r.scene, 0);
                if (wireframe) {
                    r.setLazyMaterialWireframe = true;
                }
                if (edgeLineVisible) {
                    r.edgeLineVisible = true;
                }
            }
        }
    }

    public float offsetXInit;
    public float initY;
    public float offsetZ;
    public float offsetX;

    public TGC_GLLocationGrid(int offsetXInit, int offsetZ) {
        this.offsetXInit = offsetXInit;
        this.offsetZ = offsetZ;
        this.offsetX = offsetXInit;
    }

    public void setPosition(Object3D o, float y) {
        o.position.set(offsetX, y, offsetZ);
        if (offsetX + POST_X > -offsetXInit) {
            offsetX = offsetXInit;
            offsetZ++;
        } else {
            offsetX = offsetX + OFFSET_X;
        }
    }

    public float OFFSET_X = 1f;
    private final float POST_X = 2f;
}
