package com.pochemuto.vmk.opengl.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author pochemuto
 */
public class IntegerArrayAdapter extends XmlAdapter<String, int[]> {
    @Override
    public int[] unmarshal(String str) throws Exception {
        String[] pieces = str.split("\\s+");
        int[] data = new int[pieces.length];
        for (int i = 0; i < data.length; i++) {
            String coord = pieces[i];
            data[i] = Integer.parseInt(coord);
        }
        return data;
    }

    @Override
    public String marshal(int[] array) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int v : array) {
            sb.append(' ');
            sb.append(String.valueOf(v));
        }
        if (array.length > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
