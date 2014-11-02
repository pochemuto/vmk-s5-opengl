package com.pochemuto.vmk.opengl.light;

import java.awt.Color;

import com.pochemuto.vmk.opengl.node.BaseNode;

/**
 * @author pochemuto
 */
public abstract class Light extends BaseNode {
    private Color diffuse = Color.BLACK;
    private Color specular = Color.BLACK;
    private Color ambient = Color.BLACK;

    public Color getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Color diffuse) {
        this.diffuse = diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public void setSpecular(Color specular) {
        this.specular = specular;
    }

    public Color getAmbient() {
        return ambient;
    }

    public void setAmbient(Color ambient) {
        this.ambient = ambient;
    }
}
