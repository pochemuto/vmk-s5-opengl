package com.pochemuto.vmk.opengl.material;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.net.URL;

/**
 * @author pochemuto
 */
@XmlRootElement
public class Material {
    private Color color = Color.BLACK;

    private Color diffuse = Color.WHITE;

    private URL texture;

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

    public URL getTexture() {
        return texture;
    }

    public void setTexture(URL texture) {
        this.texture = texture;
    }
}
