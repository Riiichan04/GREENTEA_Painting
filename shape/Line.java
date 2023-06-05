package painPaint.shape;

import java.awt.*;

public class Line extends AShape {
    private Point secPoint;
    public Line(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        this.secPoint = new Point(origin.x, origin.y);
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawLine(origin.x, origin.y, secPoint.x, secPoint.y);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}
