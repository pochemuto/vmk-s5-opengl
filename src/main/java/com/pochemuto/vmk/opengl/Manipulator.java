package com.pochemuto.vmk.opengl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author pochemuto
 */
public abstract class Manipulator extends MouseAdapter {
    int x,y;

    @Override
    public void mouseDragged(MouseEvent e) {
        drag(e.getX() - x, e.getY() - y);
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    abstract void drag(int dx, int dy);
}
