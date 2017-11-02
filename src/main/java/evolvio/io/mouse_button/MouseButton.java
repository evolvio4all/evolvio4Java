package evolvio.io.mouse_button;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.glfw.GLFW;

public enum MouseButton {
    Left(GLFW.GLFW_MOUSE_BUTTON_LEFT),
    Middle(GLFW.GLFW_MOUSE_BUTTON_MIDDLE),
    Right(GLFW.GLFW_MOUSE_BUTTON_RIGHT);
    private static final Map<Integer, MouseButton> MOUSE_BUTTONS_BY_CODE = new HashMap<>();
    // the values() method re-creates a new array every time it's called, so cache it here
    private static final List<MouseButton> ALL = Arrays.asList(values());
    private final int code;

    static {
        for (MouseButton mouseButton : values()) {
            MOUSE_BUTTONS_BY_CODE.put(mouseButton.code, mouseButton);
        }
    }

    MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButton fromCode(int code) {
        return MOUSE_BUTTONS_BY_CODE.get(code);
    }

    public static List<MouseButton> getAll() {
        return ALL;
    }
}
