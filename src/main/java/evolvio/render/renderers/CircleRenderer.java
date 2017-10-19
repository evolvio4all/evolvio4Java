package evolvio.render.renderers;

import evolvio.render.Circle;
import evolvio.render.IRenderer;

import java.awt.Color;
import java.awt.Graphics2D;

public class CircleRenderer implements IRenderer<Circle> {
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
