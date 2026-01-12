package com.tugalsan.java.core.gui.gl.client.three4g.light;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;
import org.treblereel.gwt.three4g.lights.*;

public class TGC_GLLights extends TGC_GLLoadable {

    public TGC_GLLights(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    private final TGC_GLScene parent;
    private final List<Light> childeren = TGS_ListUtils.of();

    public Light get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }

    public void remove(Light l) {
        childeren.remove(l);
        parent.scene.remove(l);
    }

    public Light add(Light light) {
        childeren.add(light);
        parent.scene.add(light);
        return light;
    }

    @Override
    public void lazyLoad() {
//        for (Light light : childeren) {
//            light.lazyLoad();
//        }
    }
}
