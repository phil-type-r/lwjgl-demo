import org.lwjgl.opengl.*;

/**
 * Created by phil-type-r on 16.03.14.
 */
public class Main {
    public static void main(String[] args) {
        Device dev = new Device();

        Display.setTitle("Example By phil-type-r");

        while (dev.loopGL()) {
            // la la la
        }

        dev.destroyGL();
    }
}