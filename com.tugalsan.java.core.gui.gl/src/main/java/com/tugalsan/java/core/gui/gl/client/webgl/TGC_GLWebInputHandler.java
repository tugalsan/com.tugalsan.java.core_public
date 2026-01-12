package com.tugalsan.java.core.gui.gl.client.webgl;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_GLWebInputHandler {

    final private static TGC_Log d = TGC_Log.of(TGC_GLWebInputHandler.class);

    public TGC_GLWebInputHandler() {
        mouseClickXY[0] = mouseClickXY[1] = mouseDoubleClickXY[0] = mouseDoubleClickXY[1] = -1;
    }

    final public boolean[] keyboard_keyCodePressed = new boolean[256];
    final public int[] mousePressedXY = new int[2];
    final public int[] mouseClickXY = new int[2];
    final public int[] mouseDoubleClickXY = new int[2];

    public void test() {
        if (mouseClickXY[0] != -1) {
            d.ce( "test", "mouseClickXY-> " + mouseClickXY[0] + ", " + mouseClickXY[1]);
            mouseClickXY[0] = mouseClickXY[1] = -1;
            TGC_GLWebRender.renderTest_drawMode = TGC_GLWebRender.renderTest_drawMode + 1 > TGC_GLWebRender.renderTest_drawModeMax() ? 0 : TGC_GLWebRender.renderTest_drawMode + 1;
            d.ce( "test", "renderTest_drawMode-> " + TGC_GLWebRender.renderTest_drawMode);

        }
        if (mouseDoubleClickXY[0] != -1) {
            d.ce( "test", "mouseDoubleClickXY-> " + mouseDoubleClickXY[0] + ", " + mouseDoubleClickXY[1]);
            mouseDoubleClickXY[0] = mouseDoubleClickXY[1] = -1;
        }
    }

    public void addCanvas(Canvas canvas) {
        canvas.addKeyDownHandler(e -> {
            e.preventDefault();//ALSO TRY e.getNativeEvent().preventDefault();e.getNativeEvent().stopPropagation();
            keyboard_keyCodePressed[e.getNativeKeyCode()] = true;
        });
        canvas.addKeyUpHandler(e -> {
            e.preventDefault();
            keyboard_keyCodePressed[e.getNativeKeyCode()] = false;
        });
//            canvas.addKeyPressHandler((KeyPressEvent e) -> {
//                keyboard_keyPressed_char.offer(e.getCharCode());
//                keyboard_keyPressed_unicode.offer(e.getUnicodeCharCode());
//            });
        canvas.addClickHandler(e -> {
            if (e.getNativeButton() == 1) {
                mouseClickXY[0] = e.getX();
                mouseClickXY[1] = e.getY();
            }
        });
        canvas.addDoubleClickHandler(e -> {
            if (e.getNativeButton() == 1) {
                mouseDoubleClickXY[0] = e.getX();
                mouseDoubleClickXY[1] = e.getY();
            }
        });
        canvas.addMouseDownHandler(e -> {
            if (e.getNativeButton() == 1) {
                mousePressedXY[0] = e.getX();
                mousePressedXY[1] = e.getY();
            }
        });
        canvas.addMouseUpHandler(e -> {
            if (e.getNativeButton() == 1) {
                mousePressedXY[0] = mousePressedXY[1] = -1;
            }
        });
    }
}
