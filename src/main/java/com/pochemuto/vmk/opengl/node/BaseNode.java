package com.pochemuto.vmk.opengl.node;

import java.util.ArrayList;
import java.util.List;

import com.pochemuto.vmk.opengl.core.Mat4;

import com.pochemuto.vmk.opengl.object.Node;

/**
 * @author pochemuto
 */
public class BaseNode implements Node {
    private final List<Node> children = new ArrayList<>();

    private Mat4 transform = Mat4.IDENTITY;

    private Node parent;

    private String name;

    @Override
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
    public Node getParent() {
        return parent;
    }

    @Override
    public void setTransform(Mat4 transform) {
        this.transform = transform;
    }

    @Override
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
}
