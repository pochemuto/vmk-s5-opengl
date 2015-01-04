package com.pochemuto.vmk.opengl.node;

import com.pochemuto.vmk.opengl.core.Mat4;
import com.pochemuto.vmk.opengl.object.Node;
import com.pochemuto.vmk.opengl.object.ObjectNode;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pochemuto
 */
@XmlType(propOrder = {"name", "transform", "children"})
@XmlSeeAlso({ObjectNode.class})
public abstract class BaseNode implements Node {
    private final List<Node> children = new ArrayList<>();

    private Mat4 transform = Mat4.IDENTITY;

    private Node parent;

    private String name;

    private boolean enabled = true;

    @Override
    @XmlAttribute
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Mat4 getTransform() {
        return transform;
    }

    @Override
    @XmlTransient
    public Node getParent() {
        return parent;
    }

    @Override
    public void setTransform(Mat4 transform) {
        this.transform = transform;
    }

    @Override
    @XmlElementWrapper
    @XmlAnyElement
    public List<Node> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node node) {
        children.add(node);
    }

    @Override
    public void removeChild(Node node) {
        children.remove(node);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
