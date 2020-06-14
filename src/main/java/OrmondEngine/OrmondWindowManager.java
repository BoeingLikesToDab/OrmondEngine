package OrmondEngine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class OrmondWindowManager {

    public static int halfOfWidth;
    public static int halfOfHeight;
    static GLFWVidMode videoMode;

    public static void init() {
        videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        halfOfWidth = glfwGetVideoMode(glfwGetPrimaryMonitor()).width()/2;
        halfOfHeight = glfwGetVideoMode(glfwGetPrimaryMonitor()).height()/2;
    }

    public static long newWindow(CharSequence title, int width, int height, long monitor, long share) {
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(width, height, title, monitor, share);
        if (window == 0) {
			throw new IllegalStateException("Failed to create window");
        }
        glfwShowWindow(window);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        return window;
    }

}