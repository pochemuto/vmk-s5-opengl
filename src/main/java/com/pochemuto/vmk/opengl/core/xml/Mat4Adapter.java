package com.pochemuto.vmk.opengl.core.xml;

import com.pochemuto.vmk.opengl.core.Mat4;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author pochemuto
 */
public class Mat4Adapter extends XmlAdapter<String, Mat4> {
    private final FloatArrayAdapter fa = new FloatArrayAdapter();

    @Override
    public String marshal(Mat4 mat) throws Exception {
        if (mat.equals(Mat4.IDENTITY)) {
            return "I";
        }

        return fa.marshal(mat.getData());
    }

    @Override
    public Mat4 unmarshal(String str) throws Exception {
        if ("I".equals(str)) {
            return Mat4.IDENTITY;
        }
        float[] data = fa.unmarshal(str);
        if (data.length != 16) {
            throw new IllegalArgumentException("mat4 должна иметь 16 значений");
        }
        return new Mat4(data);
    }
}
