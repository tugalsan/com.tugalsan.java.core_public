package com.tugalsan.java.core.gui.gl.client.three4g.model;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_GLModels extends TGC_GLLoadable {

//    final private static TGC_Log d = TGC_Log.of(TGC_GLScene.class);

    public TGC_GLModels(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    private final TGC_GLScene parent;
    private final List<TGC_GLRefModel> childeren = TGS_ListUtils.of();

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }
    
    public TGC_GLRefModel get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public TGC_GLRefModel add(TGC_GLRefModel subModel) {
        childeren.add(subModel);
        parent.scene.add(subModel.scene);
        return subModel;
    }

    public TGC_GLRefModel add(String name, TGC_GLModel subModel) {
        return add(new TGC_GLRefModel(name, subModel));
    }

    public void remove(TGC_GLRefModel subModel) {
        childeren.remove(subModel);
        parent.scene.remove(subModel.scene);
    }

    @Override
    public void lazyLoad() {
        childeren.stream().forEachOrdered(model -> model.lazyLoad());
    }
}
