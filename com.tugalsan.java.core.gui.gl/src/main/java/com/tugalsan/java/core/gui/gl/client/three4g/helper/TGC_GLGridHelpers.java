package com.tugalsan.java.core.gui.gl.client.three4g.helper;

import java.util.*;
import org.treblereel.gwt.three4g.helpers.*;
import org.treblereel.gwt.three4g.math.*;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.gui.gl.client.three4g.scene.*;

public class TGC_GLGridHelpers extends TGC_GLLoadable {

    public TGC_GLGridHelpers(TGC_GLScene parent) {
        super(parent.program);
        this.parent = parent;
    }
    final private TGC_GLScene parent;
    final private List<GridHelper> childeren = TGS_ListUtils.of();

    public void remove(GridHelper gh) {
        childeren.remove(gh);
        parent.scene.remove(gh);
    }

    public GridHelper add(int size, int divisions, Color colorCenterLine, Color colorGrid) {
        var gh = new GridHelper(size, divisions, colorCenterLine, colorGrid);
        childeren.add(gh);
        parent.scene.add(gh);
        return gh;
    }

    public GridHelper add(int size, int divisions) {
        return add(size, divisions, parent.program.color.getRed_FF4444(), parent.program.color.getGrey_404040());
    }

    @Override
    public void lazyLoad() {
//        for (GridHelper gridHelper : childeren) {
//            gridHelper.lazyLoad();
//        }
    }
}
