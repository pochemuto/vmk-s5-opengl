package com.pochemuto.vmk.opengl.object;

import java.util.List;

import com.pochemuto.vmk.opengl.material.Material;

/**
 * @author pochemuto
 */
public interface Object extends Node {
    List<Surface> getSurfaces();
    Material getMaterial(int surface);
    void setMaterial(Material mat, int surface);
}
