package com.pochemuto.vmk.opengl.material;

import java.awt.Color;

/**
 * @author pochemuto
 */
public class Material {
    private Color color = Color.BLACK;

    private Color diffuse = Color.WHITE;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Color diffuse) {
        this.diffuse = diffuse;
    }
}
