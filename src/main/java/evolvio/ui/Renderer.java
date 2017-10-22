package evolvio.ui;

import java.awt.Graphics2D;

public interface Renderer<T> {
    void render(Graphics2D graphics, T rendered);
}
