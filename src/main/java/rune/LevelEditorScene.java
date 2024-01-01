package rune;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {
    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene() {
    System.out.println("Inside level editor scene");
    }

    // This method is called once when the scene is created
    @Override
    public void update(float dt) {
        System.out.println(" " + (1.0f / dt) + " FPS");

        // Change the scene when the space bar is pressed
        if (!KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }

        // Change the scene after 2 seconds
        if (changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;

            // Change the background color
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        } else if (changingScene) {
            Window.changeScene(1);
        }
    }
}
