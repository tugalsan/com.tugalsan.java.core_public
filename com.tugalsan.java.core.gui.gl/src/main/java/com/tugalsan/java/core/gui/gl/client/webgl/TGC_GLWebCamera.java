package com.tugalsan.java.core.gui.gl.client.webgl;

import static com.shc.webgl4j.client.WebGL10.*;
import org.joml.Matrix4f;

public class TGC_GLWebCamera { 

    public Matrix4f projView = new Matrix4f();
    public float[] projView_tmp = new float[16];

    public void pushProjectView(int programID) {
        glUniformMatrix4fv(glGetUniformLocation(programID, TGC_GLWebShadersUtils.PARAMNAME_proj()), false, projView.get(projView_tmp));
    }

    public void setPerspective(TGC_GLWebCanvas canvas) {
        setPerspective(canvas, 70, 0.1f, 100);
    }

    public void setPerspective(TGC_GLWebCanvas canvas, int fovInDegrees, float zNear, float zFar) {
        projView.setPerspective((float) Math.toRadians(fovInDegrees), canvas.width / canvas.height, zNear, zFar);
    }

}
