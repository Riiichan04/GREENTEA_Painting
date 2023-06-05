package painPaint.shape;

import java.awt.*;

public class Trapezoid extends AShape{
    Point secPoint;
    public Trapezoid(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        secPoint = origin;
    }


    @Override
    public void draw(Graphics g) {
        //Công thức hình đồng hồ cát
//        int[] x = {origin.x, (origin.x - secPoint.x), (origin.x - secPoint.x)*2, secPoint.x};
//        int[] y = {origin.y, (origin.y - secPoint.y), (origin.y - secPoint.y)*2, secPoint.y};
        //Công thức hình thang
        int[] x = {origin.x, origin.x + (secPoint.x - origin.x)/3, origin.x + (secPoint.x - origin.x)*2/3, secPoint.x};
        int[] y = {secPoint.y, origin.y, origin.y, secPoint.y};
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(color);
        if (isFill) g.fillPolygon(x, y, 4);
        else g.drawPolygon(x, y, 4);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}
