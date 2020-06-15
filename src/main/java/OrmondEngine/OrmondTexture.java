package OrmondEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;
import org.lwjgl.stb.STBImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class OrmondTexture {

    private int id;
    private int width;
    private int height;

    public OrmondTexture(String filename) {

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = stbi_load("resources\\" + filename, width, height, comp, 4);

        id = glGenTextures();
        this.width = width.get();
        this.height = height.get();
        
        glBindTexture(GL_TEXTURE_2D, id);
        
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        try {
            stbi_image_free(data);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Well that didn't work...");
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
}