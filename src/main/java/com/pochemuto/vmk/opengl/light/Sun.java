package com.pochemuto.vmk.opengl.light;

import com.pochemuto.vmk.opengl.core.Mat4;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

/**
 * @author pochemuto
 */
@XmlRootElement
public class Sun extends Light {
    public Sun() {
        setTransform(Mat4.translate(0,0,1));
        setDiffuse(Color.WHITE);
        setSpecular(Color.WHITE);
    }
}
