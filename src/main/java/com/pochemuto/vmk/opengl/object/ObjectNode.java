package com.pochemuto.vmk.opengl.object;

import java.util.Arrays;
import java.util.List;

import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.material.Materials;
import com.pochemuto.vmk.opengl.node.BaseNode;

/**
 * @author pochemuto
 */
public class ObjectNode extends BaseNode implements Object {

    private final Material[] materials;

    private final Mesh mesh;

    public ObjectNode(Mesh mesh) {
        this.mesh = mesh;
        materials = new Material[mesh.getSurfaces().size()];
        Arrays.fill(materials, Materials.DEFAULT);
    }

    @Override
    public List<Surface> getSurfaces() {
        return mesh.getSurfaces();
    }

    @Override
    public Material getMaterial(int surface) {
        if (surface < 0 || surface >= materials.length) {
            throw new IndexOutOfBoundsException();
        }
        return materials[surface];
    }

    @Override
    public void setMaterial(Material mat, int surface) {
        if (surface < 0 || surface >= materials.length) {
            throw new IndexOutOfBoundsException();
        }
        materials[surface] = mat;
    }
}
