package evolvio.io.keyboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.glfw.GLFW;

public enum KeyAction {
    Press(GLFW.GLFW_PRESS),
    Repeat(GLFW.GLFW_REPEAT),
    Release(GLFW.GLFW_RELEASE);
    private static final Map<Integer, KeyAction> KEY_ACTIONS_BY_CODE = new HashMap<>();
    // the values() method re-creates a new array every time it's called, so cache it here
    private static final List<KeyAction> ALL = Arrays.asList(values());
    private final int code;

    static {
        for (KeyAction keyAction : values()) {
            KEY_ACTIONS_BY_CODE.put(keyAction.code, keyAction);
        }
    }

    KeyAction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static KeyAction fromCode(int code) {
        return KEY_ACTIONS_BY_CODE.get(code);
    }

    public static List<KeyAction> getAll() {
        return ALL;
    }
}
