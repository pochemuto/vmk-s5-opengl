package com.pochemuto.vmk.opengl;

import com.jogamp.opengl.util.FPSAnimator;
import com.pochemuto.vmk.opengl.core.Mat4;
import com.pochemuto.vmk.opengl.light.Spot;
import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.node.Pivot;
import com.pochemuto.vmk.opengl.object.Node;
import com.pochemuto.vmk.opengl.object.ObjectNode;
import com.pochemuto.vmk.opengl.samples.Meshes;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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
        initDynamic();
    }

    private void initDynamic() {
        Node pivot = world.getNodeByName("pivot");
        com.pochemuto.vmk.opengl.object.Object box = (com.pochemuto.vmk.opengl.object.Object) world.getNodeByName("box");

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
//                roof.setMaterial(randomMaterial(), 0);
                Material m = box.getMaterial(0);
                int surfaceLastIdx = box.getSurfaces().size() - 1;
                for (int i = 0; i < surfaceLastIdx; i++) {
                    box.setMaterial(box.getMaterial(i+1), i);
                }
                box.setMaterial(m, surfaceLastIdx);
            }
        });
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
        pivot.setName("pivot");
        ObjectNode box = new ObjectNode(Meshes.BOX6);
        Material sideMaterial = new Material();
        sideMaterial.setTexture(Application.class.getResource("/bricks.png"));
        box.setTransform(Mat4.scale(1, 0.8f, 1));
        box.setName("box");
        box.setMaterial(sideMaterial, 0);
        box.setMaterial(sideMaterial, 1);
        box.setMaterial(sideMaterial, 2);
        box.setMaterial(sideMaterial, 3);
        box.setMaterial(sideMaterial, 4);
        box.setMaterial(sideMaterial, 5);

        ObjectNode roof = new ObjectNode(Meshes.PYRAMID);
        roof.setMaterial(solidMaterial(Color.GREEN), 0);
        roof.setTransform(Mat4.translate(0, 0.8f, 0).mult(Mat4.scale(1.4f, 0.8f, 1.4f)));

        ObjectNode table = new ObjectNode(Meshes.BOX);
        table.setMaterial(solidMaterial(new Color(0, 206, 206)), 0);
        table.setTransform(Mat4.translate(0, -2, 0).mult(Mat4.scale(7,1,7)));

        Node p = new Pivot();
        p.setTransform(Mat4.rotateX(25f).mult(Mat4.translate(-2, 0, 0)));

        pivot.addChild(box);
        pivot.addChild(roof);

        p.addChild(pivot);
        p.addChild(table);

        // свет
        Spot light = new Spot();
        light.setConstantAttenuation(0);
        light.setLinearAttenuation(0.2f);
        light.setQuadraticAttenuation(0.4f);
        light.setDiffuse(new Color(223, 219, 255));

        light.setTransform(Mat4.rotateY(30));

        world.getNodes().add(light);
        world.getNodes().add(p);

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
        m.setDiffuse(c);
        return m;
    }

    public static void main(String... args) throws JAXBException {
        Application app = new Application();
        app.start();
    }

    private void start() {
        frame.setVisible(true);
        animator.start();
    }
}
