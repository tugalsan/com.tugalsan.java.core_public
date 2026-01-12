package com.tugalsan.java.core.gui.gl.client.webgl;

import com.shc.webgl4j.client.WebGL10;
import static com.shc.webgl4j.client.WebGL10.GL_ARRAY_BUFFER;
import static com.shc.webgl4j.client.WebGL10.GL_BUFFER_SIZE;
import static com.shc.webgl4j.client.WebGL10.GL_BUFFER_USAGE;
import static com.shc.webgl4j.client.WebGL10.GL_DYNAMIC_DRAW;
import static com.shc.webgl4j.client.WebGL10.GL_ELEMENT_ARRAY_BUFFER;
import static com.shc.webgl4j.client.WebGL10.GL_STATIC_DRAW;
import static com.shc.webgl4j.client.WebGL10.GL_STREAM_DRAW;
import static com.shc.webgl4j.client.WebGL10.glBindBuffer;
import static com.shc.webgl4j.client.WebGL10.glBufferData;
import static com.shc.webgl4j.client.WebGL10.glCreateBuffer;

public class TGC_GLWebBufferUtils {

    public static int getBufferUsage(boolean setPermanant, boolean usageFrequent) {
        return !setPermanant ? GL_DYNAMIC_DRAW : (usageFrequent ? GL_STATIC_DRAW : GL_STREAM_DRAW);
    }

    public static int createArrayBufferId(float[] bufferData) {
        return createArrayBufferId(bufferData, true, true);
    }

    public static int createArrayBufferId(float[] bufferData, boolean setPermanant, boolean usageFrequent) {
        var bufferId = glCreateBuffer();
        glBindBuffer(GL_ARRAY_BUFFER, bufferId);
        glBufferData(GL_ARRAY_BUFFER, bufferData, getBufferUsage(setPermanant, usageFrequent));
        return bufferId;
    }

    public static int createElementBufferId(float[] indiceData) {
        return createElementBufferId(indiceData, true, true);
    }

    public static int createElementBufferId(float[] indiceData, boolean setPermanant, boolean usageFrequent) {
        var bufferId = glCreateBuffer();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indiceData, getBufferUsage(setPermanant, usageFrequent));
        return bufferId;
    }

    public static boolean isBuffer(int bufferId) {
        return WebGL10.glIsBuffer(bufferId);
    }

    public static void deleteBuffer(int bufferId) {
        WebGL10.glDeleteBuffer(bufferId);
    }

    public static int getArrayBufferParameter_size() {
        return WebGL10.glGetBufferParameter(GL_ARRAY_BUFFER, GL_BUFFER_SIZE);
    }

    public static int getArrayBufferParameter_usage() {
        return WebGL10.glGetBufferParameter(GL_ARRAY_BUFFER, GL_BUFFER_USAGE);
    }

    public static int getElementBufferParameter_size() {
        return WebGL10.glGetBufferParameter(GL_ELEMENT_ARRAY_BUFFER, GL_BUFFER_SIZE);
    }

    public static int getElementBufferParameter_usage() {
        return WebGL10.glGetBufferParameter(GL_ELEMENT_ARRAY_BUFFER, GL_BUFFER_USAGE);
    }
}
