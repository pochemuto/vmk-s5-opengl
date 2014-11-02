package com.pochemuto.vmk.opengl.light;

import java.awt.Color;

import com.pochemuto.vmk.opengl.core.Mat4;

/**
 * @author pochemuto
 */
public class Sun extends Light {
    public Sun() {
        setTransform(Mat4.translate(0,0,1));
        setDiffuse(Color.WHITE);
        setSpecular(Color.WHITE);
    }
}
