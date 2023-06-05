package painPaint.shape;

import java.awt.*;

public class Point extends AShape {
    int x;
    int y;
    Point secPoint;
    public Point(int x, int y) {
        super(null, null, 1, false);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
//        g.drawLine(x, y, secPoint.x, secPoint.y);
    }

    @Override
    public void resize(Point desPoint) {
//        this.secPoint.setX(desPoint.x);
//        this.secPoint.setY(desPoint.y);
    }
}
