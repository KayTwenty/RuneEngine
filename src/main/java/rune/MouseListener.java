package rune;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private final boolean[] mouseButtonPressed = new boolean[9]; // 9 is the max number of mouse buttons
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    // Get the instance of the mouse listener
    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    // Callback for when the mouse is moved
    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    // Callback for when a mouse button is pressed or released
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    // Callback for when the scroll wheel is moved
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    // Reset the scroll wheel
    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    // Get the x position of the mouse
    public static float getX() {
        return (float) get().xPos;
    }

    // Get the y position of the mouse
    public static float getY() {
        return (float) get().yPos;
    }

    // Get the change in x position
    public static float getDx() {
        return (float) (get().lastX - get().xPos);
    }

    // Get the change in y position
    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    // Get the change in x position of the scroll wheel
    public static float getScrollX() {
        return (float) get().scrollX;
    }

    // Get the change in y position of the scroll wheel
    public static float getScrollY() {
        return (float) get().scrollY;
    }

    // Get whether a mouse button is being dragged
    public static boolean isDragging() {
        return get().isDragging;
    }

    // Get whether a mouse button is pressed
    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }

}
