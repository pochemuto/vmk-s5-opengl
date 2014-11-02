package com.pochemuto.vmk.opengl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import com.pochemuto.vmk.opengl.core.Vec3;
import com.pochemuto.vmk.opengl.light.Light;
import com.pochemuto.vmk.opengl.light.Projector;
import com.pochemuto.vmk.opengl.light.Spot;
import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.object.Node;
import com.pochemuto.vmk.opengl.object.Object;
import com.pochemuto.vmk.opengl.object.Surface;

/**
 * @author pochemuto
 */
public class Canvas extends GLCanvas implements GLEventListener {
    private World world;
    private GLU glu;
    private Updater updater;
    private int lights = 0;

    public Canvas() throws GLException {
        addGLEventListener(this);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);

        gl.glEnable(GL2.GL_LIGHTING);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

//        float[] light0_position = {0, 0, 0, 1.0f};
//        gl.glEnable(GL2.GL_LIGHT0);
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, Color.WHITE.getComponents(null), 0);

        gl.glTranslatef(1.6f, 0.0f, -7.0f); // translate right and into the screen

        if (world == null) return;
        if (updater != null) {
            updater.update(world);
        }

        //TODO камера
        lights = 0;
        for (Node node : world.getNodes()) {
            renderNodeTree(node, gl);
        }
    }

    private void renderPyramid(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS); // of the color cube
        // Font-face triangle
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        // Right-face triangle
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        // Back-face triangle
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);


        // Left-face triangle
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glEnd();
    }

    private void renderNodeTree(Node node, GL2 gl) {
        gl.glPushMatrix();
        gl.glMultMatrixf(node.getTransform().getData(), 0);

        if (node instanceof Object) {
            renderObject((Object) node, gl);
        } else if (node instanceof Light) {
            renderLight((Light) node, gl);
            lights++;
        }

        for (Node child : node.getChildren()) {
            renderNodeTree(child, gl);
        }

        gl.glPopMatrix();
    }

    private void renderLight(Light light, GL2 gl) {
        float[] color = new float[4];
        int lightNum = GL2.GL_LIGHT0 + lights;
        gl.glEnable(lightNum);
        gl.glLightfv(lightNum, GL2.GL_POSITION, new float[] {0,0,1,0}, 0);
        gl.glLightfv(lightNum, GL2.GL_DIFFUSE, light.getDiffuse().getComponents(color), 0);
        gl.glLightfv(lightNum, GL2.GL_SPECULAR, light.getSpecular().getComponents(color), 0);
        gl.glLightfv(lightNum, GL2.GL_AMBIENT, light.getAmbient().getComponents(color), 0);

        if (light instanceof Spot) {
            Spot spot = (Spot) light;
            gl.glLightf(lightNum, GL2.GL_CONSTANT_ATTENUATION, spot.getConstantAttenuation());
            gl.glLightf(lightNum, GL2.GL_LINEAR_ATTENUATION, spot.getConstantAttenuation());
            gl.glLightf(lightNum, GL2.GL_QUADRATIC_ATTENUATION, spot.getQuadraticAttenuation());

            if (spot instanceof Projector) {
                Projector proj = (Projector) spot;
                gl.glLightfv(lightNum, GL2.GL_SPOT_DIRECTION, vec3Array(proj.getDirection()), 0);
                gl.glLightf(lightNum, GL2.GL_SPOT_CUTOFF, proj.getCutoff());
                gl.glLightf(lightNum, GL2.GL_SPOT_EXPONENT, proj.getExponent());
            }
        }
    }

    private static float[] vec3Array(Vec3 vec) {
        return new float[] {vec.x(), vec.y(), vec.z()};
    }

    private void renderObject(Object obj, GL2 gl) {
        int s = 0;
        float[] color = new float[4];

        gl.glBegin(GL2.GL_TRIANGLES);
        for (Surface surface : obj.getSurfaces()) {
            Material material = obj.getMaterial(s++);
            //TODO материал
            gl.glColor3fv(material.getColor().getComponents(color), 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material.getDiffuse().getComponents(color), 0);

            float[] vertexes = surface.getVertexes();
            float[] normals = surface.getNormals();
            int[] polygons = surface.getPolygons();

            for (int i = 0; i < polygons.length; i += 3) {
                addVertex(vertexes, polygons[i], normals, i, gl);
                addVertex(vertexes, polygons[i + 1], normals, i + 1, gl);
                addVertex(vertexes, polygons[i + 2], normals, i + 2, gl);
            }
        }
        gl.glEnd();
    }

    private void addVertex(float[] vertexes, int v, float[] normals, int n, GL2 gl) {
        gl.glNormal3f(normals[3 * n], normals[3 * n + 1], normals[3 * n + 2]);
        gl.glVertex3f(vertexes[3 * v], vertexes[3 * v + 1], vertexes[3 * v + 2]);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        height = (height == 0) ? 1 : height;
        float aspect = (float) width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        //TODO камера
        glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }
}
