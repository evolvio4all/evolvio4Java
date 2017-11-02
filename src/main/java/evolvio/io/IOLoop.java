package evolvio.io;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class IOLoop implements AutoCloseable {
    private final ConcurrentLinkedQueue<Runnable> commandQueue = new ConcurrentLinkedQueue<>();
    private final GLFWKeyCallback keyCallback = null;
    private final GLFWMouseButtonCallback mouseButtonCallback = null;
    private final GLFWCursorPosCallback mousePositionCallback = null;

    public IOLoop(long window) {
        addUserInputResponses();
        setGLFWCallbacks();
    }

    private void addUserInputResponses() {
        // TODO
    }

    private void setGLFWCallbacks() {
        // TODO uncomment when callbacks are available
        /*
        GLFW.glfwSetKeyCallback(window, keyCallback);
        GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);
        GLFW.glfwSetCursorPosCallback(window, mousePositionCallback);
        */
    }

    public void drainEvents() {
        for (Runnable command = commandQueue.poll(); command != null; command = commandQueue.poll()) {
            command.run();
        }
    }

    @Override
    public void close() {
        // TODO uncomment when callbacks are available
        /*
        keyCallback.free();
        mouseButtonCallback.free();
        mousePositionCallback.free();
        */
    }
}
