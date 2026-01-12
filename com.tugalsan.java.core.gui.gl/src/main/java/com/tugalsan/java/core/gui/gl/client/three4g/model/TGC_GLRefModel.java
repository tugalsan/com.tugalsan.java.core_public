package com.tugalsan.java.core.gui.gl.client.three4g.model;

import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLLoadable;
import com.tugalsan.java.core.gui.gl.client.three4g.common.TGC_GLRefAbstract;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.scenes.Scene;
import com.tugalsan.java.core.log.client.TGC_Log;

public class TGC_GLRefModel extends TGC_GLRefAbstract {

    final private static TGC_Log d = TGC_Log.of(TGC_GLRefModel.class);

    public TGC_GLRefModel(CharSequence name, TGC_GLModel model) {
        super(model.program, name);
        this.model = model;
    }
    public AnimationMixer animationMixer = null;
    public AnimationClip[] animationClips;
    public TGC_GLModel model;
    public Scene childScene;

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_INIT()) {
            if (model.status == TGC_GLLoadable.STATUS_LOADED() && !model.isLazyTransformNeeded()) {
                childScene = model.getScene();
                model.scene.matrixAutoUpdate = false;
                childScene.matrixAutoUpdate = false;

                animationClips = model.getAnimationClips(childScene);
                if (childScene == null) {
                    errorMessage = model.cloneError;
                    status = TGC_GLLoadable.STATUS_ERROR();
                    d.ce("lazyLoad", "name", name, "childScene == null", errorMessage);
                } else {
                    scene.add(childScene);
                    status = TGC_GLLoadable.STATUS_LOADED();
                    d.ci("lazyLoad", "name", name, "LOADED");
                }
            }
        } else if (status == TGC_GLLoadable.STATUS_LOADED()) {
            if (animationClips != null) {
                if (selectedAnimationClipIndex != lazyAnimationClipIndex) {
                    if (lazyAnimationClipIndex > -1 && lazyAnimationClipIndex < animationClips.length) {
                        selectedAnimationClipIndex = lazyAnimationClipIndex;
                        if (animationMixer == null) {
                            animationMixer = new AnimationMixer(childScene);
                        }
                        animationMixer.clipAction(animationClips[selectedAnimationClipIndex]).play();
                        d.ci("lazyLoad", "selectedAnimationClipIndex", selectedAnimationClipIndex);
                    } else {
                        selectedAnimationClipIndex = -1;
                    }
                }
                if (selectedAnimationClipIndex != -1) {
                    animationMixer.update(program.renderer.animator.clockDelta);
                }
            }
            extraAnimations.animate();
        }
        model.lazyLoad();
    }
    private int selectedAnimationClipIndex = -1;

    public TGC_GLRefModel setLazyAnimationClip(int idx) {
        lazyAnimationClipIndex = idx;
        return this;
    }
    public int lazyAnimationClipIndex = -1;

    public void destroy() {
        if (childScene != null) {
            if (model.scene.equals(childScene)) {
                model.setSceneUsedBefore_false();
            }
            scene.remove(childScene);
            childScene = null;
            animationMixer = null;
            animationClips = null;
        }
    }
}
