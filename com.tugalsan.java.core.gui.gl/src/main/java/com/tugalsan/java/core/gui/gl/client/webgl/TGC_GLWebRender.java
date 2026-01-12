package com.tugalsan.java.core.gui.gl.client.webgl;

import com.shc.webgl4j.client.TimeUtil;
import static com.shc.webgl4j.client.WebGL10.*;

abstract public class TGC_GLWebRender {
 
    public static int renderTest_drawModeMax() {
        return 6;
    }
    public static int renderTest_drawMode = 0;

    public Integer programID;
    public TGC_GLWeb gl;
    public TGC_GLWebCanvas canvas;
    public TGC_GLWebCamera camera;

    public void initStandard() {
        {//bullshit code
            glClearColor(0f, 0f, 0f, 1f);
            {
                glEnable(GL_DEPTH_TEST);
                glDepthFunc(GL_LEQUAL);//default:GL_LEQUAL or GL_LESS
                glClearDepth(1);
            }
            {
                glClearStencil(0);
            }
            {
                glDisable(GL_BLEND);
                glBlendFunc(GL_ONE, GL_ZERO);//default: source:GL_ONE, target_buffer:GL_ZERO // or GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA
                glBlendEquation(GL_FUNC_ADD);//default: GL_FUNC_ADD
            }
            {
//            glEnable(GL_CULL_FACE);
                glCullFace(GL_BACK);
            }
            {
//?            glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
//?            glPolygonMode(GL_FRONT, GL_FILL);
//?            glShadeModel(GL_SMOOTH);
            }
            {
                glLineWidth(2);
//?            glEnable(GL_LINE_SMOOTH);
//?            glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
            }
            {
//?            glEnable(GL_POINT_SMOOTH);
//?            glHint(GL_POINT_SMOOTH_HINT, GL_NICEST);
//?            glPointSize(1);
            }
        }
    }

    public abstract void init();

    public abstract void render(double ms_cur);

    public void renderTest(double ms_cur) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        renderTest_angle++;
        var z = -4L + (float) Math.sin(TimeUtil.currentSeconds());
        camera.setPerspective(canvas);
        camera.projView.translate(0, 0, z)
                .rotateX((float) Math.toRadians(renderTest_angle))
                .rotateY((float) Math.toRadians(renderTest_angle))
                .rotateZ((float) Math.toRadians(renderTest_angle));
        camera.pushProjectView(programID);

        switch (renderTest_drawMode) {
            case 0:
                drawTriangles(false, renderTest_vertices.length);
                break;
            case 1:
                drawTriangleStrip(false, renderTest_vertices.length);
                break;
            case 2:
                drawTriangleFan(false, renderTest_vertices.length);
                break;
            case 3:
                drawPoints(false, renderTest_vertices.length);
                break;
            case 4:
                drawLines(false, renderTest_vertices.length);
                break;
            case 5:
                drawLineLoop(false, renderTest_vertices.length);
                break;
            default:
                //6
                drawLineStrip(false, renderTest_vertices.length);
                break;
        }
    }
    private float renderTest_angle = 0;
    private float[] renderTest_vertices;

    public static void drawTriangles(boolean is2d, int bufferLength) {
        glDrawArrays(GL_TRIANGLES, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawTriangleStrip(boolean is2d, int bufferLength) {
        glDrawArrays(GL_TRIANGLE_STRIP, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawTriangleFan(boolean is2d, int bufferLength) {
        glDrawArrays(GL_TRIANGLE_FAN, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawPoints(boolean is2d, int bufferLength) {
        glDrawArrays(GL_POINTS, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawLineStrip(boolean is2d, int bufferLength) {
        glDrawArrays(GL_LINE_STRIP, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawLines(boolean is2d, int bufferLength) {
        glDrawArrays(GL_LINES, 0, bufferLength / (is2d ? 2 : 3));
    }

    public static void drawLineLoop(boolean is2d, int bufferLength) {
        glDrawArrays(GL_LINE_LOOP, 0, bufferLength / (is2d ? 2 : 3));
    }

    public void initTestRectangle(Integer imageId) {
        var vertexShaderId = gl.shaderUtils.createVertexShader(gl.shaderUtils.getVertexShaderSource_lvl1(false, imageId != null, 10));
        var fragmentShaderId = gl.shaderUtils.createFragmentShader(gl.shaderUtils.getFragmentShaderSource(imageId != null));
        programID = gl.shaderUtils.createProgramId(vertexShaderId, fragmentShaderId);
        var vertexDimensionCount = 3;
        renderTest_vertices = TGC_GLWebPrimativeUtils.vertex_rectangle_3d();
        var vboPosID = gl.bufferUtils.createArrayBufferId(renderTest_vertices);
        gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_position(), vertexDimensionCount);
        if (imageId == null) {
            var colors = TGC_GLWebPrimativeUtils.color_rectangle();
            var vboColID = gl.bufferUtils.createArrayBufferId(colors);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_color(), 4);
        } else {
            var texCoords = TGC_GLWebPrimativeUtils.texCoords_rectangle();
            var vboTexID = gl.bufferUtils.createArrayBufferId(texCoords);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_texCoords(), 2);
//            loadImage(imageId);
        }
    }

    public void initTestTriangle(Integer imageId) {
        var vertexShaderId = gl.shaderUtils.createVertexShader(gl.shaderUtils.getVertexShaderSource_lvl1(false, imageId != null, 10));
        var fragmentShaderId = gl.shaderUtils.createFragmentShader(gl.shaderUtils.getFragmentShaderSource(imageId != null));
        programID = gl.shaderUtils.createProgramId(vertexShaderId, fragmentShaderId);
        var vertexDimensionCount = 3;
        renderTest_vertices = TGC_GLWebPrimativeUtils.vertex_triangle_3d();
        var vboPosID = gl.bufferUtils.createArrayBufferId(renderTest_vertices);
        gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_position(), vertexDimensionCount);
        if (imageId == null) {
            var color = TGC_GLWebPrimativeUtils.color_triangle();
            var vboColID = gl.bufferUtils.createArrayBufferId(color);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_color(), 4);
        } else {
            var texCoords = TGC_GLWebPrimativeUtils.texCoords_triangle();
            var vboTexID = gl.bufferUtils.createArrayBufferId(texCoords);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_texCoords(), 2);
//            loadImage(imageId);
        }
    }

    public void initTestCube(Integer imageId) {
        var vertexShaderId = gl.shaderUtils.createVertexShader(gl.shaderUtils.getVertexShaderSource_lvl1(false, imageId != null, 10));
        var fragmentShaderId = gl.shaderUtils.createFragmentShader(gl.shaderUtils.getFragmentShaderSource(imageId != null));
        programID = gl.shaderUtils.createProgramId(vertexShaderId, fragmentShaderId);
        var vertexDimensionCount = 3;
        renderTest_vertices = TGC_GLWebPrimativeUtils.vertex_cube();
        var vboPosID = gl.bufferUtils.createArrayBufferId(renderTest_vertices);
        gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_position(), vertexDimensionCount);
        if (imageId == null) {
            var color = TGC_GLWebPrimativeUtils.color_cube();
            var vboColID = gl.bufferUtils.createArrayBufferId(color);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_color(), 4);
        } else {
            var texCoords = TGC_GLWebPrimativeUtils.texCoords_cube();
            var vboTexID = gl.bufferUtils.createArrayBufferId(texCoords);
            gl.shaderUtils.linkBufferToShader(programID, gl.shaderUtils.PARAMNAME_texCoords(), 2);
//            loadImage(imageId);
        }
    }

}
