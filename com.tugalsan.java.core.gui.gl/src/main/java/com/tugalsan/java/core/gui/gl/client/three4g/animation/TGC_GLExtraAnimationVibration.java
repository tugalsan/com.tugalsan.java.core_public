package com.tugalsan.java.core.gui.gl.client.three4g.animation;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.scenes.Scene;

public class TGC_GLExtraAnimationVibration extends TGC_GLExtraAnimationAbstract {

    public TGC_GLExtraAnimationVibration(TGC_GLProgramAbstract program, CharSequence name, Scene scene, float k) {
        super(program, name, scene);
        this.k = k;
        reCompileK();
    }
    public float k;

    final public void reCompileK() {
        this.kCompiled = k * 0.0003f * program.renderer.animator.getMinMS();
    }
    private float kCompiled;

    @Override
    public void animate(TGC_GLProgramAbstract program, Scene scene) {
        scene.rotation.x += program.math.randomFloat(-kCompiled, kCompiled);
        scene.rotation.y += program.math.randomFloat(-kCompiled, kCompiled);
    }
}
