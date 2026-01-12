package com.tugalsan.java.core.opencv.server;

import module org.bytedeco.opencv;
import org.bytedeco.opencv.global.opencv_core;

public class TS_OpenCVImageUtils {

    private TS_OpenCVImageUtils() {

    }

    public static void flip_anti_clockwise(CvArr img) {
        //the grabbed frame will be flipped, re-flip to make it right
        opencv_core.cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
    }

    public static void saveImage(CvArr img, String filename) {
        org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage(filename, img);
    }
}
