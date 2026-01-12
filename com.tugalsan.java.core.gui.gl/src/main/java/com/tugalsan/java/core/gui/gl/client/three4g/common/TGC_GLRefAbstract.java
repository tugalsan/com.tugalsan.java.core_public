package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.animation.TGC_GLExtraAnimations;
import org.treblereel.gwt.three4g.scenes.Scene;

abstract public class TGC_GLRefAbstract extends TGC_GLLoadable {

    public TGC_GLRefAbstract(TGC_GLProgramAbstract program, CharSequence name) {
        super(program);
        this.name = name.toString();
        scene = new Scene();
        extraAnimations = new TGC_GLExtraAnimations(this);
    }
    public String name = null;
    public Scene scene;
    public TGC_GLExtraAnimations extraAnimations;
}
