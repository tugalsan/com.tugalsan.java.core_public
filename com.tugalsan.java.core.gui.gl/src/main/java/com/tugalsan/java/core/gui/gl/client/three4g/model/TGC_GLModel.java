package com.tugalsan.java.core.gui.gl.client.three4g.model;

import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import org.treblereel.gwt.three4g.extensions.resources.js.TGC_GLResourceUtils;
import java.util.Objects;
import java.util.stream.IntStream;
import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.texture.*;
import jsinterop.base.Js;
import org.treblereel.gwt.three4g.animation.*;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.TraverseCallback;
import org.treblereel.gwt.three4g.extensions.loaders.DRACOLoader;
import org.treblereel.gwt.three4g.extensions.loaders.GLTFLoader;
import org.treblereel.gwt.three4g.extensions.utils.SkeletonUtils;
import org.treblereel.gwt.three4g.loaders.OnErrorCallback;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.loaders.OnProgressCallback;
import org.treblereel.gwt.three4g.materials.MeshStandardMaterial;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.scenes.Scene;
import com.tugalsan.java.core.log.client.TGC_Log;


public class TGC_GLModel extends TGC_GLLoadable {

    final private static TGC_Log d = TGC_Log.of(TGC_GLModel.class);

    public static String DracoLoaderDecoderPath = null;

    private static boolean FIX_SCENE_CLONNING() {
        return true;
    }

    public static boolean FIX_ON_LOAD_Object3D() {
        return true;
    }

    public AnimationClip[] animationClips = null;
    public Scene scene = null;
    public TGC_GLTexture2DAbstract texture;
    public TGC_GLTextureCube environment;
    public String url;

    public boolean isDracoMode() {
        return dracoMode;
    }
    private boolean dracoMode = false;

    public TGC_GLModel(TGC_GLProgramAbstract program, CharSequence url) {
        this(program, url, null, null);
    }

    public TGC_GLModel(TGC_GLProgramAbstract program, CharSequence url, TGC_GLTexture2DAbstract texture) {
        this(program, url, texture, null);
    }

    public TGC_GLModel(TGC_GLProgramAbstract program, CharSequence url, TGC_GLTextureCube environment) {
        this(program, url, null, environment);
    }

    public TGC_GLModel(TGC_GLProgramAbstract program, CharSequence url, TGC_GLTexture2DAbstract texture, TGC_GLTextureCube environment) {
        super(program);
        d.ci("url", url);
        this.url = url.toString();
        this.texture = texture;
        this.environment = environment;
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getSkeletonUtils());
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getGLTFLoader());
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getDRACOLoader());
//        if (!TGC_GLResourceUtils.isLibAdded("getDRACOLoader")) {
//            TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getDRACOLoader());
//            var dlc = new DRACOLoaderDecoderConfig();
//            dlc.type = "js";
//            DRACOLoader.setDecoderConfig(dlc);
//        }
    }

    public boolean isLazyTransformNeeded() {
        return lazyTransformNeeded;
    }
    private boolean lazyTransformNeeded = false;

    public void setLazyPivot(float x, float y, float z) {
        lazyPivot.set(x, y, z);
        lazyTransformNeeded = true;
    }
    final private Vector3 lazyPivot = new Vector3();

    public void setLazyScale(float x, float y, float z) {
        lazyScale.set(x, y, z);
        lazyTransformNeeded = true;
    }
    final private Vector3 lazyScale = new Vector3();

    public AnimationClip[] getAnimationClips(Scene scene) {
        if (animationClips == null || animationClips.length == 0) {
            return null;
        }
        if (scene == this.scene) {
            return animationClips;
        } else {
            var newClips = new AnimationClip[animationClips.length];
            IntStream.range(0, animationClips.length).parallel().forEach(newClips_i -> {
                var oldAc = animationClips[newClips_i];
                var newKfts = new KeyframeTrack[oldAc.tracks.length];
                IntStream.range(0, oldAc.tracks.length).parallel().forEach(newKfts_i -> {
                    var oldKft = oldAc.tracks[newKfts_i];
                    newKfts[newKfts_i] = new KeyframeTrack(String.valueOf(oldKft.name), oldKft.times, oldKft.values, oldKft.getInterpolation());
                });
                newClips[newClips_i] = new AnimationClip(String.valueOf(oldAc.name), oldAc.duration, newKfts);
            });
            return newClips;
        }
    }

    public Scene getScene() {
        return TGS_FuncMTCUtils.call(() -> {
            if (sceneUsedBefore) {
                if (FIX_SCENE_CLONNING()) {
                    return Js.uncheckedCast(SkeletonUtils.clone(scene));
                } else {
                    return (Scene) scene.clone();
                }
            } else {
                sceneUsedBefore = true;
                return scene;
            }
        }, e -> {
            cloneError = e.getMessage();
            d.ce("getScene", "EXCEPTION", e);
            return null;
        });
    }
    public String cloneError = null;

    public boolean isSceneUsedBefore() {
        return sceneUsedBefore;
    }
    private boolean sceneUsedBefore = false;

    public void setSceneUsedBefore_false() {
        sceneUsedBefore = false;
    }

    @Override
    public void lazyLoad() {
        if (status == TGC_GLLoadable.STATUS_LOADED()) {
            if (lazyTransformNeeded) {
                scene.position.set(lazyPivot.x, lazyPivot.y, lazyPivot.z);
                scene.scale.set(lazyScale.x, lazyScale.y, lazyScale.z);
                scene.updateMatrix();
                lazyTransformNeeded = false;
                d.ci("lazyLoad", "transformed.url", url);
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

        if (environment != null && environment.status != TGC_GLLoadable.STATUS_LOADED()) {
            environment.lazyLoad();
            return;
        }

        TraverseCallback tcbTexture = o3d -> {
            if (o3d instanceof Mesh) {
                MeshStandardMaterial material;
                if (FIX_ON_LOAD_Object3D()) {
                    var oMaterial = Js.asPropertyMap(o3d).get("material");
                    material = Js.uncheckedCast(oMaterial);
                } else {
                    material = o3d.getProperty("material");
                }
                if (material != null) {
                    if (environment != null) {
                        material.envMap = environment.textureCube;
                    }
                    if (texture != null) {
                        material.map = texture.texture;
                    }
                }
            }
        };
        OnLoadCallback olc = (Object o) -> {
            d.ci("lazyLoad", "onLoad", "url", url);
            Object oScene = null;
            if (o instanceof Object3D) {
                var o3d = (Object3D) o;
                oScene = o3d.getProperty("scene");
            } else if (FIX_ON_LOAD_Object3D()) {
                d.ci("lazyLoad", "onLoad", "url", url, "FIX_ON_LOAD_Object3D");
                oScene = Js.asPropertyMap(o).get("scene");
            }
            if (oScene == null) {
                errorMessage = "Error: " + TGC_GLModel.class.getSimpleName() + ".olc.oScene = null";
                status = TGC_GLLoadable.STATUS_ERROR();
                return;
            }
            {
                Scene sniffedScene = Js.uncheckedCast(oScene);
                if (sniffedScene.isScene) {
                    d.ci("lazyLoad", "onLoad", "url", url, "sniffedScene.isScene detected");
                    scene = sniffedScene;
                }
            }
            if (scene == null) {
                errorMessage = "Error: " + TGC_GLModel.class.getSimpleName() + ".olc.model_scene.isScene = false";
                status = TGC_GLLoadable.STATUS_ERROR();
            } else {
                if (o instanceof Object3D) {
                    var o3d = (Object3D) o;
                    animationClips = o3d.getProperty("animations");
                    if (animationClips != null) {
                        d.ci("lazyLoad", "onLoad", "url", url, "animationClips.length", animationClips.length);
                    }
                } else if (FIX_ON_LOAD_Object3D()) {
                    var oAnimations = Js.asPropertyMap(o).get("animations");
                    if (oAnimations != null) {
                        animationClips = Js.uncheckedCast(oAnimations);
                        d.ci("lazyLoad", "onLoad", "url", url, "FIX_ON_LOAD_Object3D", "animationClips.length", animationClips.length);
                    }
                }

                if (texture != null || environment != null) {
                    scene.traverse(tcbTexture);
                }
                status = TGC_GLLoadable.STATUS_LOADED();
                d.ci("lazyLoad", "LOADED", "dracoMode:" + dracoMode, "url", url);
            }
        };
        OnProgressCallback opc = ope -> {
            if (ope.lengthComputable) {
                progress = 100 * ope.loaded / ope.total;
                d.ci("lazyLoad", "onProgress", "url", url, ope.loaded, ope.total);
            }
        };
        OnErrorCallback oec = e -> {
            var msg = e.toString();
            if (dracoMode == false && Objects.equals("Error: THREE.GLTFLoader: No DRACOLoader instance provided.", msg)) {
                d.ci("lazyLoad", "onError", "switching2DracoMode", "url", url);
                dracoMode = true;
                status = TGC_GLLoadable.STATUS_INIT();
                lazyLoad();
            } else {
                errorMessage = msg;
                d.ce("lazyLoad", "onError", "url", url, errorMessage);
                status = TGC_GLLoadable.STATUS_ERROR();
            }
        };
        var gltfLoader = new GLTFLoader();
        if (dracoMode) {
            if (DracoLoaderDecoderPath == null) {
                d.ce("lazyLoad", "DracoLoaderDecoderPath == null");
            } else {
                var dracoLoader = new DRACOLoader();
                dracoLoader.setDecoderPath(DracoLoaderDecoderPath);
                gltfLoader.setDRACOLoader(dracoLoader);
            }
        }
        gltfLoader.load(url, olc, opc, oec);
        status = TGC_GLLoadable.STATUS_LOADING();
        d.ci("lazyLoad", "LOADING", "url", url);
    }

    public void destroy(boolean keepMaterial) {
        if (scene != null) {
            TraverseCallback tcbTexture = o3d -> {
                if (o3d instanceof Mesh) {
                    if (keepMaterial) {
                        MeshStandardMaterial material;
                        if (FIX_ON_LOAD_Object3D()) {
                            var oMaterial = Js.asPropertyMap(o3d).get("material");
                            material = Js.uncheckedCast(oMaterial);
                        } else {
                            material = o3d.getProperty("material");
                        }
                        if (material != null) {
                            if (environment != null) {
                                material.envMap = null;
                            }
                            if (texture != null) {
                                material.map = null;
                            }
                        }
                    }
                    scene.dispose();
                    scene = null;
                }
            };
            scene.traverse(tcbTexture);
        }
    }
}
