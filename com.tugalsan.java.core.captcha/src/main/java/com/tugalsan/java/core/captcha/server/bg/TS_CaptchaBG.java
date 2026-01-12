package com.tugalsan.java.core.captcha.server.bg;

import module java.desktop;

/**
 * 
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 * 
 */
public interface TS_CaptchaBG {

    /**
     * Add the background to the given image.
     * 
     * @param image
     * @return
     */
    public BufferedImage addBackground(BufferedImage image);
    
    public BufferedImage getBackground(int width, int height);
}
