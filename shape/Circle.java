package painPaint.shape;

import java.awt.*;

public class Circle extends AShape{
    private int radius;
    public Circle(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        this.radius = 0;
    }
    @Override
    public void draw(Graphics g) {
        int x = origin.x - radius;
        int y = origin.y - radius;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(color);
        if (isFill) g2d.fillOval(x, y, 2*radius, 2*radius);
        else g2d.drawOval(x, y, 2*radius, 2*radius);
    }
    @Override
    public void resize(Point desPoint) {
        int dx = desPoint.x - origin.x;
        int dy = desPoint.y - origin.y;
        radius = (int) Math.sqrt(dx*dx + dy*dy);
    }

}
