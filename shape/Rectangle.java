package painPaint.shape;

import java.awt.*;

public class Rectangle extends AShape{
    private Point secPoint;
    public Rectangle(Point origin, Color color, int stroke, boolean isFill) {
        super(origin, color, stroke, isFill);
        secPoint = origin;
    }


    @Override
    public void draw(Graphics g) {
        int x = Math.min(origin.x, secPoint.x);
        int y = Math.min(origin.y, secPoint.y);

        int w = Math.abs(origin.x - secPoint.x);
        int h = Math.abs(origin.y - secPoint.y);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(color);
        if (!isFill) g2d.drawRect(x, y, w, h);
        else g2d.fillRect(x, y, w, h);
    }

    @Override
    public void resize(Point desPoint) {
        this.secPoint = desPoint;
    }
}

