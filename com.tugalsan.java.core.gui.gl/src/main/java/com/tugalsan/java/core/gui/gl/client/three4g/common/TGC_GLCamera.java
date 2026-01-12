package com.tugalsan.java.core.gui.gl.client.three4g.common;

import org.treblereel.gwt.three4g.extensions.resources.js.TGC_GLResourceUtils;
import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.extensions.controls.FirstPersonControls;
import org.treblereel.gwt.three4g.extensions.controls.FlyControls;
import org.treblereel.gwt.three4g.extensions.controls.OrbitControls;
import org.treblereel.gwt.three4g.extensions.controls.TrackballControls;

public class TGC_GLCamera {//FOV CAMERA

    final public TGC_GLProgramAbstract program;
    public PerspectiveCamera camera;

    public OrbitControls orbitControls;
    public FirstPersonControls firstPersonControls;
    public TrackballControls trackballControls;
    public FlyControls flyControls;

    public TGC_GLCamera(TGC_GLProgramAbstract program, int fov, float zNear, float zFar) {
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getOrbitControls());
        TGC_GLResourceUtils.addLib(TGC_GLResourceUtils.INSTANCE.getTrackballControls());
        this.program = program;
        camera = new PerspectiveCamera(fov, program.canvas.calculateAspect(), zNear, zFar);
        camera.updateProjectionMatrix();
    }

    public void updateAspect() {
        camera.aspect = program.canvas.calculateAspect();
        camera.updateProjectionMatrix();
    }

    public void updatePosition(float x, float y, float z) {
        camera.position.set(x, y, z);
        camera.updateProjectionMatrix();
    }

    public void compileMatrix() {
        camera.updateProjectionMatrix();
    }

    public void addOrbitControls() {
        orbitControls = new OrbitControls(camera, program.renderer.renderer.domElement);
        orbitControls.target.set(0, 0.5f, 0);
        orbitControls.enablePan = true;
        orbitControls.enableZoom = true;
        orbitControls.maxPolarAngle = program.math.getRadians(45);
    }

    public void addOTrackballControls() {
        trackballControls = new TrackballControls(camera);
    }
}
