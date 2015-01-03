package com.pochemuto.vmk.opengl.core.xml;

import com.pochemuto.vmk.opengl.object.Surface;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author pochemuto
 */
public class SurfaceAdapter extends XmlAdapter<SurfaceAdapted, Surface>{
    @Override
    public Surface unmarshal(SurfaceAdapted v) throws Exception {
        return new Surface(v.getVertexes(), v.getPolygons(), v.getNormals());
    }

    @Override
    public SurfaceAdapted marshal(Surface v) throws Exception {
        return new SurfaceAdapted(v.getVertexes(), v.getPolygons(), v.getNormals());
    }
}
