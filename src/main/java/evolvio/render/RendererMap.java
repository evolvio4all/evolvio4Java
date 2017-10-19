package evolvio.render;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public final class RendererMap {
    private final Map<Class<?>, IRenderer<?>> renderers = new HashMap<>();

    public <T> void addRenderer(Class<T> renderedClass, IRenderer<T> renderer) {
        renderers.put(renderedClass, renderer);
    }

    @SuppressWarnings("unchecked")
    public <T> void render(Graphics2D graphics, T rendered) {
        Class<T> renderedClass = (Class<T>) rendered.getClass();
        IRenderer<T> renderer = (IRenderer<T>) renderers.get(renderedClass);
        if (renderer != null) {
            renderer.render(graphics, rendered);
        }
    }
}
