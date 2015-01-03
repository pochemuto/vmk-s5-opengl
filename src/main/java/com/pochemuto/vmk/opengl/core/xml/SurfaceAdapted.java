package com.pochemuto.vmk.opengl.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author pochemuto
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SurfaceAdapted {
    @XmlJavaTypeAdapter(FloatArrayAdapter.class)
    private float[] vertexes;

    @XmlJavaTypeAdapter(FloatArrayAdapter.class)
    private float[] normals;

    @XmlJavaTypeAdapter(IntegerArrayAdapter.class)
    private int[] polygons;

    public SurfaceAdapted() {
    }

    public SurfaceAdapted(float[] vertexes, int[] polygons, float[] normals) {
        this.vertexes = vertexes;
        this.normals = normals;
        this.polygons = polygons;
    }

    public float[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(float[] vertexes) {
        this.vertexes = vertexes;
    }

    public float[] getNormals() {
        return normals;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
    }

    public int[] getPolygons() {
        return polygons;
    }

    public void setPolygons(int[] polygons) {
        this.polygons = polygons;
    }
}
