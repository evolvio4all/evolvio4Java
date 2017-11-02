package evolvio.io.mouse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.glfw.GLFW;

public enum MouseButtonAction {
    PRESS(GLFW.GLFW_PRESS),
    REPEAT(GLFW.GLFW_REPEAT),
    RELEASE(GLFW.GLFW_RELEASE);
    private static final Map<Integer, MouseButtonAction> KEY_ACTIONS_BY_CODE = new HashMap<>();
    // the values() method re-creates a new array every time it's called, so cache it here
    private static final List<MouseButtonAction> ALL = Arrays.asList(values());
    private final int code;

    static {
        for (MouseButtonAction mouseButtonAction : values()) {
            KEY_ACTIONS_BY_CODE.put(mouseButtonAction.code, mouseButtonAction);
        }
    }

    MouseButtonAction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButtonAction fromCode(int code) {
        return KEY_ACTIONS_BY_CODE.get(code);
    }

    public static List<MouseButtonAction> getAll() {
        return ALL;
    }
}
