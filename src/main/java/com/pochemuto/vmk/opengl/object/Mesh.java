package com.pochemuto.vmk.opengl.object;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pochemuto
 */
@XmlRootElement
public class Mesh {

    private List<Surface> surfaces = new ArrayList<>();

    public List<Surface> getSurfaces() {
        return surfaces;
    }
}
