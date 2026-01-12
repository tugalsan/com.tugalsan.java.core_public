package com.tugalsan.java.core.gui.gl.client.three4g.primative;

import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;
import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_3JSPrimatives extends TGC_GLLoadable {

    public TGC_3JSPrimatives(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    private final TGC_GLScene parent;
    private final List<TGC_3JSRefPrimative> childeren = TGS_ListUtils.of();

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }

    public TGC_3JSRefPrimative get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public TGC_3JSRefPrimative add(TGC_3JSRefPrimative child) {
        childeren.add(child);
        parent.scene.add(child.scene);
        return child;
    }

    public TGC_3JSRefPrimative add(CharSequence name, TGC_3JSPrimativeAbstract child) {
        return add(new TGC_3JSRefPrimative(name, child));
    }

    public void remove(TGC_3JSRefPrimative child) {
        childeren.remove(child);
        parent.scene.remove(child.scene);
    }

    @Override
    public void lazyLoad() {
        childeren.stream().forEachOrdered(p -> p.lazyLoad());
    }
}
