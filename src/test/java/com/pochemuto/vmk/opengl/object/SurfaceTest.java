package com.pochemuto.vmk.opengl.object;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SurfaceTest {
    private final static float EPS = 1e-5f;
    private Surface s;

    @Before
    public void before() {
        float[] vertexes = {
                12.2f, 1.7f, 2.3f,
                1, 1, 1,
                2, 2, 2};
        int[] polygons = {0, 1, 2};
        s = new Surface(vertexes, polygons);
    }

    @Test
    public void testGetVertexes() throws Exception {
        assertArrayEquals(new float[]{
                12.2f, 1.7f, 2.3f,
                1, 1, 1,
                2, 2, 2}, s.getVertexes(), EPS);
    }

    @Test
    public void testGetPolygons() throws Exception {
        assertArrayEquals(new int[]{0, 1, 2}, s.getPolygons());
    }

    @Test
    public void testGetNormals() throws Exception {
        assertArrayEquals(new float[]{
                0.0415406f, 0.685421f, -0.726961f,
                0.0415406f, 0.685421f, -0.726961f,
                0.0415406f, 0.685421f, -0.726961f}, s.getNormals(), EPS);
    }

    @Test
    public void testMultiNormals() throws Exception {
        float[] vertexes = new float[]{
                0, 0, 0,
                1, 0, 0,
                0, 1, 0,
                1, 1, 0
        };
        int[] polygons = new int[] {
                0, 1, 2,
                2, 1, 3
        };
        s = new Surface(vertexes, polygons);

        float[] normals = s.getNormals();
        System.out.println(Arrays.toString(normals));
        float[] expected = {
                0,0,1f,
                0,0,1f,
                0,0,1f,
                0,0,1f,
                0,0,1f,
                0,0,1f
        };
        assertArrayEquals(expected, normals, EPS);
    }
}