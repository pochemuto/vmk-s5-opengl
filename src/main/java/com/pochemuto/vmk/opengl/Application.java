package com.pochemuto.vmk.opengl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.util.FPSAnimator;
import com.pochemuto.vmk.opengl.core.Mat4;
import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.node.Pivot;
import com.pochemuto.vmk.opengl.object.Node;
import com.pochemuto.vmk.opengl.object.ObjectNode;
import com.pochemuto.vmk.opengl.samples.Meshes;

/**
 * @author pochemuto
 */
public class Application {
    private Canvas canvas;
    private World world;
    private FPSAnimator animator;
    private JFrame frame;

    private float angle = 0;

    public Application() {
        initFrame();
        initWorld();
    }

    private void initFrame() {
        world = new World();
        canvas = new Canvas();
        canvas.setWorld(world);

        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 600;

        animator = new FPSAnimator(canvas, 60, true);
        frame = new JFrame("OpenGL Java Rocks!");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(canvas, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        animator.stop(); // stop the animator loop
                        System.exit(0);
                    }
                }.start();
            }
        });
    }

    private void initWorld() {
        ObjectNode box = new ObjectNode(Meshes.BOX6);
        box.setName("Box");
//        box.setTransform(Mat4.translate(-1f, 0, 0));
//        box.setTransform(Mat4.quaternion(10, 1, 1, 1));

        box.setMaterial(solidMaterial(Color.RED),   0);
        box.setMaterial(solidMaterial(Color.GREEN), 1);
        box.setMaterial(solidMaterial(Color.BLUE),  2);
        box.setMaterial(solidMaterial(Color.GRAY),  3);
        box.setMaterial(solidMaterial(Color.BLACK), 4);
        box.setMaterial(solidMaterial(Color.CYAN),  5);

        Node p = new Pivot();
        p.setTransform(Mat4.rotateX(25f));
        p.addChild(box);
        world.getNodes().add(p);

        canvas.setUpdater(world1 -> {
            float da = 0.7f;
            angle += da;
            box.setTransform(Mat4.rotateY(angle));
        });
    }

    private Material solidMaterial(Color c) {
        Material m = new Material();
        m.setColor(c);
        return m;
    }

    public static void main(String... args) {
        Application app = new Application();
        app.start();
    }

    private void start() {
        frame.setVisible(true);
        animator.start();
    }
}
