package com.pochemuto.vmk.opengl.object;

/**
 * @author pochemuto
 */
public class Surface {
    private final float[] vertexes;
    private final int[] polygons;

    public Surface(float[] vertexes, int[] polygons) {
        if (polygons.length % 3 != 0) {
            throw new IllegalArgumentException("количество точек полигонов должно быть кратно трем");
        }
        if (vertexes.length % 3 != 0) {
            throw new IllegalArgumentException("количество координат вертексов должно быть кратно трем");
        }
        this.vertexes = vertexes;
        this.polygons = polygons;
    }

    public float[] getVertexes() {
        return vertexes;
    }

    public int[] getPolygons() {
        return polygons;
    }
}
