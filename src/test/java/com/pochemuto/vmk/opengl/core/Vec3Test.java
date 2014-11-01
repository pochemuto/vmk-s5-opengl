package com.pochemuto.vmk.opengl.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class Vec3Test {
    private final static float EPS = 1e-3f;

    @Test
    public void testEquals() throws Exception {
        assertEquals(new Vec3(7, 5, 9), new Vec3(7, 5, 9));
        assertNotEquals(new Vec3(7, 5, 9), new Vec3(7, 5, 8));
    }

    @Test
    public void testAdd() throws Exception {
        Vec3 v1 = new Vec3(1, 3, 7);
        Vec3 v2 = new Vec3(4, 9, 12);
        assertEquals(v1.add(v2), new Vec3(5, 12, 19));

        v1 = new Vec3(1.1f, 3.2f, 5.3f);
        v2 = new Vec3(2.3f, 4.9f, 4.3f);
        Vec3 r = v1.add(v2);
        assertEquals(3.4f, r.x(), EPS);
        assertEquals(8.1f, r.y(), EPS);
        assertEquals(9.6f, r.z(), EPS);
    }

    @Test
    public void testSub() throws Exception {
        Vec3 v1 = new Vec3(-1.5f, 6.9f, 7.11f);
        Vec3 v2 = new Vec3(10.4f, 4.3f, 3.6f);
        Vec3 r = v1.sub(v2);

        assertEquals(-11.9f, r.x(), EPS);
        assertEquals(2.6f, r.y(), EPS);
        assertEquals(3.51f, r.z(), EPS);
    }

    @Test
    public void testMulScalar() throws Exception {
        Vec3 v = new Vec3(19.4f, 81.2f, 10f).mul(19.7f);
        assertEquals(382.18f, v.x(), EPS);
        assertEquals(1599.64f, v.y(), EPS);
        assertEquals(197, v.z(), EPS);
    }

    @Test
    public void testMul() throws Exception {
        Vec3 v1 = new Vec3(19.4f, 81.2f, 10f);
        Vec3 v2 = new Vec3(0.02f, 0.7f, -1.3f);
        Vec3 r = v1.mul(v2);
        assertEquals(-112.56f, r.x(), EPS);
        assertEquals(25.42f, r.y(), EPS);
        assertEquals(11.956, r.z(), EPS);
    }

    @Test
    public void testLen() throws Exception {
        assertEquals(91.862f, new Vec3(54.4f, 71.9f, 17.6f).len(), EPS);
    }

    @Test
    public void testNormalize() throws Exception {
        Vec3 v = new Vec3(54.4f, 71.9f, 17.6f).normalize();
        assertEquals(0.592189f, v.x(), EPS);
        assertEquals(0.782691f, v.y(), EPS);
        assertEquals(0.191591f, v.z(), EPS);

        assertEquals(1f, v.len(), EPS);
    }

    @Test
    public void testX() throws Exception {
        assertEquals(10.4f, new Vec3(10.4f, 0, -7f).x(), EPS);
    }

    @Test
    public void testY() throws Exception {
        assertEquals(10.4f, new Vec3(7, 10.4f, -7f).y(), EPS);
    }

    @Test
    public void testZ() throws Exception {
        assertEquals(10.4f, new Vec3(102.4f, -13.2f, 10.4f).z(), EPS);
    }
}