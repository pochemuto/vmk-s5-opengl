package com.pochemuto.vmk.opengl.object;

import com.pochemuto.vmk.opengl.core.Vec3;
import com.pochemuto.vmk.opengl.core.xml.SurfaceAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author pochemuto
 */
@XmlRootElement
@XmlJavaTypeAdapter(SurfaceAdapter.class)
public class Surface {

    private final float[] vertexes;

    private final float[] normals;

    private final int[] polygons;

    private float[] texcoords;

    public Surface(float[] vertexes, int[] polygons, float[] normals) {
        if (polygons.length % 3 != 0) {
            throw new IllegalArgumentException("количество точек полигонов должно быть кратно трем");
        }
        if (vertexes.length % 3 != 0) {
            throw new IllegalArgumentException("количество координат вертексов должно быть кратно трем");
        }
        if (normals.length != polygons.length * 3) {
            throw new IllegalArgumentException("количество координат нормалей неверно");
        }
        this.vertexes = vertexes;
        this.polygons = polygons;
        this.normals = normals;
    }

    public Surface(float[] vertexes, int[] polygons) {
        this(vertexes, polygons, autoNormals(vertexes, polygons));
    }

    private static float[] autoNormals(float[] vertexes, int[] polygons) {
        float[] normals = new float[polygons.length*3];   // на каждый полигон 3 вектора

        for (int i = 0; i < polygons.length; i+=3) {
            Vec3 a = vector(vertexes, polygons[i]),
                 b = vector(vertexes, polygons[i+1]),
                 c = vector(vertexes, polygons[i+2]);

            Vec3 normal = normal(a, b, c);
            putNormal(normal, normals, 3*i);
            putNormal(normal, normals, 3*i+3);
            putNormal(normal, normals, 3*i+6);
        }

        return normals;
    }

    private static Vec3 vector(float[] vertexes, int v) {
        return new Vec3(vertexes[3*v], vertexes[3*v+1], vertexes[3*v+2]);
    }

    private static Vec3 normal(Vec3 center, Vec3 left, Vec3 right) {
        Vec3 l = left.sub(center), r = right.sub(center);
        return l.mul(r).normalize();
    }

    private static void putNormal(Vec3 n, float[] target, int offset) {
        target[offset]   = n.x();
        target[offset+1] = n.y();
        target[offset+2] = n.z();
    }

    public float[] getVertexes() {
        return vertexes;
    }

    public int[] getPolygons() {
        return polygons;
    }

    public float[] getNormals() {
        return normals;
    }

    public float[] getTexcoords() {
        return texcoords;
    }

    public void setTexcoords(float[] texcoords) {
        if (texcoords.length != polygons.length * 2) {
            throw new IllegalArgumentException("текстурных координат должно быть по две на каждый вертекс");
        }
        this.texcoords = texcoords;
    }
}