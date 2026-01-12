package com.tugalsan.java.core.gui.gl.client.three4g.sprite;

import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLRefAbstract;
import org.treblereel.gwt.three4g.objects.Sprite;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_GLRefSprite extends TGC_GLRefAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_GLRefSprite.class);

    public TGC_GLRefSprite(CharSequence name, TGC_GLSprite sprite) {
        super(sprite.program, name);
        this.sprite = sprite;
    }
    public TGC_GLSprite sprite;
    public Sprite childSprite;

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_INIT()) {
            if (sprite.status == TGC_GLLoadable.STATUS_LOADED() && !sprite.isLazyTransformNeeded()) {
                childSprite = sprite.getSprite();
                sprite.sprite.matrixAutoUpdate = false;
                childSprite.matrixAutoUpdate = false;
                if (childSprite == null) {
                    errorMessage = sprite.cloneError;
                    status = TGC_GLLoadable.STATUS_ERROR();
                    d.ci( "lazyLoad", "childSprite == null", "name", name, "errorMessage", errorMessage);
                } else {
                    scene.add(childSprite);
                    status = TGC_GLLoadable.STATUS_LOADED();
                    d.ci( "lazyLoad", "loaded", "name", name);
                }
            }
        }
        sprite.lazyLoad();
    }

    public void destroy() {
        if (childSprite != null) {
            if (sprite.sprite.equals(childSprite)) {
                sprite.setSpriteUsedBefore_false();
            }
            scene.remove(childSprite);
            childSprite = null;
        }
    }
}
