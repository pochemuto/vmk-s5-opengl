package com.pochemuto.vmk.opengl.light;

import com.pochemuto.vmk.opengl.core.Vec3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pochemuto
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Spot extends PointLight {
    public Vec3 direction = new Vec3(0,0,-1);
    public float cutoff = 180.0f;
    public float exponent;

    public Vec3 getDirection() {
        return direction;
    }

    public void setDirection(Vec3 direction) {
        this.direction = direction;
    }

    public float getCutoff() {
        return cutoff;
    }

    public void setCutoff(float cutoff) {
        this.cutoff = cutoff;
    }

    public float getExponent() {
        return exponent;
    }

    public void setExponent(float exponent) {
        this.exponent = exponent;
    }

    public static void main(String... args) {
        Thread t = new Thread();
        t.interrupt();
    }
}