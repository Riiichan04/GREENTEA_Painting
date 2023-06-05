package painPaint.shape;

import java.awt.*;

public class Triangle extends AShape {
    Point secPoint;
    public Triangle(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        secPoint = origin;
    }


    @Override
    public void draw(Graphics g) {
        int[] x = {origin.x, (origin.x + secPoint.x)/2, secPoint.x};
        int[] y = {secPoint.y, origin.y, secPoint.y};
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(color);
        if (isFill) g.fillPolygon(x, y, 3);
        else g.drawPolygon(x, y, 3);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}
