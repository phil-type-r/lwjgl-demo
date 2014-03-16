import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import org.lwjgl.util.vector.*;
import org.lwjgl.LWJGLException;

/**
 * Created by phil-type-r on 16.03.14.
 * Device-Class
 */
public class Device {
    private int width;
    private int height;
    private boolean firstLoop;
    private Vector4f backDrop;

    public Device() {
        this(640, 480, false);
        this.width = 640;
        this.height = 480;
    }

    public Device(int width, int height, boolean fullscreen) {
        try {
            DisplayMode dispMode = new DisplayMode(width, height);
            Display.setDisplayMode(dispMode);
            Display.setFullscreen(fullscreen);
            Display.create();
            Keyboard.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
            System.exit(0);
        } finally {
            this.width = width;
            this.height = height;
            this.firstLoop = true;
            initGL();
        }
    }

    private void initGL() {
        backDrop = new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
        glClearColor(backDrop.x, backDrop.y, backDrop.z, backDrop.w);
        glDisable(GL_LIGHTING);
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, this.width, 0, this.height, 1, 100);
        glPushMatrix();
        glMatrixMode(GL11.GL_MODELVIEW);
        glLoadIdentity();
        glPushMatrix();
    }

    public void destroyGL() {
        Display.destroy();
        Keyboard.destroy();
    }

    public boolean loopGL() {
        if (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (!firstLoop) {
                Display.update();
                Display.sync(60);
            } else firstLoop = false;
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            GL11.glColor3f(backDrop.x, backDrop.y, backDrop.z);
        } else return false;
        return true;
    }
}