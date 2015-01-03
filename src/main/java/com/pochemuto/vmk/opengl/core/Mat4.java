package com.pochemuto.vmk.opengl.core;

import com.pochemuto.vmk.opengl.core.xml.Mat4Adapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Arrays;

/**
 * @author pochemuto
 */
@XmlJavaTypeAdapter(Mat4Adapter.class)
public class Mat4 {

    @XmlElement
    private final float[] data;

    public Mat4() {
        data = new float[16];
        data[0] = 1;
        data[5] = 1;
        data[10] = 1;
        data[15] = 1;
    }

    public Mat4(float[] data) {
        if (data.length != 16) {
            throw new IllegalArgumentException("количество значений в матрице не равно 16");
        }
        this.data = data;
    }

    public float[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public static final Mat4 IDENTITY = new Mat4();

    public static Mat4 translate(float x, float y, float z) {
        Mat4 result = new Mat4();
        float[] data = result.data;
        data[12] = x;
        data[13] = y;
        data[14] = z;
        return result;
    }

    public static Mat4 scale(float x, float y, float z) {
        Mat4 result = new Mat4();
        float[] data = result.data;
        data[0] = x;
        data[5] = y;
        data[10] = z;
        return result;
    }

    public static Mat4 quaternion(float w, float x, float y, float z) {
        float n = w * w + x * x + y * y + z * z;
        float s = n == 0 ? 0 : 2 / n;
        float wx = s * w * x, wy = s * w * y, wz = s * w * z,
                xx = s * x * x, xy = s * x * y, xz = s * x * z,
                yy = s * y * y, yz = s * y * z, zz = s * z * z;

        Mat4 result = new Mat4();
        float[] data = result.data;

        data[0] = 1 - (yy + zz);
        data[1] = xy + wz;
        data[2] = xz - wy;
        data[4] = xy - wz;
        data[5] = 1 - (xx + zz);
        data[6] = yz + wx;
        data[8] = xz + wy;
        data[9] = yz - wx;
        data[10] = 1 - (xx + yy);
        return result;
    }

    public static Mat4 rotate(float angle, float x, float y, float z) {
        angle = (float) Math.toRadians(angle);
        Mat4 result = new Mat4();
        float[] data = result.data;
        float cosa = (float) Math.cos(angle), sina = (float) Math.sin(angle);

        data[0] = cosa + (1 - cosa) * x * x;
        data[1] = (1 - cosa) * y * x + sina * z;
        data[2] = (1 - cosa) * z * x - sina * y;
        data[4] = (1 - cosa) * x * y - sina * z;
        data[5] = cosa + (1 - cosa) * y * y;
        data[6] = (1 - cosa) * z * y + sina * x;
        data[8] = (1 - cosa) * x * y + sina * y;
        data[9] = (1 - cosa) * y * z - sina * x;
        data[10] = cosa + (1 - cosa) * z * z;

        data[0] = cosa + (1 - cosa) * x * x;
        data[4] = (1 - cosa) * y * x + sina * z;
        data[8] = (1 - cosa) * z * x - sina * y;
        data[1] = (1 - cosa) * x * y - sina * z;
        data[5] = cosa + (1 - cosa) * y * y;
        data[9] = (1 - cosa) * z * y + sina * x;
        data[2] = (1 - cosa) * x * y + sina * y;
        data[6] = (1 - cosa) * y * z - sina * x;
        data[10] = cosa + (1 - cosa) * z * z;

        return result;

    }

    public static Mat4 rotateX(float angle) {
        angle = (float) Math.toRadians(angle);
        Mat4 result = new Mat4();
        float[] data = result.data;
        float cosa = (float) Math.cos(angle), sina = (float) Math.sin(angle);

        data[5] = cosa;
        data[6] = sina;
        data[9] = -sina;
        data[10] = cosa;

        return result;
    }

    public static Mat4 rotateY(float angle) {
        angle = (float) Math.toRadians(angle);
        Mat4 result = new Mat4();
        float[] data = result.data;
        float cosa = (float) Math.cos(angle), sina = (float) Math.sin(angle);

        data[0] = cosa;
        data[2] = -sina;
        data[8] = sina;
        data[10] = cosa;

        return result;
    }

    public static Mat4 rotateZ(float angle) {
        angle = (float) Math.toRadians(angle);
        Mat4 result = new Mat4();
        float[] data = result.data;
        float cosa = (float) Math.cos(angle), sina = (float) Math.sin(angle);

        data[0] = cosa;
        data[1] = sina;
        data[4] = -sina;
        data[5] = cosa;

        return result;
    }

    public static Mat4 scale(float factor) {
        return scale(factor, factor, factor);
    }

    public Mat4 mult(Mat4 m) {
        Mat4 result = new Mat4();
        float[] a = this.data;
        float[] b = m.data;
        float[] c = result.data;

        for (int i = 0; i < 4; i++) {   // строка
            for (int j = 0; j < 4; j++) {  // столбец
                float v = 0;
                for (int k = 0; k < 4; k++) {
                    v += a[k*4 + i]*b[j*4 + k];
                }
                c[j*4 + i] = v;
            }
        }

        return result;
    }
}
