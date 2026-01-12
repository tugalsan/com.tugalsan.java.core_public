package com.tugalsan.java.core.gui.gl.client.three4g.sprite;

import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.TGC_GLTexture2DAbstract;
import org.treblereel.gwt.three4g.materials.SpriteMaterial;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Sprite;
import com.tugalsan.java.core.log.client.TGC_Log;


public class TGC_GLSprite extends TGC_GLLoadable {

    final private static TGC_Log d = TGC_Log.of(TGC_GLSprite.class);

    public TGC_GLSprite(TGC_GLProgramAbstract program, CharSequence name, TGC_GLTexture2DAbstract texture) {
        super(program);
        this.name = name.toString();
        this.texture = texture;
    }
    public String name;
    public TGC_GLTexture2DAbstract texture;

    public boolean isLazyTransformNeeded() {
        return lazyTransformNeeded;
    }
    protected boolean lazyTransformNeeded = false;

    public TGC_GLSprite setLazyPivot(float x, float y, float z) {
        lazyPivot.set(x, y, z);
        lazyTransformNeeded = true;
        return this;
    }
    protected Vector3 lazyPivot = new Vector3();

    public TGC_GLSprite setLazyScale(float x, float y) {
        lazyScale.set(x, y, 1);
        lazyTransformNeeded = true;
        return this;
    }
    protected Vector3 lazyScale = new Vector3();

    public Sprite getSprite() {
        return TGS_FuncMTCUtils.call(() -> {
            if (meshUsedBefore) {
                return (Sprite) sprite.clone();
            } else {
                meshUsedBefore = true;
                return sprite;
            }
        }, e -> {
            cloneError = e.getMessage();
            d.ce("getSprite", "name", name, "cloneError", e);
            return null;
        });
    }
    public Sprite sprite;
    public String cloneError = null;

    public boolean isSpriteUsedBefore() {
        return meshUsedBefore;
    }
    protected boolean meshUsedBefore = false;

    public void setSpriteUsedBefore_false() {
        meshUsedBefore = false;
    }

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_LOADED()) {
            if (lazyTransformNeeded) {
                sprite.position.set(lazyPivot.x, lazyPivot.y, lazyPivot.z);
                sprite.scale.set(lazyScale.x, lazyScale.y, lazyScale.z);
                sprite.updateMatrix();
                lazyTransformNeeded = false;
                d.ci("lazyLoad", "transformed.name", name);
            }
            return;
        }

        if (status != TGC_GLLoadable.STATUS_INIT()) {
            return;
        }

        if (texture != null && texture.status != TGC_GLLoadable.STATUS_LOADED()) {
            texture.lazyLoad();
            return;
        }

        if (!additionalLoadValidation()) {
            return;
        }

        createSprite();
        status = TGC_GLLoadable.STATUS_LOADED();
        d.ci("lazyLoad", "loaded", "name", name);
    }

    protected boolean additionalLoadValidation() {
        return true;
    }

    private TGC_GLSprite createSprite() {
        var spriteMaterial = new SpriteMaterial();
        spriteMaterial.map = texture.texture;
        sprite = new Sprite(spriteMaterial);
        return this;
    }

    public TGC_GLSprite destroy(boolean keepMaterial) {
        if (sprite != null) {
            //sprite.dispose();//HOW?
            if (!keepMaterial) {
                sprite.material.dispose();
            }
        }
        return this;
    }
}
