package com.pochemuto.vmk.opengl.light;

import com.pochemuto.vmk.opengl.core.Mat4;

/**
 * @author pochemuto
 */
public class Spot extends Light {
    private float constantAttenuation = 1f;
    private float linearAttenuation;
    private float quadraticAttenuation;

    public Spot() {
        setTransform(Mat4.translate(0,0,1));
    }

    public float getConstantAttenuation() {
        return constantAttenuation;
    }

    public void setConstantAttenuation(float constantAttenuation) {
        this.constantAttenuation = constantAttenuation;
    }

    public float getLinearAttenuation() {
        return linearAttenuation;
    }

    public void setLinearAttenuation(float linearAttenuation) {
        this.linearAttenuation = linearAttenuation;
    }

    public float getQuadraticAttenuation() {
        return quadraticAttenuation;
    }

    public void setQuadraticAttenuation(float quadraticAttenuation) {
        this.quadraticAttenuation = quadraticAttenuation;
    }
}
