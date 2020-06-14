package OrmondEngine;

import static org.lwjgl.glfw.GLFW.*;

public class OrmondApplicationManager {
    public static void startApp() {
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialise GLFW");
        }
    }
}