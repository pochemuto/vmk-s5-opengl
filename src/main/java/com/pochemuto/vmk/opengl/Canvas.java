package com.pochemuto.vmk.opengl;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.pochemuto.vmk.opengl.core.Vec3;
import com.pochemuto.vmk.opengl.light.Light;
import com.pochemuto.vmk.opengl.light.PointLight;
import com.pochemuto.vmk.opengl.light.Spot;
import com.pochemuto.vmk.opengl.light.Sun;
import com.pochemuto.vmk.opengl.material.Material;
import com.pochemuto.vmk.opengl.object.Node;
import com.pochemuto.vmk.opengl.object.Object;
import com.pochemuto.vmk.opengl.object.Surface;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pochemuto
 */
public class Canvas extends GLCanvas implements GLEventListener {
    private World world;
    private GLU glu;
    private Updater updater;
    private int lights = 0;
    private final Map<String, Texture> textures = new HashMap<>();

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

        world.recursive(node -> {
            if (node instanceof Object) {
                Object obj = (Object) node;
                for (int i = 0; i < obj.getSurfaces().size(); i++) {
                    Material material = obj.getMaterial(i);
                    String texFile = material.getTexture();
                    if (texFile != null) {
                        try {
//                            int[] ids = new int[1];
//                            gl.glGenTextures(1, ids, 0);
//                            int textureId = ids[0];
//                            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId);
//                            Buffer image = ByteBuffer.wrap(Files.readAllBytes(Paths.get("/Users/pochemuto/Documents/workspace/opengl/src/main/resources/bricks.png")));
//                            gl.glTexImage2D(textureId, 0, GL.GL_RGBA, 256, 256, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, image);
//                            textures.put(texFile, textureId);
                            Texture texture = TextureIO.newTexture(new File("/Users/pochemuto/Documents/workspace/opengl/src/main/resources/bricks.png"), true);
                            textures.put(texFile, texture);
                            System.out.println("loaded " + texFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
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
        int lightNum = GL2.GL_LIGHT1 + lights;
        if (light instanceof Sun) {
            lightNum = 0;
        }

        gl.glEnable(lightNum);

        gl.glLightfv(lightNum, GL2.GL_POSITION, new float[]{0, 0, 1, 0}, 0);
        gl.glLightfv(lightNum, GL2.GL_DIFFUSE, light.getDiffuse().getComponents(color), 0);
        gl.glLightfv(lightNum, GL2.GL_SPECULAR, light.getSpecular().getComponents(color), 0);
        gl.glLightfv(lightNum, GL2.GL_AMBIENT, light.getAmbient().getComponents(color), 0);

        if (light instanceof PointLight) {
            PointLight pointLight = (PointLight) light;
            gl.glLightf(lightNum, GL2.GL_CONSTANT_ATTENUATION, pointLight.getConstantAttenuation());
            gl.glLightf(lightNum, GL2.GL_LINEAR_ATTENUATION, pointLight.getLinearAttenuation());
            gl.glLightf(lightNum, GL2.GL_QUADRATIC_ATTENUATION, pointLight.getQuadraticAttenuation());

            if (pointLight instanceof Spot) {
                Spot spot = (Spot) pointLight;
                gl.glLightfv(lightNum, GL2.GL_SPOT_DIRECTION, vec3Array(spot.getDirection()), 0);
                gl.glLightf(lightNum, GL2.GL_SPOT_CUTOFF, spot.getCutoff());
                gl.glLightf(lightNum, GL2.GL_SPOT_EXPONENT, spot.getExponent());
            }
        }

    }

    private static float[] vec3Array(Vec3 vec) {
        return new float[]{vec.x(), vec.y(), vec.z()};
    }

    private void renderObject(Object obj, GL2 gl) {
        int s = 0;
        float[] color = new float[4];

        for (Surface surface : obj.getSurfaces()) {
            Material material = obj.getMaterial(s++);
            //TODO материал
            gl.glColor3fv(material.getColor().getComponents(color), 0);
            gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material.getDiffuse().getComponents(color), 0);

            float[] texcoords = surface.getTexcoords();
            String textureFile = material.getTexture();
            try {
                if (textureFile != null && texcoords != null) {
                    Texture textureId = textures.get(textureFile);
                    if (textureId == null) {
                        System.err.println("текстура " + textureFile + " не загружена");
                    } else {
//                        gl.glEnable(GL.GL_TEXTURE_2D);
//                        gl.glBindTexture(GL.GL_TEXTURE_2D, textureId);
                        textureId.enable(gl);
                        textureId.bind(gl);
                    }
//                    Texture texture = TextureIO.newTexture(textureFile, true, TextureIO.TGA);
//                    Texture texture = TextureIO.newTexture(getClass().getResourceAsStream("/bricks.png"), false, TextureIO.PNG);
//                    texture.bind(gl);
//                    texture.enable(gl);

//                    System.out.println("загружена текстура " + textureFile + " (" + texture.getImageWidth() + "x" + texture.getImageHeight());
                }
            } catch (GLException e) {
                System.err.println("ошибка при загрузке текстуры " + textureFile);
                e.printStackTrace();
            }


            gl.glBegin(GL2.GL_TRIANGLES);
            float[] vertexes = surface.getVertexes();
            float[] normals = surface.getNormals();
            int[] polygons = surface.getPolygons();

            for (int i = 0; i < polygons.length; i += 3) {
                addVertex(vertexes, polygons[i], normals, texcoords, i, gl);
                addVertex(vertexes, polygons[i + 1], normals, texcoords, i + 1, gl);
                addVertex(vertexes, polygons[i + 2], normals, texcoords, i + 2, gl);
            }

            gl.glDisable(GL2.GL_TEXTURE_2D);
            gl.glEnd();
        }
    }

    private void addVertex(float[] vertexes, int v, float[] normals, float[] texcoords, int n, GL2 gl) {
        gl.glNormal3fv(normals, 3 * n);
        if (texcoords != null) {
            gl.glTexCoord2fv(texcoords, 2 * n);
        }
        gl.glVertex3fv(vertexes, 3 * v);
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
