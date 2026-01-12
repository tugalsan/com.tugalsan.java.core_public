package com.tugalsan.java.core.gui.gl.client.three4g.common;

import com.google.gwt.animation.client.AnimationScheduler;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.core.Clock;

public class TGC_GLRenderAnimator {//ANIMATE FUNCTION CONTROLLER 

//    final private static TGC_Log d = TGC_Log.of(TGC_GLRenderAnimator.class);

    final public TGC_GLProgramAbstract program;
    final private AnimationScheduler.AnimationCallback callback;

    public float getClockDelta() {
        return clockDelta;
    }
    public double timestampCurrent = 0f;

    public double getTimestampCurrent() {
        return timestampCurrent;
    }
    public float clockDelta = 0f;
    final private Clock clock = new Clock();

    public int getMaxFPS() {
        return maxFPS;
    }
    private int maxFPS = 5;

    public void setMaxFPS(int maxFPS) {
        this.maxFPS = maxFPS;
        this.minMS = 1000f / maxFPS;
        setAnimate(true);
    }

    public float getMinMS() {
        return minMS;
    }
    private float minMS = 1000f / maxFPS;

    public TGC_GLRenderAnimator(TGC_GLProgramAbstract program) {
        this.program = program;
        this.callback = new AnimationScheduler.AnimationCallback() {
            
            
            @Override
            public void execute(double timestamp_current) {
                if (minMS < timestamp_current - timestamp_previous) {//FPS CONTROL
                    if (timestamp_start == 0) {//OFFSET CONTROL
                        timestamp_start = timestamp_current;
                    }
                    {//RENDER
                        clockDelta = clock.getDelta();
                        program.lazyUpdate();
                        timestampCurrent = timestamp_current - timestamp_start;
                        program.animate(clockDelta, timestampCurrent, maxFPS, minMS);
                        program.renderer.renderer.clear();
                        program.renderer.renderer.render(program.sceneWorld.scene, program.camera.camera);
                        program.renderer.renderer.clearDepth();
                        program.post_animate(clockDelta, timestampCurrent, maxFPS, minMS);
                        program.renderer.renderer.render(program.sceneWorld.scene, program.camera.camera);
                    }
                    timestamp_previous = timestamp_current;
                }
                if (animate) {
                    AnimationScheduler.get().requestAnimationFrame(callback);
                }
            }
        };
    }
    private double timestamp_previous;
    private double timestamp_start;

    public void resetTimestamp() {
        timestamp_start = 0;
    }

    private boolean animate = false;

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
        if (animate) {
            AnimationScheduler.get().requestAnimationFrame(callback);
        }
    }
}
