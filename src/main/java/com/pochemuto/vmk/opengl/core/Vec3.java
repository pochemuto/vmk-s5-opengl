package com.pochemuto.vmk.opengl.core;

/**
 * @author pochemuto
 */
public class Vec3 {
    private final float[] data;

    private Vec3(float[] data) {
        this.data = data;
    }

    public Vec3(float x, float y, float z) {
        data = new float[]{x, y, z};
    }

    public Vec3 add(Vec3 v) {
        float[] r = new float[data.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = data[i] + v.data[i];
        }
        return new Vec3(r);
    }

    public Vec3 sub(Vec3 v) {
        float[] r = new float[data.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = data[i] - v.data[i];
        }
        return new Vec3(r);
    }

    public Vec3 mul(Vec3 vec3) {
        throw new UnsupportedOperationException();
    }

    public float x() {
        return data[0];
    }

    public float y() {
        return data[1];
    }

    public float z() {
        return data[2];
    }
}
