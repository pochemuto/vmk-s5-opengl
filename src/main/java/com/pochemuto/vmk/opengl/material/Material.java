package com.pochemuto.vmk.opengl.material;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

/**
 * @author pochemuto
 */
@XmlRootElement
public class Material {
    private Color color = Color.BLACK;

    private Color diffuse = Color.WHITE;

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
}
