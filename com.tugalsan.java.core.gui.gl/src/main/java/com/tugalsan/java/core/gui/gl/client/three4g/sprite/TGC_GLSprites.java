package com.tugalsan.java.core.gui.gl.client.three4g.sprite;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_GLSprites extends TGC_GLLoadable {

    public TGC_GLSprites(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    final private TGC_GLScene parent;
    private final List<TGC_GLRefSprite> childeren = TGS_ListUtils.of();

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }

    public TGC_GLRefSprite get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public TGC_GLRefSprite add(TGC_GLRefSprite subSprite) {
        childeren.add(subSprite);
        parent.scene.add(subSprite.childSprite);
        return subSprite;
    }

    public TGC_GLRefSprite add(CharSequence name, TGC_GLSprite subSprite) {
        return add(new TGC_GLRefSprite(name, subSprite));
    }

    public void remove(TGC_GLRefSprite subSprite) {
        childeren.remove(subSprite);
        parent.scene.remove(subSprite.childSprite);
    }

    @Override
    public void lazyLoad() {
        childeren.stream().forEachOrdered(sprite -> sprite.lazyLoad());
    }
}
