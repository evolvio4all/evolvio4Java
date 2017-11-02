package evolvio.io.mouse;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePositionIO extends GLFWCursorPosCallback {
    private final ConcurrentLinkedQueue<Runnable> commandQueue;
    private final BiDoubleConsumer offsetCommand;

    public MousePositionIO(ConcurrentLinkedQueue<Runnable> commandQueue, BiDoubleConsumer offsetCommand) {
        this.commandQueue = commandQueue;
        this.offsetCommand = offsetCommand;
    }


    @Override
    public void invoke(long window, double xoffset, double yoffset) {
        Runnable queuedCommand = () -> offsetCommand.accept(xoffset, yoffset);
        commandQueue.add(queuedCommand);
    }
}
