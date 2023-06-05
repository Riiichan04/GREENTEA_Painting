//package painPaint.event;
//
//import painPaint.shape.AShape;
//import painPaint.shape.Circle;
//import painPaint.shape.Line;
//import painPaint.shape.Rectangle;
//
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.util.ArrayList;
//import java.util.Stack;
//
//public class MouseEvent extends MouseAdapter {
//    Stack<AShape> listShape = new Stack<>();
//    ArrayList<painPaint.shape.Point> listPoint;
//    ArrayList<ArrayList<painPaint.shape.Point>> redrawPoint = new ArrayList<>();
//    AShape lastShape = null;
//    painPaint.shape.Point lastPoint;
//    boolean isStarted, isPencil;
//    int shapeType;
//    int stroke = 1;
//    public MouseEvent() {
//
//    }
//    @Override
//    public void mouseClicked(java.awt.event.MouseEvent e) {
//        if (isStarted) {
//            //Click lần thứ 2 => Kết thúc vẽ
//            isStarted = false;
//            lastShape = null;
//            isPencil = false;
//        } else {
//            //Click lần 1 để bắt đầu vẽ
//            isStarted = true;
//            listPoint = new ArrayList<>();
//            switch (shapeType) {
//                //Với shapeType = 1 -> Vẽ hình tròn
//                case 1: {
//                    //Click lần 1 -> Tạo sẵn 1 hình tròn với bán kính = 0
//                    //Vẽ bằng lastShape
//                    lastShape = new Circle(new painPaint.shape.Point(e.getX(), e.getY()), Color.BLACK, stroke, false);
//                    listShape.push(lastShape);
//                    break;
//                }
//                case 2: {
//                    lastShape = new Line(new painPaint.shape.Point(e.getX(), e.getY()), Color.BLACK, stroke, false);
//                    listShape.push(lastShape);
//                    break;
//                }
//                case 3: {
//                    lastShape = new Rectangle(new painPaint.shape.Point(e.getX(), e.getY()), Color.BLACK, stroke, false);
//                    listShape.push(lastShape);
//                    break;
//                }
//                case 4: {
//                    lastPoint = new painPaint.shape.Point(e.getX(), e.getY());
//                    listPoint.add(lastPoint);
//                    redrawPoint.add(listPoint);
//                    isPencil = true;
//                    break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void mouseMoved(java.awt.event.MouseEvent e) {
//        if (isStarted && isPencil) {
//            lastPoint = new painPaint.shape.Point(e.getX(), e.getY());
//            redrawPoint.get(redrawPoint.size() - 1).add(lastPoint);
//            if (lastShape != null) {
//                lastShape.resize(new painPaint.shape.Point(e.getX(), e.getY()));
//            }
////            repaint();
//        } else if (isStarted) {
//            lastShape.resize(new painPaint.shape.Point(e.getX(), e.getY()));
////            repaint();
//        }
//    }
//}
