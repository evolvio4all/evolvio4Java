package evolvio.ui.renderers;

import evolvio.ui.Renderer;
import evolvio.ui.domain.Circle;

import java.awt.Color;
import java.awt.Graphics2D;

public class CircleRenderer implements Renderer<Circle> {
    @Override
    public void render(Graphics2D graphics, Circle rendered) {
        Color color = rendered.getColor();
        int x = (int) rendered.getX();
        int y = (int) rendered.getY();
        int radius = (int) rendered.getRadius();
        graphics.setColor(color);
        graphics.drawOval(x, y, radius, radius);
    }
}
