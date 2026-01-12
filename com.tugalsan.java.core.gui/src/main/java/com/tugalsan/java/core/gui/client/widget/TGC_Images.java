package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.ui.Image;
import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.thread.client.TGC_ThreadUtils;
import java.util.List;
import java.util.stream.IntStream;

public class TGC_Images {

    final public List<Image> images = TGS_ListUtils.of();
    private int currentIdx = 0;

    public TGC_Images show(int idx) {
        if (images.isEmpty() || idx < 0 || idx >= images.size()) {
            return this;
        }
        TGC_ThreadUtils.run_afterSeconds_afterGUIUpdate(t -> {
            IntStream.range(0, images.size()).parallel().forEach(i -> images.get(i).setVisible(i == idx));
        }, 0.1f);
        return this;
    }

    public TGC_Images showNext() {
        currentIdx++;
        if (currentIdx >= images.size()) {
            currentIdx = 0;
        }
        show(currentIdx);
        return this;
    }

    public TGC_Images showFirst() {
        show(0);
        return this;
    }

    public TGC_Images showLast() {
        show(images.size() - 1);
        return this;
    }
}
