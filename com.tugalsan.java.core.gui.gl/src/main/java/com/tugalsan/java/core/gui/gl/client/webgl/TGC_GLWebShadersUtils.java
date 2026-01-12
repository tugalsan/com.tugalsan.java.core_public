package com.tugalsan.java.core.gui.gl.client.webgl;

import static com.shc.webgl4j.client.WebGL10.GL_FLOAT;
import static com.shc.webgl4j.client.WebGL10.GL_FRAGMENT_SHADER;
import static com.shc.webgl4j.client.WebGL10.GL_VERTEX_SHADER;
import static com.shc.webgl4j.client.WebGL10.glAttachShader;
import static com.shc.webgl4j.client.WebGL10.glCompileShader;
import static com.shc.webgl4j.client.WebGL10.glCreateProgram;
import static com.shc.webgl4j.client.WebGL10.glCreateShader;
import static com.shc.webgl4j.client.WebGL10.glEnableVertexAttribArray;
import static com.shc.webgl4j.client.WebGL10.glGetAttribLocation;
import static com.shc.webgl4j.client.WebGL10.glLinkProgram;
import static com.shc.webgl4j.client.WebGL10.glShaderSource;
import static com.shc.webgl4j.client.WebGL10.glUseProgram;
import static com.shc.webgl4j.client.WebGL10.glVertexAttribPointer;

/* OpenGL ES Shader Language (known as ES SL) https://www.tutorialspoint.com/webgl/associating_attributes_buffer_objects.htm
Types:
    Primatives    : void, bool, int, float
    FloatVectors  : vec2, vec3, vec4,
    BooleanVectors: bvec2, bvec3, bvec4
    IntegerVectors: ivec2, ivec3, ivec4
    FloatMatrix   : mat2, mat3, mat4
Texture:
    AccessTo2DTexture        : sampler2D
    AccessToCubeMappedTexture: samplerCube
Attribute:
    This qualifier acts as a link between a vertex shader and OpenGL ES for per-vertex data. 
    The value of this attribute changes for every execution of the vertex shader.
Uniform:
    This qualifier links shader programs and the WebGL application. 
    Unlike attribute qualifier, the values of uniforms do not change. 
    Uniforms are read-only; you can use them with any basic data types, to declare a variable.
    Example − uniform vec4 lightPosition;
Varying:
    This qualifier forms a link between a vertex shader and fragment shader for interpolated data. 
    It can be used with the following data types − float, vec2, vec3, vec4, mat2, mat3, mat4, or arrays.
    Example − varying vec3 normal;
Vertex Shader: 
    handles below per vertex:
        Vertex transformation
        Normal transformation and normalization
        Texture coordinate generation
        Texture coordinate transformation
        Lighting
        Color material application
    Predefined Variables:
        highp vec4 gl_Position;     Holds the position of the vertex.
        mediump float gl_PointSize; Holds the transformed point size. The units for this variable are pixels.
Fragment Shader:
    A mesh is formed by multiple triangles, and the surface of the each triangle is known as a fragment. 
    A fragment shader is the code that runs on every pixel on each fragment. 
    This is written to calculate and fill the color on individual pixels.
    handles below per fragment:
        Operations on interpolated values
        Texture access
        Texture application
        Fog
        Color sum
    Predefined Variables:
        mediump vec4 gl_FragCoord;   Holds the fragment position within the frame buffer.
        bool gl_FrontFacing;         Holds the fragment that belongs to a front-facing primitive.
        mediump vec2 gl_PointCoord;  Holds the fragment position within a point (point rasterization only).
        mediump vec4 gl_FragColor;   Holds the output fragment color value of the shader
        mediump vec4 gl_FragData[n]; Holds the fragment color for color attachment n.

 */
public class TGC_GLWebShadersUtils {

    public static String PARAMNAME_position() {
        return "position";
    }

    public static String PARAMNAME_texCoords() {
        return "texCoords";
    }

    public static String PARAMNAME_color() {
        return "color";
    }

    public static String PARAMNAME_proj() {
        return "proj";
    }

    public static int linkBufferToShader(int programId, CharSequence bufferName, int bufferDataSize) {
        var attributeLoc = glGetAttribLocation(programId, bufferName.toString());
        glVertexAttribPointer(attributeLoc, bufferDataSize, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(attributeLoc);
        return attributeLoc;
    }

    public static int createProgramId(int vertexShaderId, Integer fragmentShaderId) {
        var programId = glCreateProgram();
        glAttachShader(programId, vertexShaderId);
        if (fragmentShaderId != null) {
            glAttachShader(programId, fragmentShaderId);
        }
        glLinkProgram(programId);
        glUseProgram(programId);
        return programId;
    }

    public static void useProgram(int programId) {
        glUseProgram(programId);
    }

    public static int createVertexShader(CharSequence vsSource) {
        var vsShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vsShaderID, vsSource.toString());
        glCompileShader(vsShaderID);
        return vsShaderID;
    }

    public static int createFragmentShader(CharSequence fsSource) {
        var fsShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fsShaderID, fsSource.toString());
        glCompileShader(fsShaderID);
        return fsShaderID;
    }

    public static String getVertexShaderSource_Minimal_2d(boolean is2d) {
        return is2d ? "attribute vec2 position;void main(){gl_Position = vec4(position, 0.0, 1.0);}"
                : "attribute vec3 position;void main(){gl_Position = vec4(position, 1.0);}";
    }

    public static String getFragmentShaderSource_Minimal() {
        return "void main(){gl_FragColor = vec4(0, 0.8, 0, 1);}";
    }

    public static String getVertexShaderSource_lvl2(boolean isTextured) {
        var sb = new StringBuilder();
        sb.append("precision mediump float;");
        sb.append("uniform mat4 proj;");
        sb.append("attribute vec3 position;");
        sb.append(isTextured ? "attribute vec2 texCoords;varying vec2 vTexCoords;" : "attribute vec4 color;varying vec4 vColor;");
        sb.append("void main(){");
        sb.append(isTextured ? "vTexCoords = texCoords;" : "vColor = color;");
        sb.append("gl_Position = proj * vec4(position, 1.0);");
        sb.append("}");
        return sb.toString();
    }

    public static String getVertexShaderSource_lvl1(boolean is2d, boolean isTextured, int pointSize) {
        var sb = new StringBuilder();
        sb.append("precision mediump float;");
        sb.append("uniform mat4 proj;");
        if (is2d) {
            sb.append("attribute vec2 position;");
        } else {
            sb.append("attribute vec3 position;");
        }
        if (isTextured) {
            sb.append("attribute vec2 texCoords;");
            sb.append("varying vec2 vTexCoords;");
        } else {
            sb.append("attribute vec4 color;");
            sb.append("varying vec4 vColor;");
        }
        sb.append("void main(){");
        if (isTextured) {
            sb.append("vTexCoords = texCoords;");
        } else {
            sb.append("vColor = color;");
        }
        if (is2d) {
            sb.append("gl_Position = proj * vec4(position, 0.0, 1.0);");
        } else {
            sb.append("gl_Position = proj * vec4(position, 1.0);");
        }
        sb.append("gl_PointSize = ").append(pointSize).append(".0;");
        sb.append("}");
        return sb.toString();
    }

    public static String getFragmentShaderSource(boolean isTextured) {
        var sb = new StringBuilder();
        sb.append("precision mediump float;");
        sb.append(isTextured ? "uniform sampler2D tex;varying vec2 vTexCoords;" : "varying vec4 vColor;");
        sb.append("void main(){");
        sb.append(isTextured ? "gl_FragColor = texture2D(tex, vTexCoords);" : "gl_FragColor = vColor;");
        sb.append("}");
        return sb.toString();
    }
}
