package com.pochemuto.vmk.opengl;

import com.pochemuto.vmk.opengl.light.Light;
import com.pochemuto.vmk.opengl.light.PointLight;
import com.pochemuto.vmk.opengl.light.Spot;
import com.pochemuto.vmk.opengl.light.Sun;
import com.pochemuto.vmk.opengl.node.BaseNode;
import com.pochemuto.vmk.opengl.node.Pivot;
import com.pochemuto.vmk.opengl.object.Node;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pochemuto
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({BaseNode.class, Light.class, PointLight.class, Spot.class, Sun.class, Pivot.class})
public class World {

    @XmlElementWrapper
    @XmlAnyElement
    private final List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNodeByName(String name) {
        for (Node node : nodes) {
            Node found = findByName(node, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private Node findByName(Node node, String name) {
        if (name.equals(node.getName())) {
            return node;
        }
        for (Node child : node.getChildren()) {
            Node found = findByName(child, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
