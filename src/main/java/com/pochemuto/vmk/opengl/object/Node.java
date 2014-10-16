package com.pochemuto.vmk.opengl.object;

import java.util.List;

import com.pochemuto.vmk.opengl.core.Mat4;

/**
 * @author pochemuto
 */
public interface Node {
    String getName();

    void setName(String name);

    Mat4 getTransform();

    Node getParent();

    void setTransform(Mat4 transform);

    List<Node> getChildren();

    void addChild(Node node);

    void removeChild(Node node);
}
