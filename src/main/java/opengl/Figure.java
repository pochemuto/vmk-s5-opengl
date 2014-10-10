package opengl;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

/**
 * @author pochemuto
 */
public class Figure {
    private static float angle = 0;
    protected static Point[] points = new Point[4];
    protected static Color[] colors = new Color[4];
    static {
        points[0] = new Point(0,0,0);
        points[1] = new Point(1,0,0);
        points[2] = new Point(0,1,0);
        points[3] = new Point(0,0,1);

        colors[0] = Color.RED;
        colors[1] = Color.BLUE;
        colors[2] = Color.GREEN;
        colors[3] = Color.PINK;
    }

    protected static void reshape(GL2 gl, int width, int height) {
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluPerspective(30.0f, width/((float) height), 0.1f, 10.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glViewport(0, 0, width, height);

    }

    protected static void render( GL2 gl2, int width, int height ) {
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glClearColor(1f,1f,1f,1f);


        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glLoadIdentity();
        gl2.glTranslatef(-0.5f, -0.5f, -5f);
        gl2.glRotatef(angle++,0,1,0.3f);
        // draw a triangle filling the window

        gl2.glBegin(GL.GL_TRIANGLES);
        renderTriangle(gl2, 0, 1, 2);
        renderTriangle(gl2, 0, 1, 3);
        renderTriangle(gl2, 0, 2, 3);
        renderTriangle(gl2, 1, 2, 3);

        gl2.glEnd();

    }

    protected static void renderTriangle(GL2 gl2, int p1, int p2, int p3)
    {
        int[] pts = new int[] {p1,p2,p3};
        float[] f = new float[3];

        for (int i = 0; i < 3; i++) {
            colors[pts[i]].getColorComponents(f);
            gl2.glColor3fv(f, 0);
            points[pts[i]].getCoords(f);
            gl2.glVertex3fv(f, 0);
        }

    }

    public static void init(GL2 gl2) {
        // Enable smooth shading, which blends colors nicely, and smoothes out lighting.
        gl2.glShadeModel(GL2.GL_SMOOTH);
        // Set background color in RGBA. Alpha: 0 (transparent) 1 (opaque)
        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Setup the depth buffer and enable the depth testing
        gl2.glClearDepth(1.0f);          // clear z-buffer to the farthest
        gl2.glEnable(GL2.GL_DEPTH_TEST);  // enables depth testing
        gl2.glDepthFunc(GL2.GL_LEQUAL);   // the type of depth test to do
    }
}


class Point
{
    public final float x;
    public final float y;
    public final float z;

    Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void getCoords(float[] f)
    {
        f[0] = x;
        f[1] = y;
        f[2] = z;
    }
}