package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTextureCube;
import org.treblereel.gwt.three4g.geometries.ParametricBufferGeometry;
import org.treblereel.gwt.three4g.geometries.ParametricGeometryFunction;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;

public class TGC_3JSPrimativeParametric extends TGC_3JSPrimativeAbstract {

    public static int DEF_SLICES() {
        return 16;
    }

    public static int DEF_STACKS() {
        return 16;
    }

    public TGC_3JSPrimativeParametric(TGC_GLProgramAbstract program, CharSequence name, ParametricGeometryFunction func) {
        this(program, name, func, DEF_SLICES(), DEF_STACKS(), null, null);
    }

    public TGC_3JSPrimativeParametric(TGC_GLProgramAbstract program, CharSequence name, ParametricGeometryFunction func,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        this(program, name, func, DEF_SLICES(), DEF_STACKS(), texture, environment);
    }

    public TGC_3JSPrimativeParametric(TGC_GLProgramAbstract program, CharSequence name, ParametricGeometryFunction func, int slices, int stacks) {
        this(program, name, func, slices, stacks, null, null);
    }

    public TGC_3JSPrimativeParametric(TGC_GLProgramAbstract program, CharSequence name, ParametricGeometryFunction func, int slices, int stacks,
            TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program, name, texture, environment);
        this.func = func;
        this.slices = slices;
        this.stacks = stacks;
//        program.d("getParametricGeometries.adding...");
//        TGC_3JSResourceUtils.addLib(TGC_3JSResourceUtils.INSTANCE.getParametricGeometries());
//        program.d("getParametricGeometries.added");
    }
    public ParametricGeometryFunction func;
    public int slices, stacks;

    @Override
    protected TGC_3JSPrimativeAbstract createMesh() {
        var geometry = new ParametricBufferGeometry(func, slices, stacks);
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

//    /**
//     * @author Dmitrii Tikhomirov <chani@me.com>
//     * Created by treblereel on 7/12/18.
//     */
//    @JsType(isNative = true, namespace = "THREE", name = "ParametricGeometries")
//    public static class TGC_3JSParametricGeometriesNative {
//
//        public static ParametricGeometryFunction klein ();
//
//        public static ParametricGeometryFunction mobius ();
//
//        public static ParametricGeometryFunction mobius3d ();
//    }
    /**
     * @author Dmitrii Tikhomirov <chani@me.com>
     * Created by treblereel on 7/12/18.
     */
    public static ParametricGeometryFunction func_pellipticParaboloid1 = (float u, float v) -> {
        u *= 1;
        v *= (float) (360 * Math.PI / 180);
        
        var a = 200; // semimajor axis a
        var b = 400; // semimajor axis b
        var h = 350; // height
        
        var x = a * Math.sqrt(u) * Math.cos(v);
        var y = b * Math.sqrt(u) * Math.sin(v);
        var z = u * h;
        
        return new Vector3((float) x, (float) y, (float) z);
    };

    /**
     * @author Dmitrii Tikhomirov <chani@me.com>
     * Created by treblereel on 7/12/18.
     */
    public static ParametricGeometryFunction func_pellipticParaboloid2 = (float u, float v) -> {
        u *= 1;
        v *= (float) (240 * Math.PI / 180);
        
        var a = 300; // semimajor axes
        var h = 300; // height
        var m = Math.sin(u * (135 * Math.PI / 180)); // mod
        
        var x = a * Math.sqrt(u) * Math.cos(v) * m;
        var y = a * Math.sqrt(u) * Math.sin(v) * m;
        var z = u * h;
        
        return new Vector3((float) x, (float) y, (float) z);
    };
}
