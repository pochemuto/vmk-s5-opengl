package com.pochemuto.vmk.opengl;

import java.util.ArrayList;
import java.util.List;

import com.pochemuto.vmk.opengl.object.Node;

/**
 * @author pochemuto
 */
public class World {
    private final List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return nodes;
    }
}
