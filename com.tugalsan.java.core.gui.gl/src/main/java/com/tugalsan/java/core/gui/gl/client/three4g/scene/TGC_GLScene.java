package com.tugalsan.java.core.gui.gl.client.three4g.scene;

import com.tugalsan.java.core.gui.gl.client.three4g.helper.TGC_GLGridHelpers;
import com.tugalsan.java.core.gui.gl.client.three4g.light.TGC_GLLights;
import com.tugalsan.java.core.gui.gl.client.three4g.sprite.TGC_GLSprites;
import com.tugalsan.java.core.gui.gl.client.three4g.model.TGC_GLModels;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.primative.TGC_3JSPrimatives;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.scenes.Fog;
import org.treblereel.gwt.three4g.scenes.Scene;

public class TGC_GLScene extends TGC_GLLoadable {

//    final private static TGC_Log d = TGC_Log.of(TGC_GLScene.class);

    public TGC_GLScene(TGC_GLProgramAbstract program, CharSequence name) {
        super(program);
        this.name = name.toString();
        scene = new Scene();
        childScenes = new TGC_GLScenes(this);
        childLights = new TGC_GLLights(this);
        childModels = new TGC_GLModels(this);
        childSprites = new TGC_GLSprites(this);
        childPrimatives = new TGC_3JSPrimatives(this);
        childGridHelpers = new TGC_GLGridHelpers(this);
    }
    public String name = null;
    public Scene scene;

    public TGC_GLScenes childScenes;
    public TGC_GLLights childLights;
    public TGC_GLModels childModels;
    public TGC_GLSprites childSprites;
    public TGC_3JSPrimatives childPrimatives;
    public TGC_GLGridHelpers childGridHelpers;

    @Override
    public void lazyLoad() {
        childScenes.lazyLoad();
        childLights.lazyLoad();
        childModels.lazyLoad();
        childSprites.lazyLoad();
        childPrimatives.lazyLoad();
        childGridHelpers.lazyLoad();
    }

    public void setFog(Color color, float min, float max) {
        if (color == null) {
            scene.fog = null;
        } else {
            scene.fog = new Fog(color.getHex(), min, max);
        }
    }
}
