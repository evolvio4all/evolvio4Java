package evolvio.world;

import java.util.HashMap;
import java.util.Map;

public class WorldObject {
    private final Map<Class<? extends WorldObjectComponent>, WorldObjectComponent> components = new HashMap<>();

    public void addComponent(WorldObjectComponent component) {
        components.put(component.getClass(), component);
    }

    public <C extends WorldObjectComponent> C getComponent(Class<C> componentClass) {
        WorldObjectComponent rawComponent = components.get(componentClass);
        @SuppressWarnings("unchecked")
        C typedComponent = (C) rawComponent;
        return typedComponent;
    }
}
