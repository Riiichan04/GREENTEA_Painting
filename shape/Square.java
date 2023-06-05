package painPaint.shape;

import java.awt.*;

public class Square extends AShape {
    Point secPoint;
    public Square(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        secPoint = origin;
    }


    @Override
    public void draw(Graphics g) {
        int w = Math.min((secPoint.x - origin.x), (secPoint.y - origin.y));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.stroke));
        g2d.setColor(color);
//        g2d.drawRect(origin.x + w, origin.y + w, w, w);
        int[] x = {origin.x + w, origin.x, origin.x, origin.x + w};
        int[] y = {origin.y + w, origin.y + w, origin.y, origin.y};
        if (isFill) g2d.fillPolygon(x, y, 4);
        else g2d.drawPolygon(x, y, 4);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}
