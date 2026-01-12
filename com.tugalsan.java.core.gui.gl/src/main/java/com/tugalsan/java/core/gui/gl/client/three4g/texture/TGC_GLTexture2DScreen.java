package com.tugalsan.java.core.gui.gl.client.three4g.texture;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.shape.client.TGS_ShapeLocation;
import elemental2.core.Uint8Array;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.math.Vector2;
import org.treblereel.gwt.three4g.textures.DataTexture;

public class TGC_GLTexture2DScreen extends TGC_GLTexture2DAbstract {

    private static int DEF_TEXTURE_SIZE() {
        return 128;
    }

    public TGC_GLTexture2DScreen(TGC_GLProgramAbstract program) {
        this(program, DEF_TEXTURE_SIZE());
    }

    public TGC_GLTexture2DScreen(TGC_GLProgramAbstract program, int textureSize) {
        this(program, textureSize, null);
    }

    public TGC_GLTexture2DScreen(TGC_GLProgramAbstract program, int textureSize, TGS_ShapeLocation<Float> location) {
        super(program);
        this.textureSize = textureSize;
        this.pixelRatio = program.window.getPixelRatio();
        this.pixelSize = (int) (textureSize * pixelRatio);
        var data = new Uint8Array(pixelSize * pixelSize * 3);
        texture = new DataTexture(data, pixelSize, pixelSize, THREE.RGBFormat);
        texture.minFilter = THREE.NearestFilter;
        texture.magFilter = THREE.NearestFilter;
        texture.needsUpdate = true;
        if (location == null) {
            location = new TGS_ShapeLocation(
                    program.window.getWidth() - textureSize,
                    program.window.getHeight() - textureSize
            );
        }
        this.location = location;
        this.location_inPixels = new Vector2(location.x * pixelRatio, location.y * pixelRatio);
    }
    private float pixelRatio;

    public int getSize() {
        return textureSize;
    }
    private int textureSize, pixelSize;

    public TGS_ShapeLocation getLocation() {
        return location;
    }
    public TGS_ShapeLocation location;
    public Vector2 location_inPixels;

    public void post_animate() {
        program.renderer.renderer.copyFramebufferToTexture(locStartPixel_Ratioed, texture);
        if (status != STATUS_LOADED()) {
            status = STATUS_LOADED();
        }
    }
    final private Vector2 locStartPixel_Ratioed = new Vector2();

    @Override
    public void lazyLoad() {
    }
}
