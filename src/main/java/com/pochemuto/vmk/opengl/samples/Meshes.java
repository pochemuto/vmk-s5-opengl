package com.pochemuto.vmk.opengl.samples;

import java.util.Arrays;

import com.pochemuto.vmk.opengl.object.Mesh;
import com.pochemuto.vmk.opengl.object.Surface;

/**
 * @author pochemuto
 */
public class Meshes {
    public final static Mesh BOX;
    public final static Mesh BOX6;

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
                0, 1, 2,  // низ
                0, 2, 3,
                4, 5, 6,  // верх
                4, 6, 7,
                0, 1, 5,  // право
                0, 5, 4,
                2, 3, 7,  // лево
                2, 7, 6,
                0, 4, 7,  // перед
                0, 7, 3,
                1, 2, 6,  // зад
                1, 6, 5
        };
        Surface s = new Surface(vertexes, polygons);
        BOX.getSurfaces().add(s);

        BOX6 = new Mesh();
        for (int i = 0; i < polygons.length / 6; i++) {
            s = new Surface(vertexes, Arrays.copyOfRange(polygons, 6 * i, 6 * (i + 1)));
            BOX6.getSurfaces().add(s);
        }
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
                0, 1, 2,
                0, 2, 3,
                0, 1, 4,
                1, 2, 4,
                2, 3, 4,
                3, 0, 4
        };

        Surface s = new Surface(vertexes, polygons);
        PYRAMID.getSurfaces().add(s);
    }
}
