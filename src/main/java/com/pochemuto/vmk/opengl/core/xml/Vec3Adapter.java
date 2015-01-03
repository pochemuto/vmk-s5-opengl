package com.pochemuto.vmk.opengl.core.xml;

import com.pochemuto.vmk.opengl.core.Vec3;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author pochemuto
 */
public class Vec3Adapter extends XmlAdapter<String, Vec3> {
    private final FloatArrayAdapter fa = new FloatArrayAdapter();

    @Override
    public Vec3 unmarshal(String v) throws Exception {
        float[] coords = fa.unmarshal(v);
        if (coords.length != 3) {
            throw new IllegalArgumentException("количество координат вектора не равно трем");
        }
        return new Vec3(coords[0], coords[1], coords[2]);
    }

    @Override
    public String marshal(Vec3 v) throws Exception {
        float[] data = {v.x(), v.y(), v.z()};
        return fa.marshal(data);
    }
}
