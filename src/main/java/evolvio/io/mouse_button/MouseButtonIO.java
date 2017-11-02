package evolvio.io.mouse_button;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButtonIO extends GLFWMouseButtonCallback {
    private final ConcurrentLinkedQueue<BiConsumer<MouseButton, MouseButtonAction>> commandQueue;
    private final Map<MouseButton, Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>>> mouseButtonCommands = new ConcurrentHashMap<>();

    public MouseButtonIO(ConcurrentLinkedQueue<BiConsumer<MouseButton, MouseButtonAction>> commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void setCallback(MouseButton MouseButton, BiConsumer<MouseButton, MouseButtonAction> callback) {
        Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> validMouseActionCommands = getValidMouseActionCommands(MouseButton);
        for (MouseButtonAction MouseButtonAction : MouseButtonAction.getAll()) {
            validMouseActionCommands.put(MouseButtonAction, callback);
        }
    }

    public void setCallback(MouseButton mouseButton, BiConsumer<MouseButton, MouseButtonAction> callback, MouseButtonAction mouseButtonAction) {
        Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> validMouseActionCommands = getValidMouseActionCommands(mouseButton);
        validMouseActionCommands.put(mouseButtonAction, callback);
    }

    public void removeCallback(MouseButton mouseButton) {
        Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> mouseActionCommands = mouseButtonCommands.get(mouseButton);
        if (mouseActionCommands != null) {
            mouseActionCommands.clear();
        }
    }

    public void removeCallback(MouseButton mouseButton, MouseButtonAction mouseButtonAction) {
        Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> mouseActionCommands = mouseButtonCommands.get(mouseButton);
        if (mouseActionCommands != null) {
            mouseActionCommands.remove(mouseButtonAction);
        }
    }

    private Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> getValidMouseActionCommands(MouseButton mouseButton) {
        return mouseButtonCommands.computeIfAbsent(mouseButton, k -> new ConcurrentHashMap<>());
    }

    @Override
    public void invoke(long window, int mouseButtonCode, int mouseActionCode, int mods) {
        MouseButton mouseButton = MouseButton.fromCode(mouseButtonCode);
        if (mouseButton != null) {
            Map<MouseButtonAction, BiConsumer<MouseButton, MouseButtonAction>> MouseActionCommands = mouseButtonCommands.get(mouseButton);
            if (MouseActionCommands != null) {
                MouseButtonAction mouseButtonAction = MouseButtonAction.fromCode(mouseActionCode);
                if (mouseButtonAction != null) {
                    BiConsumer<MouseButton, MouseButtonAction> command = MouseActionCommands.get(mouseButtonAction);
                    if (command != null) {
                        commandQueue.add(command);
                    }
                }
            }
        }
    }
}