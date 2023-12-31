package rune;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    private final boolean[] keyPressed = new boolean[350]; // 350 is the max number of keys on a keyboard

    private KeyListener() {

    }

    // Get the instance of the key listener
    private static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    // Callback for when a key is pressed or released
    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    // Check what key is being pressed
    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
}
