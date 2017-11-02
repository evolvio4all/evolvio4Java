package evolvio.io.keyboard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardIO extends GLFWKeyCallback {
    private final ConcurrentLinkedQueue<BiConsumer<Key, KeyAction>> commandQueue;
    private final Map<Key, Map<KeyAction, BiConsumer<Key, KeyAction>>> keyCommands = new ConcurrentHashMap<>();

    public KeyboardIO(ConcurrentLinkedQueue<BiConsumer<Key, KeyAction>> commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void setCallback(Key key, BiConsumer<Key, KeyAction> callback) {
        Map<KeyAction, BiConsumer<Key, KeyAction>> validKeyActionCommands = getValidKeyActionCommands(key);
        for (KeyAction keyAction : KeyAction.getAll()) {
            validKeyActionCommands.put(keyAction, callback);
        }
    }

    public void setCallback(Key key, BiConsumer<Key, KeyAction> callback, KeyAction keyAction) {
        Map<KeyAction, BiConsumer<Key, KeyAction>> validKeyActionCommands = getValidKeyActionCommands(key);
        validKeyActionCommands.put(keyAction, callback);
    }

    public void removeCallback(Key key) {
        Map<KeyAction, BiConsumer<Key, KeyAction>> keyActionCommands = keyCommands.get(key);
        if (keyActionCommands != null) {
            keyActionCommands.clear();
        }
    }

    public void removeCallback(Key key, KeyAction keyAction) {
        Map<KeyAction, BiConsumer<Key, KeyAction>> keyActionCommands = keyCommands.get(key);
        if (keyActionCommands != null) {
            keyActionCommands.remove(keyAction);
        }
    }

    private Map<KeyAction, BiConsumer<Key, KeyAction>> getValidKeyActionCommands(Key key) {
        return keyCommands.computeIfAbsent(key, k -> new ConcurrentHashMap<>());
    }

    @Override
    public void invoke(long window, int keyCode, int scancode, int keyActionCode, int mods) {
        Key key = Key.fromCode(keyCode);
        if (key != null) {
            Map<KeyAction, BiConsumer<Key, KeyAction>> keyActionCommands = keyCommands.get(key);
            if (keyActionCommands != null) {
                KeyAction keyAction = KeyAction.fromCode(keyActionCode);
                if (keyAction != null) {
                    BiConsumer<Key, KeyAction> command = keyActionCommands.get(keyAction);
                    if (command != null) {
                        commandQueue.add(command);
                    }
                }
            }
        }
    }
}
