package painPaint.shape;

import java.awt.*;

public class Rhombus extends AShape{
    Point secPoint;
    public Rhombus(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        secPoint = origin;
    }


    @Override
    public void draw(Graphics g) {
        int[] x = {origin.x, (secPoint.x + origin.x)/2, secPoint.x, (secPoint.x + origin.x)/2};
        int[] y = {(secPoint.y + origin.y)/2, origin.y, (secPoint.y + origin.y)/2, secPoint.y, };
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(color);
        if (isFill) g2d.fillPolygon(x, y, 4);
        else g2d.drawPolygon(x, y, 4);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}
