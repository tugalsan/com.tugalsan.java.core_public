package com.tugalsan.java.core.captcha.server.bg;

import module java.desktop;

/**
 * Generates a transparent background.
 *
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 *
 */
public class TS_CaptchaBGTransparent implements TS_CaptchaBG {

    @Override
    public BufferedImage addBackground(BufferedImage image) {
        return getBackground(image.getWidth(), image.getHeight());
    }

    @Override
    public BufferedImage getBackground(int width, int height) {
        var bg = new BufferedImage(width, height, Transparency.TRANSLUCENT);
        var g = bg.createGraphics();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        g.fillRect(0, 0, width, height);

        return bg;
    }

}
