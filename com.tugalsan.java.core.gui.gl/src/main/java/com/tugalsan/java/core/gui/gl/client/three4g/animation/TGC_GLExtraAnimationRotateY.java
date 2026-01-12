package com.tugalsan.java.core.gui.gl.client.three4g.animation;

import org.treblereel.gwt.three4g.scenes.*;
import com.tugalsan.java.core.math.client.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;

public class TGC_GLExtraAnimationRotateY extends TGC_GLExtraAnimationAbstract {

    public TGC_GLExtraAnimationRotateY(TGC_GLProgramAbstract program, CharSequence name, Scene scene, float k) {
        super(program, name, scene);
        this.k = k;
        reCompileKRadian();
    }
    public float k;

    final public void reCompileKRadian() {
        this.kRadian = TGS_MathUtils.radianSimplified(program.math.getRadians(k * 0.003f * program.renderer.animator.getMinMS()));
    }
    private float kRadian;

    @Override
    public void animate(TGC_GLProgramAbstract program, Scene scene) {
        scene.rotation.y = TGS_MathUtils.radianSimplified(scene.rotation.y + program.math.getRadians(kRadian));
    }
}
