/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.main;

import evolvio.entity.Entity;
import evolvio.models.RawModel;
import evolvio.models.TexturedModel;
import evolvio.render.lwjgl.Camera;
import evolvio.render.lwjgl.Light;
import evolvio.render.lwjgl.OBJLoader;
import evolvio.render.lwjgl.ObjectLoader;
import evolvio.render.lwjgl.Renderer;
import evolvio.render.lwjgl.Window;
import evolvio.render.lwjgl.shaders.StaticShader;
import evolvio.textures.Texture;
import evolvio.textures.TextureLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

/**
 *
 * @author quentin
 */
public class LWJGLMain {

    private Window WINDOW;
    private Renderer renderer;
    private StaticShader staticShader;
    private Camera cam;
    private Light light;
    private ObjectLoader loader;

    //for testing only
    private Entity dragon;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LWJGLMain main = new LWJGLMain();
        main.startUp();
        main.loop();
        main.cleanUp();
    }

    public void startUp() {
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Could not initialize glfw!");
        }

        WINDOW = new Window();
        WINDOW.setSize(1270, 720);
        WINDOW.setFps(60);
        WINDOW.createWindow("My Window");

        GL.createCapabilities(true);
        init();
    }

    private void init() {
        cam = new Camera(WINDOW.getWidth(), WINDOW.getHeight());
        loader = new ObjectLoader();
        staticShader = new StaticShader();
        renderer = new Renderer();
        light = new Light(new Vector3f(-20, 0, 0), new Vector3f(1, 1, 0.5f));

        initEntity();
    }

    private void initEntity() {
        RawModel rawDragon = OBJLoader.loadOBJModel("dragon", loader);
        Texture texture = null;
        try {
            int textureID = TextureLoader.loadTexture("white");
            texture = new Texture(textureID);
            texture.setReflectivity(1);
            texture.setShineDamper(10);
        } catch (IOException ex) {
            Logger.getLogger(LWJGLMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        TexturedModel texturedDragon = new TexturedModel(rawDragon, texture);
        dragon = new Entity(texturedDragon, new Vector3f(0, -5, -15), new Vector3f(0, 0, 0), 1f);
    }

    public void loop() {
        while (!WINDOW.shouldClose()) {
            update();
            render();
            WINDOW.swapBuffers();
        }
    }

    private void update() {
        WINDOW.update();
        dragon.rotate(0, 0.5f, 0);
    }

    private void render() {
        renderer.prepare();

        staticShader.start();
        staticShader.setLight(light);
        renderer.render(dragon, staticShader, cam);

        staticShader.stop();
    }

    public void cleanUp() {
        WINDOW.cleanUp();
        staticShader.cleanUp();
        loader.cleanUp();
    }

}
