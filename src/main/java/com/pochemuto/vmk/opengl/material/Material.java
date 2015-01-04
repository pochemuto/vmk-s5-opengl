package com.pochemuto.vmk.opengl.material;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

/**
 * @author pochemuto
 */
@XmlRootElement
public class Material {
    private Color color = Color.BLACK;

    private Color ambient = new Color(0, 0, 0, 255);

    private Color diffuse = Color.WHITE;

    private Color specular = Color.WHITE;

    private Color emission = new Color(0, 0, 0, 255);

    private float shininess = 100;

    private String texture;

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

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
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

    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }

    public float getShininess() {
        return shininess;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }
}
