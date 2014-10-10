package opengl;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.Animator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App a = new App();
        a.start(args);
    }

    public void start(String[] args) {
        final Frame frame = new Frame();

        final GLCanvas canvas = new GLCanvas( );
        frame.add(canvas);

        frame.setSize(300, 300);
        frame.setBackground(Color.WHITE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.remove( canvas );
                frame.dispose();
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable drawable) {
                Figure.init(drawable.getGL().getGL2());
            }

            @Override
            public void dispose(GLAutoDrawable drawable) {

            }

            @Override
            public void display(GLAutoDrawable drawable) {
                    Figure.render(drawable.getGL().getGL2(), drawable.getWidth(), drawable.getHeight());
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                Figure.reshape(drawable.getGL().getGL2(), width, height);
            }
        });

        Animator animator = new Animator(canvas);
        animator.start();
        frame.show();
    }


}
