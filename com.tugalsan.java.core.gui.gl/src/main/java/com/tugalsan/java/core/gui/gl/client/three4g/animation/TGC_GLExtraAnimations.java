package com.tugalsan.java.core.gui.gl.client.three4g.animation;

import com.tugalsan.java.core.gui.gl.client.three4g.common.*;
import com.tugalsan.java.core.list.client.*;
import java.util.*;

public class TGC_GLExtraAnimations {

    public TGC_GLExtraAnimations(TGC_GLRefAbstract refScene) {
        this.refScene = refScene;
    }
    private final TGC_GLRefAbstract refScene;

    private final List<TGC_GLExtraAnimationAbstract> childeren = TGS_ListUtils.of();

    public TGC_GLExtraAnimations add(TGC_GLExtraAnimationAbstract child) {
        childeren.add(child);
        return this;
    }

    public TGC_GLExtraAnimationAbstract get(int i) {
        return childeren.get(i);
    }

    public int size() {
        return childeren.size();
    }

    public TGC_GLExtraAnimationAbstract getAnimation() {
        return chosenIndex == -1 ? null : childeren.get(chosenIndex);
    }
    private int chosenIndex = -1;

    public boolean play(int chosenIndex) {
        if (-1 < chosenIndex && chosenIndex < childeren.size()) {
            this.chosenIndex = chosenIndex;
            return true;
        }
        return false;
    }

    public boolean playLast() {
        return play(childeren.size() - 1);
    }

    public void stop() {
        chosenIndex = -1;
    }

    public void animate() {
        if (chosenIndex != -1) {
            childeren.get(chosenIndex).animate(refScene.program, refScene.scene);
        }
    }
}
