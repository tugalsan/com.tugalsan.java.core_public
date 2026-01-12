package com.tugalsan.java.core.opencv.server;

import module org.bytedeco.javacv;

public class TS_OpenCVFrameUtils {

    private TS_OpenCVFrameUtils() {

    }

    public static CanvasFrame newCanvasFrame(String name) {
        return new CanvasFrame(name);
    }

    public static OpenCVFrameGrabber newFrameGrabber(int webCamOrder) {
        return new OpenCVFrameGrabber(webCamOrder); // 1 for next camera
    }

    public static OpenCVFrameGrabber newFrameGrabber_getFirst() {
        return newFrameGrabber(0);
    }

    public static OpenCVFrameConverter.ToIplImage newFrameConverter() {
        return new OpenCVFrameConverter.ToIplImage();
    }
}
