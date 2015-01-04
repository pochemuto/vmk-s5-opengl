package com.pochemuto.vmk.opengl.object;

import com.pochemuto.vmk.opengl.core.Mat4;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author pochemuto
 */
@XmlType(propOrder = {"name", "transform", "children"})
public interface Node {
    String getName();

    void setName(String name);

    Mat4 getTransform();

    Node getParent();

    void setTransform(Mat4 transform);

    List<Node> getChildren();

    void addChild(Node node);

    void removeChild(Node node);

    void setEnabled(boolean enabled);

    boolean isEnabled();
}
