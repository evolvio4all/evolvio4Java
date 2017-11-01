package evolvio.render.lwjgl;

import evolvio.io.Input;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Window {

    private long window;

    private int width, height;
    private boolean fullscreen;
    private boolean hasResized;
    private GLFWWindowSizeCallback windowSizeCallback;
    private double fps = 60;
    private double timePerFrame;
    private boolean limitFps = true;
    private long lastSwap;

    private Input input;

    public void setFps(double fps) {
        this.fps = fps;
        updateTimePerFrame();
    }

    public void setLimitFps(boolean limitFps) {
        this.limitFps = limitFps;
    }

    private void updateTimePerFrame() {
        timePerFrame = 1000 / fps;
    }

    private void setLocalCallbacks() {
        windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long argWindow, int argWidth, int argHeight) {
                setSize(argWidth, argHeight);
                hasResized = true;
            }
        };

        glfwSetWindowSizeCallback(window, windowSizeCallback);
    }

    public Window() {
        setSize(640, 480);
        setFullscreen(false);
        hasResized = false;
        updateTimePerFrame();
    }

    public void createWindow(String title) {
        window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            throw new IllegalStateException("Failed to create window!");
        }

        if (!fullscreen) {
            GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vid.width() - width) / 2, (vid.height() - height) / 2);
        }

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

        input = new Input(window);
        setLocalCallbacks();
    }

    public void cleanUp() {
        glfwFreeCallbacks(window);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void swapBuffers() {
        long now = System.currentTimeMillis();
        double diff = now - lastSwap;
        if (limitFps && diff < timePerFrame) {
            try {
                Thread.sleep((long) (timePerFrame - diff));
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        glfwSwapBuffers(window);
        lastSwap = now;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public void update() {
        hasResized = false;
        input.update();
        glfwPollEvents();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean hasResized() {
        return hasResized;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public long getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }
}
