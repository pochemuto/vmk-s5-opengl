package com.pochemuto.vmk.opengl.object;

import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.material.Materials;
import com.pochemuto.vmk.opengl.node.BaseNode;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author pochemuto
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectNode extends BaseNode implements Object {

    @XmlElementWrapper
    private Material[] materials;

    @XmlElement
    private Mesh mesh;

    public ObjectNode() {
    }

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
