/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Quentin
 */
public class TextureLoader {

    public static int loadTexture(String textureName) throws IOException {
        BufferedImage read;
        int id;
        try {
            read = ImageIO.read(new File("./res/texture/" + textureName + ".png"));
            int width = read.getWidth();
            int height = read.getHeight();
            int[] pixelsRaw = read.getRGB(0, 0, width, height, null, 0, width);
            System.out.println("rawPixels: " + pixelsRaw.length);
            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixelsRaw[x * width + y];
                    pixels.put((byte) ((pixel >> 16) & 0xFF)); // red
                    pixels.put((byte) ((pixel >> 8) & 0xFF)); // green
                    pixels.put((byte) (pixel & 0xFF)); //blue
                    pixels.put((byte) ((pixel >> 24) & 0xFF)); // alpha
                }
            }
            pixels.flip();

            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

        } catch (IOException ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return id;
    }
}
