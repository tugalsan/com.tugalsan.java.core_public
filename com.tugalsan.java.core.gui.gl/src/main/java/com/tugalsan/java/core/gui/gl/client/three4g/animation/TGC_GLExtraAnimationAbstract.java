package com.tugalsan.java.core.gui.gl.client.three4g.animation;

import org.treblereel.gwt.three4g.scenes.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;

abstract public class TGC_GLExtraAnimationAbstract { 

    public TGC_GLExtraAnimationAbstract(TGC_GLProgramAbstract program, CharSequence name, Scene scene) {
        this.program = program;
        this.name = name.toString();
        this.scene = scene;
    }
    public TGC_GLProgramAbstract program;
    public String name;
    public  Scene scene;

    abstract public void animate(TGC_GLProgramAbstract program, Scene scene);
}
