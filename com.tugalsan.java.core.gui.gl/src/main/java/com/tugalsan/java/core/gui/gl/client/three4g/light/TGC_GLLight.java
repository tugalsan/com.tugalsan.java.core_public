package com.tugalsan.java.core.gui.gl.client.three4g.light;

import com.tugalsan.java.core.gui.gl.client.three4g.TGC_GLProgramAbstract;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.lights.HemisphereLight;
import org.treblereel.gwt.three4g.lights.PointLight;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Vector3;

public class TGC_GLLight {//EXAMPLES OF LIGHT

    TGC_GLProgramAbstract program;

    public TGC_GLLight(TGC_GLProgramAbstract program) {
        this.program = program;
    }

    public PointLight createPointLight_White(Vector3 postion) {
        return createPointLight(program.color.getWhite_0xFFFFFF(), 1, 0, 1, postion);
    }

    public PointLight createPointLight(Color color, float intensity_def1, float distance_nolimit0, float decay_def1, Vector3 position) {
        return createPointLight(color, intensity_def1, distance_nolimit0, decay_def1, position.x, position.y, position.z);
    }

    public PointLight createPointLight(Color color, float intensity_def1, float distance_nolimit0, float decay_def1, float posX, float posY, float posZ) {
        var light = new PointLight(color.getHex(), intensity_def1, distance_nolimit0, decay_def1);
        light.position.set(posX, posY, posZ);
        return light;
    }

    public DirectionalLight createDirectionalLight(Color color, float intensity_def1, Vector3 position) {
        return createDirectionalLight(color, intensity_def1, position.x, position.y, position.z);
    }

    public DirectionalLight createDirectionalLight(Color color, float intensity_def1, float posX, float posY, float posZ) {
        var light = new DirectionalLight(color.getHex(), 1.5f);
        light.position.set(posX, posY, posZ);
        return light;
    }

    public DirectionalLight createDirectionalLight_Example1() {
        return createDirectionalLight(program.color.get_EFEFFF(), 1.5f, -1000, 1000, 0);
    }

    public DirectionalLight createDirectionalLight_Example2() {
        return createDirectionalLight(program.color.get_EFEFFF(), 1.5f, 1000, 1000, 0);
    }

    public HemisphereLight createHemisphereLight(Color colorTop, Color colorBottom, Vector3 position) {
        return createHemisphereLight(colorTop, colorBottom, position.x, position.y, position.z);
    }

    public HemisphereLight createHemisphereLight(Color colorTop, Color colorBottom, float posX, float posY, float posZ) {//sky lamp
        var light = new HemisphereLight(colorTop.getHex(), colorBottom.getHex());
        light.position.set(posX, posY, posZ);
        return light;
    }

    public HemisphereLight createHemisphereLight_Example() {//sky lamp
        return createHemisphereLight(program.color.get_BBBBFF(), program.color.get_444422(), 0, 1, 0);
    }

    public AmbientLight createAmbientLight(Color color) {
        return new AmbientLight(color.getHex());
    }

    public AmbientLight createAmbientLight_404040() {
        return createAmbientLight(program.color.getGrey_404040());
    }
}
