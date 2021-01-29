package OrmondEngine;

import static org.lwjgl.glfw.GLFW.*;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import OrmondEngine.OrmondTexture;

public class OrmondWindowManager {

    public static int halfOfWidth;
    public static int halfOfHeight;
    static GLFWVidMode videoMode;
    static boolean cutscene = true;

    public static void init() {
        videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        halfOfWidth = glfwGetVideoMode(glfwGetPrimaryMonitor()).width() / 2;
        halfOfHeight = glfwGetVideoMode(glfwGetPrimaryMonitor()).height() / 2;
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

        glClearColor(1.0f,1.0f,1.0f,1.0f);
        
        OrmondTexture logo = null;
        try {
            logo = new OrmondTexture("logo.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Thread thread = new Thread() {
        	public void run() {
        		try {
        			Thread.sleep(3000);
        			cutscene = false;
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        };
        
        thread.start();
        
        while (cutscene == true) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            glEnable(GL_TEXTURE_2D);
            
            logo.bind();
            glBegin(GL_QUADS);
            	glTexCoord2f(1, 0);
            	glVertex2f(1f, 1f);
            	glTexCoord2f(0, 0);
            	glVertex2f(-1f, 1f);
            	glTexCoord2f(0, 1);
            	glVertex2f(-1f, -1f);
            	glTexCoord2f(1, 1);
            	glVertex2f(1f, -1f);
            glEnd();
            
            glfwSwapBuffers(window);
        }
        return window;
    }

}