package com.pochemuto.vmk.opengl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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

    private boolean drag = false;
    private float angleY = 0;
    private float angleX = 0;
    private float angleYSpeed = 0;
    private float g = 0.9f;
    private float EPS = 1e-10f;
    private float da = 0.7f;
    private boolean animate = false;
    private long last;
    private boolean cyclic = false;

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

        Manipulator m =new Manipulator() {
            @Override
            void drag(int dx, int dy) {
                angleY += dx;
                angleX += dy;
                angleYSpeed = dx;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drag = true;
                animate = false;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drag = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    animate = true;
                }
            }
        };
        canvas.addMouseListener(m);
        canvas.addMouseMotionListener(m);
    }

    private void initWorld() {
        // наполняем мир
        Node pivot = new Pivot();
        ObjectNode box = new ObjectNode(Meshes.BOX6);
        box.setName("Box");
//        box.setMaterial(solidMaterial(Color.RED),   0);
//        box.setMaterial(solidMaterial(Color.GREEN), 1);
//        box.setMaterial(solidMaterial(Color.BLUE),  2);
//        box.setMaterial(solidMaterial(Color.GRAY),  3);
//        box.setMaterial(solidMaterial(Color.BLACK), 4);
//        box.setMaterial(solidMaterial(Color.CYAN),  5);

        ObjectNode roof = new ObjectNode(Meshes.PYRAMID);
        roof.setMaterial(solidMaterial(Color.GREEN), 0);
        roof.setTransform(Mat4.translate(0, 1f, 0).mult(Mat4.scale(1.4f, 1f, 1.4f)));

        ObjectNode table = new ObjectNode(Meshes.BOX);
        table.setMaterial(solidMaterial(new Color(1,1,1)), 0);
        table.setTransform(Mat4.translate(0, -2, 0).mult(Mat4.scale(7,1,7)));


        Node p = new Pivot();
        p.setTransform(Mat4.rotateX(25f).mult(Mat4.translate(-2,0,0)));

        pivot.addChild(box);
//        pivot.addChild(roof);

        p.addChild(pivot);
        p.addChild(table);
        world.getNodes().add(p);

        // обновление мира
        canvas.setUpdater(w -> {
            // вращение
            if (!drag && angleYSpeed > EPS) {
                angleY += angleYSpeed;
                angleYSpeed *= g;
            }
            if (animate) {
                angleY += da;
            }
            pivot.setTransform(Mat4.rotateX(angleX).mult(Mat4.rotateY(angleY)));
            // циклическое обновление материалов
            long time = new Date().getTime();
            if (cyclic && time - last >= 2000) {
                last = time;
                roof.setMaterial(randomMaterial(), 0);
                Material m = box.getMaterial(0);
                int surfaceLastIdx = box.getSurfaces().size() - 1;
                for (int i = 0; i < surfaceLastIdx; i++) {
                    box.setMaterial(box.getMaterial(i+1), i);
                }
                box.setMaterial(m, surfaceLastIdx);
            }
        });
    }

    private Material randomMaterial() {
        Color[] c = new Color[] {
                Color.GREEN, Color.RED, Color.BLUE
        };

        int n = (int) (Math.random()*(c.length));
        return solidMaterial(c[n]);
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
