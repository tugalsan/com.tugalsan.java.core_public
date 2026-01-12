package com.tugalsan.java.core.gui.gl.client.three4g.color;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.math.Color;

public class TGC_GLColor {//EXAMPLS COLOR

    final public TGC_GLProgramAbstract program;

    public TGC_GLColor(TGC_GLProgramAbstract program) {
        this.program = program;
    }

    public Color createColor(int r, int g, int b) {
        var max = 255f;
        return new Color(r / max, g / max, b / max);
    }

    public Color getRed_FF4444() {
        return new Color(0xFF4444);
    }

    public Color getGrey_404040() {
        return new Color(0x404040);
    }

    public Color getLightGreen_BFE3DD() {
        return new Color(0xBFE3DD);
    }

    public Color getGreen_00FF00() {
        return new Color(0x00FF00);
    }

    public Color get_BBBBFF() {
        return new Color(0xBBBBFF);
    }

    public Color get_444422() {
        return new Color(0x444422);
    }

    public Color get_006699() {
        return new Color(0x006699);
    }

    public Color get_EFEFFF() {
        return new Color(0xEFEFFF);
    }

    public Color getWhite_0xFFFFFF() {
        return new Color(0xFFFFFF);
    }
}
