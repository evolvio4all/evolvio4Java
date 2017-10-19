package evolvio.render;

import java.awt.Graphics2D;

public interface IRenderer<T> {
    void render(Graphics2D graphics, T rendered);
}
