package com.pochemuto.vmk.opengl.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pochemuto
 */
public class Mesh {

    private List<Surface> surfaces = new ArrayList<>();

    public List<Surface> getSurfaces() {
        return surfaces;
    }
}
