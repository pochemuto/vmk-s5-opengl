package com.pochemuto.vmk.opengl.samples;

import com.pochemuto.vmk.opengl.object.Mesh;
import com.pochemuto.vmk.opengl.object.Surface;

import java.util.Arrays;

/**
 * @author pochemuto
 */
public class Meshes {
    public final static Mesh BOX;
    public final static Mesh BOX6;
    public final static Mesh PLANE;

    static {
        BOX = new Mesh();
        // против часовой строки
        float[] vertexes = {
                0, 0, 0,  // низ
                0, 1, 0,
                1, 1, 0,
                1, 0, 0,
                0, 0, 1,  // верх
                0, 1, 1,
                1, 1, 1,
                1, 0, 1,
        };
        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i] -= 0.5f;
        }
        int[] polygons = {
                0, 1, 2,  // зад
                0, 2, 3,
                5, 4, 6,  // перед
                6, 4, 7,
                1, 0, 5,  // лево
                5, 0, 4,
                3, 2, 7,  // право
                7, 2, 6,
                4, 0, 7,  // низ
                7, 0, 3,
                2, 1, 6,  // верх
                6, 1, 5
        };
        Surface s = new Surface(vertexes, polygons);
        BOX.getSurfaces().add(s);

        // текстурные координаты одной стороны кубика
        float[] sideCoord = {
                0, 0,
                1, 1,
                0, 1,
                0, 0,
                1, 1,
                1, 0
        };
        BOX6 = new Mesh();
        int sides = polygons.length / 6;
        for (int i = 0; i < sides; i++) {
            s = new Surface(vertexes, Arrays.copyOfRange(polygons, 6 * i, 6 * (i + 1)));
            s.setTexcoords(sideCoord);
            BOX6.getSurfaces().add(s);
        }

        PLANE = new Mesh();

        vertexes = new float[]{
                0, 0, 0,
                1, 0, 0,
                0, 1, 0,
                1, 1, 0
        };
        polygons = new int[] {
                0, 1, 2,
                2, 1, 3
        };
        s = new Surface(vertexes, polygons);
        System.out.println(Arrays.toString(s.getNormals()));
    }

    public final static Mesh PYRAMID;
    static {
        PYRAMID = new Mesh();
        float[] vertexes = {
                0,    0, 0,
                0,    0, 1,
                1,    0, 1,
                1,    0, 0,
                0.5f, 1, 0.5f
        };

        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i] -= 0.5f;
        }

        int[] polygons = {
                1, 0, 2,  // основание
                2, 0, 3,
                0, 1, 4,
                1, 2, 4,
                2, 3, 4,
                3, 0, 4
        };

        Surface s = new Surface(vertexes, polygons);
        PYRAMID.getSurfaces().add(s);
    }
}
