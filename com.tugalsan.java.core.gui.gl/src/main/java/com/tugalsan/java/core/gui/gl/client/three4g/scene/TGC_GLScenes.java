package com.tugalsan.java.core.gui.gl.client.three4g.scene;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_GLScenes extends TGC_GLLoadable {

    public TGC_GLScenes(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    private final TGC_GLScene parent;
    private final List<TGC_GLScene> childeren = TGS_ListUtils.of();

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }

    public TGC_GLScene get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public TGC_GLScene add(TGC_GLScene subScene) {
        childeren.add(subScene);
        parent.scene.add(subScene.scene);
        return subScene;
    }

    public void remove(TGC_GLScene subScene) {
        childeren.remove(subScene);
        parent.scene.remove(subScene.scene);
    }

    @Override
    public void lazyLoad() {
        childeren.stream().forEachOrdered(scene -> scene.lazyLoad());
    }
}
