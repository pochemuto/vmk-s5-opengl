package com.pochemuto.vmk.opengl.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author pochemuto
 */
public class FloatArrayAdapter extends XmlAdapter<String, float[]> {

    @Override
    public float[] unmarshal(String str) throws Exception {
        String[] pieces = str.split("\\s+");
        float[] data = new float[pieces.length];
        for (int i = 0; i < data.length; i++) {
            String coord = pieces[i];
            data[i] = Float.parseFloat(coord);
        }
        return data;
    }

    @Override
    public String marshal(float[] array) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (float v : array) {
            sb.append(' ');
            sb.append(String.valueOf(v));
        }
        if (array.length > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

}
