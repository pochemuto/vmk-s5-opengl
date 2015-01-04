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
                0, 0, 0,  // перед
                0, 1, 0,
                1, 1, 0,
                1, 0, 0,
                0, 0, 1,  // зад
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
                7, 6, 5,  // перед
                7, 5, 4,
                4, 5, 1,  // лево
                4, 1, 0,
                3, 2, 6,  // право
                3, 6, 7,
                4, 0, 3,  // низ
                4, 3, 7,
                1, 5, 6,  // верх
                1, 6, 2
        };
        Surface s = new Surface(vertexes, polygons);
        BOX.getSurfaces().add(s);

        // текстурные координаты одной стороны кубика
        float[] sideCoord = {
                1, 0,
                1, 1,
                0, 1,
                1, 0,
                0, 1,
                0, 0
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
                0, 3, 2,  // основание
                0, 2, 1,
                0, 1, 4,
                1, 2, 4,
                2, 3, 4,
                3, 0, 4
        };

        float[] tex = {
                // A
                0, 0,
                0, 0.5f,
                0.5f, 0.5f,
                0, 0,
                0.5f, 0.5f,
                0.5f, 0,
                // B
                0.5f, 0,
                1, 0,
                0.75f, 0.5f,
                // C
                0, 0.5f,
                0.5f, 0.5f,
                0.25f, 1,
                // D
                0.5f, 0.5f,
                1, 0.5f,
                0.75f, 1,
                // E
                0.75f, 1,
                0.25f, 1,
                0.5f, 0.5f
        };

        Surface s = new Surface(vertexes, polygons);
        s.setTexcoords(tex);
        PYRAMID.getSurfaces().add(s);
    }
}
