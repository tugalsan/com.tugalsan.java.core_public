package com.tugalsan.java.core.gui.gl.client.webgl;

public class TGC_GLWebPrimativeUtils {

    public static float[] vertex_rectangle_3d() {
        return new float[]{
            -0.8f, +0.8f, 0.0f,
            +0.8f, +0.8f, 0.0f,
            -0.8f, -0.8f, 0.0f,
            +0.8f, +0.8f, 0.0f,
            +0.8f, -0.8f, 0.0f,
            -0.8f, -0.8f, 0.0f
        };
    }

    public static float[] vertex_rectangle_2d() {
        return new float[]{
            -0.8f, +0.8f,
            +0.8f, +0.8f,
            -0.8f, -0.8f,
            +0.8f, +0.8f,
            +0.8f, -0.8f,
            -0.8f, -0.8f
        };
    }

    public static float[] color_rectangle() {
        return new float[]{
            1, 0, 0, 1,
            0, 1, 0, 1,
            0, 0, 1, 1,
            0, 1, 0, 1,
            1, 1, 1, 1,
            0, 0, 1, 1
        };
    }

    public static float[] texCoords_rectangle() {
        return new float[]{
            0, 0,
            1, 0,
            0, 1,
            1, 0,
            1, 1,
            0, 1
        };
    }

    public static float[] vertex_triangle_3d() {
        return new float[]{
            +0.0f, +0.8f, 0.0f,
            +0.8f, -0.8f, 0.0f,
            -0.8f, -0.8f, 0.0f
        };
    }

    public static float[] vertex_triangle_2d() {
        return new float[]{
            +0.0f, +0.8f,
            +0.8f, -0.8f,
            -0.8f, -0.8f
        };
    }

    public static float[] color_triangle() {
        return new float[]{
            1, 0, 0, 1,
            0, 1, 0, 1,
            0, 0, 1, 1
        };
    }

    public static float[] texCoords_triangle() {
        return new float[]{
            0, 0,
            1, 0,
            0, 1,
            1, 0,
            1, 1,
            0, 1
        };
    }

    public static float[] vertex_cube() {
        return new float[]{
            // Front face
            -1, +1, +1,
            -1, -1, +1,
            +1, +1, +1,
            +1, +1, +1,
            -1, -1, +1,
            +1, -1, +1,
            // Right face
            +1, +1, +1,
            +1, -1, +1,
            +1, +1, -1,
            +1, +1, -1,
            +1, -1, +1,
            +1, -1, -1,
            // Back face
            +1, +1, -1,
            +1, -1, -1,
            -1, -1, -1,
            -1, -1, -1,
            -1, +1, -1,
            +1, +1, -1,
            // Left face
            -1, +1, -1,
            -1, -1, -1,
            -1, -1, +1,
            -1, -1, +1,
            -1, +1, +1,
            -1, +1, -1,
            // Top face
            -1, +1, -1,
            -1, +1, +1,
            +1, +1, -1,
            +1, +1, -1,
            -1, +1, +1,
            +1, +1, +1,
            // Bottom face
            -1, -1, -1,
            -1, -1, +1,
            +1, -1, -1,
            +1, -1, -1,
            -1, -1, +1,
            +1, -1, +1
        };
    }

    public static float[] color_cube() {
        return new float[]{
            // Front face
            1, 1, 1, 1,
            0, 0, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            // Right face
            1, 1, 1, 1,
            0, 0, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            // Back face
            1, 1, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            // Left face
            1, 1, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            // Top face
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            // Bottom face
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1,
            0, 0, 1, 1
        };
    }

    public static float[] texCoords_cube() {
        return new float[]{
            // Front face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1,
            // Right face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1,
            // Back face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1,
            // Left face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1,
            // Top face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1,
            // Bottom face
            0, 0,
            0, 1,
            1, 0,
            1, 0,
            0, 1,
            1, 1
        };
    }
}
