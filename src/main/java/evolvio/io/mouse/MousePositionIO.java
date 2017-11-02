package evolvio.io.mouse;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePositionIO extends GLFWCursorPosCallback {
    private final ConcurrentLinkedQueue<Runnable> commandQueue;
    private BiDoubleConsumer offsetCommand;

    public MousePositionIO(ConcurrentLinkedQueue<Runnable> commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void setCommand(BiDoubleConsumer offsetCommand) {
        this.offsetCommand = offsetCommand;
    }

    @Override
    public void invoke(long window, double xoffset, double yoffset) {
        if (offsetCommand != null) {
            Runnable queuedCommand = () -> offsetCommand.accept(xoffset, yoffset);
            commandQueue.add(queuedCommand);
        }
    }
}
