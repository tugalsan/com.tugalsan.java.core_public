package com.tugalsan.java.core.gui.gl.client.webgl;

import com.google.gwt.canvas.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.shc.webgl4j.client.*;
import com.tugalsan.java.core.string.client.*;

public class TGC_GLWebSupportUtils {

    private static String isSupportedHTML(CharSequence name, boolean value) {
        return TGS_StringUtils.cmn().concat("<b>", name, ": </b>", (value ? "Supported" : "Not Supported"), "</br>");
    }

    public static HTML createInformationWidget(boolean isWebGL20) {
        var webglInfo = new StringBuilder("<h2>Browser</h2>");
        webglInfo.append("<p style='padding-left: 40px;'>");
        {
            webglInfo.append("<b>User Agent: </b>").append(Window.Navigator.getUserAgent()).append("</br>");
            webglInfo.append("<b>Platform: </b>").append(Window.Navigator.getPlatform()).append("</br>");
        }
        webglInfo.append("</p>");

        var canvas = Canvas.createIfSupported();
        webglInfo.append("<h2>WebGL Canvas</h2>");
        webglInfo.append("<p style='padding-left: 40px;'>");
        {
            webglInfo.append(isSupportedHTML("Canvas", canvas != null));
            webglInfo.append(isSupportedHTML("WebGL 1.0", WebGL10.isSupported()));
            webglInfo.append(isSupportedHTML("WebGL 2.0", WebGL20.isSupported()));
        }
        webglInfo.append("</p>");
        if (canvas == null || (isWebGL20 && !WebGL20.isSupported()) || (!isWebGL20 && !WebGL10.isSupported())) {
            return new HTML(webglInfo.toString());
        }

        var contextGL = isWebGL20 ? WebGL20.createContext(canvas) : WebGL10.createContext(canvas);
        contextGL.makeCurrent();
        webglInfo.append("<h2>WebGL Context</h2>");
        webglInfo.append("<p style='padding-left: 40px;'>");
        {
            {
                var idx = WebGL10.GL_VERSION;
                var prm = WebGL10.glGetParameter(idx);
                var str = String.valueOf(prm);
                webglInfo.append("<b>WebGL version: </b>").append(str).append("</br>");
            }
            {
                var idx = WebGL10.GL_SHADING_LANGUAGE_VERSION;
                var prm = WebGL10.glGetParameter(idx);
                var str = String.valueOf(prm);
                webglInfo.append("<b>GLSL version: </b>").append(str).append("</br>");
            }
            {
                var idx = WebGL10.GL_RENDERER;
                var prm = WebGL10.glGetParameter(idx);
                var str = String.valueOf(prm);
                webglInfo.append("<b>WebGL renderer: </b>").append(str).append("</br>");
            }
            {
                var idx = WebGL10.GL_VENDOR;
                var prm = WebGL10.glGetParameter(idx);
                var str = String.valueOf(prm);
                webglInfo.append("<b>WebGL vendor: </b>").append(str).append("</br>");
            }
            if (WEBGL_debug_renderer_info.isSupported()) {
                WEBGL_debug_renderer_info.enableExtension();
                {
                    var idx = WEBGL_debug_renderer_info.GL_UNMASKED_RENDERER_WEBGL;
                    var prm = WebGL10.glGetParameter(idx);
                    var str = String.valueOf(prm);
                    webglInfo.append("<b>Unmasked renderer: </b>").append(str).append("</br>");
                }
                {
                    var idx = WEBGL_debug_renderer_info.GL_UNMASKED_VENDOR_WEBGL;
                    var prm = WebGL10.glGetParameter(idx);
                    var str = String.valueOf(prm);
                    webglInfo.append("<b>Unmasked vendor: </b>").append(str).append("</br>");
                }
            }
        }
        webglInfo.append("</p>");

        webglInfo.append("<h2>WebGL Extensions</h2>");
        webglInfo.append("<p style='padding-left: 40px;'>");
        {
            webglInfo.append(isSupportedHTML("ANGLE_instanced_arrays", ANGLE_instanced_arrays.isSupported()));

            webglInfo.append(isSupportedHTML("EXT_blend_minmax", EXT_blend_minmax.isSupported()));
            webglInfo.append(isSupportedHTML("EXT_frag_depth", EXT_frag_depth.isSupported()));
            webglInfo.append(isSupportedHTML("EXT_shader_texture_lod", EXT_shader_texture_lod.isSupported()));
            webglInfo.append(isSupportedHTML("EXT_texture_filter_anisotropic", EXT_texture_filter_anisotropic.isSupported()));

            webglInfo.append(isSupportedHTML("OES_element_index_uint", OES_element_index_uint.isSupported()));
            webglInfo.append(isSupportedHTML("OES_standard_derivatives", OES_standard_derivatives.isSupported()));
            webglInfo.append(isSupportedHTML("OES_texture_float", OES_texture_float.isSupported()));
            webglInfo.append(isSupportedHTML("OES_texture_float_linear", OES_texture_float_linear.isSupported()));
            webglInfo.append(isSupportedHTML("OES_texture_half_float", OES_texture_half_float.isSupported()));
            webglInfo.append(isSupportedHTML("OES_texture_half_float_linear", OES_texture_half_float_linear.isSupported()));
            webglInfo.append(isSupportedHTML("OES_vertex_array_object", OES_vertex_array_object.isSupported()));

            webglInfo.append(isSupportedHTML("WEBGL_compressed_texture_s3tc", WEBGL_compressed_texture_s3tc.isSupported()));
            webglInfo.append(isSupportedHTML("WEBGL_debug_renderer_info", WEBGL_debug_renderer_info.isSupported()));
            webglInfo.append(isSupportedHTML("WEBGL_debug_shaders", WEBGL_debug_shaders.isSupported()));
            webglInfo.append(isSupportedHTML("WEBGL_depth_texture", WEBGL_depth_texture.isSupported()));
            webglInfo.append(isSupportedHTML("WEBGL_draw_buffers", WEBGL_draw_buffers.isSupported()));
            webglInfo.append(isSupportedHTML("WEBGL_lose_context", WEBGL_lose_context.isSupported()));
        }
        webglInfo.append("</p>");

        var attributes = WebGLContext.getCurrent().getAttributes();
        webglInfo.append("<h2>Context Attributes</h2>");
        webglInfo.append("<p style='padding-left: 40px;'>");
        {
            webglInfo.append("<b>Alpha: </b>").append(attributes.getAlpha()).append("</br>");
            webglInfo.append("<b>Antialias: </b>").append(attributes.getAntialias()).append("</br>");
            webglInfo.append("<b>Depth: </b>").append(attributes.getDepth()).append("</br>");
            webglInfo.append("<b>FailIfMajorPerformanceCaveat: </b>").append(attributes.getFailIfMajorPerformanceCaveat()).append("</br>");
            webglInfo.append("<b>PremultipliedAlpha: </b>").append(attributes.getPremultipliedAlpha()).append("</br>");
            webglInfo.append("<b>PreserveDrawingBuffer: </b>").append(attributes.getPreserveDrawingBuffer()).append("</br>");
            webglInfo.append("<b>Stencil: </b>").append(attributes.getStencil()).append("</br>");
        }
        webglInfo.append("</p>");

        return new HTML(webglInfo.toString());
    }

    public static boolean isWEBGL1Supported() {
        return WebGL10.isSupported();
    }

    public static boolean isWEBGL2Supported() {
        return WebGL20.isSupported();
    }
}
