package painPaint.shape;

import java.awt.*;

public abstract class AShape {
    protected Point origin;
    protected Color color;
    protected int stroke;
    protected boolean isFill;

    public AShape(Point origin, Color color, int stroke, boolean isFill) {
        this.origin = origin;
        this.color = color;
        this.stroke = stroke;
        this.isFill = isFill;
    }
    public abstract void draw(Graphics g);
    public abstract void resize(Point desPoint);
    public Color getColor() {return this.color;}
}