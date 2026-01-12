package com.tugalsan.java.core.gui.gl.client.webgl;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In2;
import java.util.*;
import java.util.stream.*;

import com.tugalsan.java.core.list.client.*;
 
public class TGC_GLWeb {

    public TGC_GLWeb(int canvasCapacity) {
        this.canvasCapacity = canvasCapacity;
        canvasBuffer = new TGC_GLWebCanvas[canvasCapacity];
    }

    public TGC_GLWebShadersUtils shaderUtils = new TGC_GLWebShadersUtils();
    public TGC_GLWebBufferUtils bufferUtils = new TGC_GLWebBufferUtils();
    public TGC_GLWebSupportUtils supportUtils = new TGC_GLWebSupportUtils();

    final private TGC_GLWebCanvas[] canvasBuffer;
    public TGC_GLWebInputHandler inputHandler = new TGC_GLWebInputHandler();
    final private List<Image> imageBuffer = TGS_ListUtils.of();
    public Integer canvasFocusedId = 0;
    public Integer canvasCapacity = 0;
    public Integer canvasCount = 0;

    public Integer createCanvasId(int xOffSet, int yOffSet, int width, int height, Integer qualityPercent_default100, TGC_GLWebRender render) {
        var canvasId = canvasCount;

        var onFocused = (TGS_FuncMTU_In2<Integer, Boolean>) (Integer canvasId1, Boolean isFocused) -> {
            if (isFocused) {
                canvasFocusedId = canvasId1;
                return;
            }
            if (canvasFocusedId == canvasId1.intValue()) {
                canvasFocusedId = null;
            }
        };
        var canvas = new TGC_GLWebCanvas(canvasId, this, xOffSet, yOffSet, width, height, qualityPercent_default100, render, onFocused);
        if (canvas.canvas == null) {
            return null;
        }
        inputHandler.addCanvas(canvas.canvas);
        canvasBuffer[canvasId] = canvas;
        canvasCount++;
        return canvasId;
    }

    public int createImageId(CharSequence httpSrc) {
        var image = new Image(httpSrc.toString());
        image.setVisible(false);
        image.setSize("0px", "0px");
        RootPanel.get().add(image);//texture image size should be 2^x!!!
        imageBuffer.add(image);
        return imageBuffer.size() - 1;
    }

    public Image getImage(Integer imageId) {
        if (checkImageId(imageId)) {
            return imageBuffer.get(imageId);
        }
        return null;
    }

    public TGC_GLWebCanvas getCanvas(Integer canvasId) {
        if (checkCanvasId(canvasId)) {
            return canvasBuffer[canvasId];
        }
        return null;
    }

    public void render(double ms_cur, Integer canvasId, boolean checkIfFocused) {
        if (checkIfFocused && canvasId.intValue() != canvasFocusedId) {
            return;
        }
        if (checkCanvasId(canvasId)) {
            canvasBuffer[canvasId].context.makeCurrent();
            canvasBuffer[canvasId].render.render(ms_cur);
        }
    }

    public void renderAll(double ms_cur, boolean checkIfFocused) {
        if (checkIfFocused) {
            render(ms_cur, canvasFocusedId, false);
        } else {
            for (var canvasId = 0; canvasId < canvasCount; canvasId++) {
                render(ms_cur, canvasId, checkIfFocused);
            }
        }
    }

    public void init(Integer canvasId) {
        if (checkCanvasId(canvasId)) {
            canvasBuffer[canvasId].context.makeCurrent();
            canvasBuffer[canvasId].render.init();
        }
    }

    public void initAll() {
        IntStream.range(0, canvasCount).forEach(canvasId -> init(canvasId));
    }

    public void setCanvasNull(int canvasId) {
        canvasBuffer[canvasId] = null;
        //TODO setCanvasNull GL CLEAR
    }

    public void setImageNull(int imageId) {
        imageBuffer.set(imageId, null);
        //TODO setImageNull GL CLEAR
    }

    public void clearImageList() {
        imageBuffer.clear();
    }

    public void clearCanvasList() {
        for (var i = 0; i < canvasCount; i++) {
            setCanvasNull(i);
        }
        canvasCount = 0;
    }

    public boolean checkCanvasId(Integer canvasId) {
        if (canvasId == null) {
            return false;
        }
        if (canvasId >= 0 && canvasId < canvasCount) {
            return canvasBuffer[canvasId] != null;
        }
        return false;
    }

    public boolean checkImageId(Integer imageId) {
        if (imageId == null) {
            return false;
        }
        if (imageId >= 0 && imageId < imageBuffer.size()) {
            return imageBuffer.get(imageId) != null;
        }
        return false;
    }
}
