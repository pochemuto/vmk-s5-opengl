package com.pochemuto.vmk.opengl.object;

import com.pochemuto.vmk.opengl.core.Vec3;

/**
 * @author pochemuto
 */
public class Surface {
    private final float[] vertexes;
    private final float[] normals;
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
        this.normals = null;
    }

    private float[] autoNormals(float[] vertexes, int[] polygons) {
        float[] normals = new float[polygons.length*3];

        int ni = 0;
        for (int i = 0; i < polygons.length; i+=3) {
            Vec3 a = vector(vertexes, i),
                 b = vector(vertexes, i+1),
                 c = vector(vertexes, i+2);

            Vec3 n =  b.sub(a).mul(c.sub(a));
            putVector(normals, n, ni);
            ni += 3;
            n = a.sub(b).mul(c.sub(b));
            putVector(normals, n, ni);
            ni += 3;
            n = b.sub(c).mul(a.sub(c));
            putVector(normals, n, ni);
        }

        return normals;
    }

    private Vec3 vector(float[] vertexes, int v) {
        return new Vec3(vertexes[3*v], vertexes[3*v+1], vertexes[3*v+2]);
    }

    private void putVector(float[] target, Vec3 v, int offset) {
        target[offset++] = v.x();
        target[offset++] = v.y();
        target[offset]   = v.z();
    }

    public float[] getVertexes() {
        return vertexes;
    }

    public int[] getPolygons() {
        return polygons;
    }
}
