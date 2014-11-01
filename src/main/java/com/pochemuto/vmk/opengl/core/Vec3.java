package com.pochemuto.vmk.opengl.core;

import java.util.Arrays;

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

    public Vec3 mul(Vec3 v) {
        // | i  j  k  |
        // | d0 d1 d2 |
        // | v0 v1 v2 |
        float[] r = new float[data.length];

        r[0] = data[1] * v.data[2] - data[2] * v.data[1];
        r[1] = data[2] * v.data[0] - data[0] * v.data[2];
        r[2] = data[0] * v.data[1] - data[1] * v.data[0];
        return new Vec3(r);
    }

    public Vec3 mul(float value) {
        float[] r = Arrays.copyOf(data, data.length);
        for (int i = 0; i < r.length; i++) {
            r[i] *= value;
        }
        return new Vec3(r);
    }

    public float len() {
        float x = data[0], y = data[1], z = data[2];
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vec3 normalize() {
        return this.mul(1 / len());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec3)) return false;

        Vec3 vec3 = (Vec3) o;

        if (!Arrays.equals(data, vec3.data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
