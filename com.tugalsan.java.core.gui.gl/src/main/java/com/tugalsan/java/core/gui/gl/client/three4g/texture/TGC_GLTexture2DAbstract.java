package com.tugalsan.java.core.gui.gl.client.three4g.texture;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;
import org.treblereel.gwt.three4g.*;
import org.treblereel.gwt.three4g.textures.*;

abstract public class TGC_GLTexture2DAbstract extends TGC_GLLoadable { 


    public TGC_GLTexture2DAbstract(TGC_GLProgramAbstract program) {
        super(program);
    }
    public Texture texture = null;

    public TGC_GLTexture2DAbstract setRepeat(boolean repeatEnable) {
        texture.wrapS = texture.wrapT = repeatEnable ? THREE.RepeatWrapping : THREE.ClampToEdgeWrapping;
        return this;
    }

    public Boolean getRepeat() {
        if (texture.wrapS == THREE.RepeatWrapping && texture.wrapT == THREE.RepeatWrapping) {
            return true;
        } else if (texture.wrapS == THREE.ClampToEdgeWrapping && texture.wrapT == THREE.ClampToEdgeWrapping) {
            return false;
        }
        return null;
    }

    public TGC_GLTexture2DAbstract setDetail(int anisotropy_from1_to16) {
        texture.anisotropy = anisotropy_from1_to16;
        return this;
    }

    public int getDetail() {
        return texture.anisotropy;
    }
}
