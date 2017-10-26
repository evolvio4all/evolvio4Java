/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.main;

import evolvio.render.lwjgl.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

/**
 *
 * @author quentin
 */
public class LWJGLMain {

    private static Window WINDOW;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startUp();
        gameLoop();
        cleanUp();
    }

    private static void startUp() {
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Could not initialize glfw!");
        }

        WINDOW = new Window();
        WINDOW.setSize(1270, 720);
        WINDOW.setFps(60);
        WINDOW.createWindow("My Window");

        GL.createCapabilities(true);
    }

    private static void gameLoop() {
        while (!WINDOW.shouldClose()) {
            update();
            render();
            WINDOW.swapBuffers();
        }
    }

    private static void update() {
        WINDOW.update();
    }

    private static void render() {

    }

    private static void cleanUp() {
        WINDOW.cleanUp();
    }

}
