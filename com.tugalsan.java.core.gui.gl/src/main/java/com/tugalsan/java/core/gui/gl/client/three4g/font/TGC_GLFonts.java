package com.tugalsan.java.core.gui.gl.client.three4g.font;

import com.tugalsan.java.core.gui.gl.client.three4g.*;
import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_GLFonts extends TGC_GLLoadable {

    final private static boolean PARALLEL = true; //may cause unexpected exception: java.lang.OutOfMemoryError: Java heap space

    public TGC_GLFonts(TGC_GLProgramAbstract program, CharSequence... fontUrls) {
        super(program);
        TGS_ListUtils.of(fontUrls).forEach(fontUrl -> add(fontUrl.toString()));
    }
    private final List<TGC_GLFont> childeren = TGS_ListUtils.of();

    public TGC_GLFont get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public boolean isEmpty() {
        return childeren.isEmpty();
    }

    public void clear() {
        IntStream.range(0, size()).forEachOrdered(i -> remove(get(0)));
    }

    public void remove(TGC_GLFont l) {
        childeren.remove(l);
    }

    final public TGC_GLFont add(CharSequence url) {
        for (var font : childeren) {
            if (font.url.equals(url.toString())) {
                return font;
            }
        }
        var font = new TGC_GLFont(program, url);
        childeren.add(font);
        return font;
    }

    final public List<TGC_GLFont> add(CharSequence... urls) {
        List fonts = TGS_ListUtils.of();
        Arrays.stream(urls).forEachOrdered(url -> fonts.add(add(url)));
        return fonts;
    }

    final public List<TGC_GLFont> add(List<String> urls) {
        List fonts = TGS_ListUtils.of();
        urls.stream().forEachOrdered(url -> fonts.add(add(url)));
        return fonts;
    }

    @Override
    public void lazyLoad() {
        (PARALLEL ? childeren.parallelStream() : childeren.stream()).forEach(font -> font.lazyLoad());
    }
}
