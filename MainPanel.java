package painPaint;

import painPaint.shape.*;
import painPaint.shape.Rectangle;
//import paint.shape.Point;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.prefs.BackingStoreException;

public class MainPanel extends JPanel {
    //ID của các hình
    public static final int PENCIL_ID = 1;
    public static final int LINE_ID = 2;
    public static final int CIRCLE_ID = 3;
    public static final int RECT_ID = 4;
    public static final int SQUARE_ID = 5;
    public static final int TRAPEZOID_ID = 6;
    public static final int PARALLELOGRAM_ID = 7;
    public static final int TRIANGLE_ID = 8;
    public static final int TRIANGLES_ID = 9;
    public static final int RHOMBUS_ID = 10;
    public static final int ERASER_ID = 11;
    //Giá trị mặc định
    public int shapeType = LINE_ID;
    //Kích thước của MainPanel
    private int mainW, mainH;
    //Nhóm Button chứa các button
    private ButtonGroup buttonGroup = new ButtonGroup();
    //Độ dày của bút vẽ
    private int stroke = 1;
    //Màu sắc
    public Color[] arrColor = {Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.WHITE};
    //Màu mặc định
    private Color color = Color.BLACK;
    private int menuH = 75;
    private boolean isFill = false;
    private Color pencilColor = Color.BLACK;
    private boolean isPencil;
    ArrayList<Color> listPencilColor = new ArrayList<>();
    ArrayList<Integer> listPencilStroke = new ArrayList<>();
    Stack<AShape> listShape = new Stack<>();
    Stack<Color> listColor = new Stack<>();
    ArrayList<Point> listPoint;
    ArrayList<ArrayList<Point>> redrawPoint = new ArrayList<>();
    public MainPanel(int w, int h) {
        listPencilColor.add(Color.BLACK);
        listPencilStroke.add(stroke);
        setLayout(null);
        setBounds(0, 0, w, h);
        mainW = w;
        mainH = h;
        BTNPanel btnPanel = new BTNPanel(this.getHeight() / 18 + 10, this.getHeight());
        add(btnPanel);
        DrawPanel dp = new DrawPanel();
        add(dp);
        MenuPanel mp = new MenuPanel();
        add(mp);
    }

    class ShapeButton extends JButton {
        private int padding = 5;
        private int shapeType;

        public ShapeButton(int shapeType, ActionListener actionListener, int x, int y, int w, int h) {
            setBounds(x, y, w, h);
            setBackground(new Color(60, 60, 60));
            setForeground(Color.WHITE);
            setBorder(null);
            this.shapeType = shapeType;
            addActionListener(actionListener);
            setActionCommand("" + shapeType);
            buttonGroup.add(this);
            addMouseListener(setHover(buttonGroup));
        }

        //Vẽ Button
        @Override
        protected void paintComponent(Graphics g) {
            //Vẽ Button
            super.paintComponent(g);
            switch (shapeType) {
                case PENCIL_ID -> {
                    ImageIcon penicon = new ImageIcon("JSWing2023\\src\\painPaint\\buttonImg\\pencilBTN.png");
                    Image imgIcon = penicon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    penicon = new ImageIcon(imgIcon);
                    setIcon(penicon);
                }
                case LINE_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawLine(padding + 5, padding + 5, getWidth() - padding - 5, getWidth() - padding - 5);
                }
                case CIRCLE_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawOval(padding, padding, getWidth() - 2 * padding, getHeight() - 2 * padding);
                }
                case RECT_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawRect(padding, padding + 10, this.getWidth() - padding * 2, this.getHeight() - padding * 2 - 10);
                }
                case SQUARE_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawRect(padding + 5, padding + 5, this.getWidth() - padding * 2 - 10, this.getWidth() - padding * 2 - 10);
                }
                case TRAPEZOID_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    int[] listX = {padding, padding * 2, this.getWidth() - padding * 2, this.getWidth() - padding};
                    int[] listY = {this.getHeight() - padding * 3, padding, padding, this.getHeight() - padding * 3};
                    g2d.drawPolygon(listX, listY, 4);
                }
                case PARALLELOGRAM_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    int[] listX = {padding, this.getWidth() - padding * 4, this.getWidth() - padding, this.getWidth() - padding * 4};
                    int[] listY = {this.getHeight() - padding, padding, padding, this.getHeight() - padding};
                    g2d.drawPolygon(listX, listY, 4);
                }
                case TRIANGLE_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    int[] listX = {padding, this.getWidth() / 2, this.getWidth() - padding};
                    int[] listY = {this.getHeight() - padding * 2, padding, this.getHeight() - padding * 2};
                    g2d.drawPolygon(listX, listY, 3);
                }
                case TRIANGLES_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    int[] listX = {padding * 2, padding * 2, this.getWidth() - padding * 2};
                    int[] listY = {padding * 2, this.getWidth() - padding * 2, this.getWidth() - padding * 2};
                    g2d.drawPolygon(listX, listY, 3);
                }
                case RHOMBUS_ID -> {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    int[] listX = {5, 20, 35, 20};
                    int[] listY = {20, 5, 20, 35};
                    g2d.drawPolygon(listX, listY, 4);
                }
            }
        }

        public MouseAdapter setHover(ButtonGroup bg) {
            return new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    setBackground(new Color(153, 180, 209));
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setBackground(new Color(60, 60, 60));
                }
            };
        }
    }

    class BTNPanel extends JPanel {
        public BTNPanel(int w, int h) {
            setBounds(0, menuH, w, h);
            setLayout(null);
            setBorder(new LineBorder(color, 3));
            setBackground(new Color(60, 60, 60));
            ActionListener shapeAction = e -> {
                shapeType = Integer.parseInt(e.getActionCommand());
            };
            for (int i = 1, locX = 5, locY = 5; i < 11; i++, locY += 60) {
                ShapeButton sb = new ShapeButton(i, shapeAction, locX, locY, this.getHeight() / 18, this.getHeight() / 18);
                add(sb);
            }
        }
    }

    class DrawPanel extends JPanel {

        AShape lastShape = null;
        boolean isStarted;
        Point lastPoint;
        public DrawPanel() {
            setBounds(mainH / 18 + 5, menuH, mainW - (mainH) / 18, mainH);
            setBackground(Color.WHITE);
            setBorder(new LineBorder(color, 3));
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            addMouseListener(ma);
            addMouseMotionListener(ma);
            setLayout(null);
        }

        public MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isStarted) {
                    //Click lần thứ 2 => Kết thúc vẽ
                    isStarted = false;
                    lastShape = null;
                    isPencil = false;
                } else {
                    //Click lần 1 để bắt đầu vẽ
                    isStarted = true;
                    listPoint = new ArrayList<>();
                    switch (shapeType) {
                        //Với shapeType = 1 -> Vẽ hình tròn
                        case PENCIL_ID -> {
                            lastPoint = new Point(e.getX(), e.getY());
                            listPoint.add(lastPoint);
                            redrawPoint.add(listPoint);
                            isPencil = true;
                            listPencilColor.add(color);
                            listPencilStroke.add(stroke);
                        }
                        case LINE_ID -> {
                            listColor.push(color);
                            lastShape = new Line(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case CIRCLE_ID -> {
                            //Click lần 1 -> Tạo sẵn 1 hình tròn với bán kính = 0
                            //Vẽ bằng lastShape
                            listColor.push(color);
                            lastShape = new Circle(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case RECT_ID -> {
                            listColor.push(color);
                            lastShape = new Rectangle(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case SQUARE_ID -> {
                            listColor.push(color);
                            lastShape = new Square(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }

                        case TRAPEZOID_ID -> {
                            listColor.push(color);
                            lastShape = new Trapezoid(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case PARALLELOGRAM_ID -> {
                            listColor.push(color);
                            lastShape = new Parallelogram(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case TRIANGLE_ID -> {
                            listColor.push(color);
                            lastShape = new Triangle(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case TRIANGLES_ID -> {
                            listColor.push(color);
                            lastShape = new TriangleSquare(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                        case RHOMBUS_ID -> {
                            listColor.push(color);
                            lastShape = new Rhombus(new painPaint.shape.Point(e.getX(), e.getY()), listColor.peek(), stroke, isFill);
                            listShape.push(lastShape);
                        }
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isStarted && isPencil) {
                    lastPoint = new Point(e.getX(), e.getY());
                    redrawPoint.get(redrawPoint.size() - 1).add(lastPoint);

                    if (lastShape != null) {
                        lastShape.resize(new painPaint.shape.Point(e.getX(), e.getY()));
                        int x = e.getX();
                        int y = e.getY();
                    }
                    repaint();
                } else if (isStarted) {
                    lastShape.resize(new painPaint.shape.Point(e.getX(), e.getY()));
                    int x = e.getX();
                    int y = e.getY();
                    repaint();
                }
            }

        };

        @Override
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            drawPencil(g);
            for (painPaint.shape.AShape shape : listShape) {
                shape.draw(g);
            }
            repaint();
        }

        public void drawPencil(Graphics g) {
            pencilColor = color;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(stroke));

            if (redrawPoint.size() > 0) {
                ArrayList<Point> listDraw;

                for (int i = 0; i < redrawPoint.size(); i++) {
                    listDraw = redrawPoint.get(i);
                    if (listPencilColor.size() > 1) g2d.setColor(listPencilColor.get(i+1));
                    else g2d.setColor(pencilColor);

                    if (listPencilStroke.size() > 1) g2d.setStroke(new BasicStroke(listPencilStroke.get(i+1)));
                    else g2d.setStroke(new BasicStroke(stroke));

                    if (listDraw.size() < 2) return;
                    Point secondPoint;
                    Point firstPoint = listDraw.get(0);

                    for (int j = 1; j < listDraw.size(); j++) {
                        secondPoint = listDraw.get(j);
                        g2d.drawLine((int) firstPoint.getX(), (int) firstPoint.getY(), (int) secondPoint.getX(), (int) secondPoint.getY());
                        firstPoint = secondPoint;
                    }
                }
            }
        }
    }

    public class MenuPanel extends JPanel {
        int widthMenu;

        public MenuPanel() {
            setBounds(0, 0, mainW, menuH);
            setLayout(null);
            widthMenu = getWidth();
            ColorPanel cp = new ColorPanel();
            ToolKitPanel tkp = new ToolKitPanel();
            add(cp);
            add(tkp);
        }

        public class ToolKitPanel extends JPanel {
            int previewStroke = stroke;

            public ToolKitPanel() {
                setBounds(0, 0, widthMenu / 3, menuH);
                setLayout(null);
                JButton fillBtn = new JButton("Fill");
                fillBtn.setBackground(Color.WHITE);
                fillBtn.setFocusable(false);
                fillBtn.setBounds(10, 10, menuH - 20, menuH - 20);
                fillBtn.addActionListener(e -> {
                    isFill = !isFill;
                });
                JLabel strokeLabel = new JLabel("Độ dày của bút");
                strokeLabel.setBounds(menuH, 5, menuH + 45, 15);
                JTextField strokeField = new JTextField();
                strokeField.setBounds(menuH, 25, menuH + 30, (menuH - 10) / 2);
                strokeField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        strokeField.setFocusable(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        strokeField.setFocusable(false);
                        super.mouseExited(e);
                        try {
                            if (strokeField.getText().equals("")) stroke = 1;
                            stroke = Integer.parseInt(strokeField.getText());
                        } catch (NumberFormatException ignored) {
                        }
                    }
                });

                JButton deleteAllBtn = new JButton("Xóa hết");
                deleteAllBtn.setBackground(Color.WHITE);
                deleteAllBtn.setFocusable(false);
                deleteAllBtn.setBounds(menuH + 110, 10, menuH + 20, menuH - 20);
                deleteAllBtn.addActionListener(e -> {
                    if (!listShape.isEmpty()) listShape.clear();
                    if (!listColor.isEmpty()) listColor.clear();
                    if (!listPoint.isEmpty())listPoint.clear();
                    if (!listPencilColor.isEmpty()) listPencilColor.clear();
                    if (!listPencilStroke.isEmpty()) listPencilStroke.clear();
                    if (!redrawPoint.isEmpty()) redrawPoint.clear();
                    listColor.add(color);
                    listPencilColor.add(color);
                    listPencilStroke.add(stroke);

                });

                add(strokeLabel);
                add(strokeField);
                add(fillBtn);
                add(deleteAllBtn);
            }
        }

        public class ColorPanel extends JPanel {
            JButton currentColor = new JButton();
            JPanel selectColor = new JPanel();

            public ColorPanel() {
                setBounds(widthMenu / 3, 0, 500, menuH);
                setLayout(null);

                currentColor.setBounds(0, 5, menuH - 10, menuH - 10);
                currentColor.setBackground(color);
                addActionCurrent();
                add(currentColor);

                selectColor.setLayout(new GridLayout(2, 5, 5, 5));
                selectColor.setBounds(menuH, 5, 300, menuH - 10);
                for (int i = 0; i < arrColor.length; i++) {
                    JButton colorBtn = new JButton();
                    colorBtn.setFocusable(false);
                    if (i == arrColor.length - 1) colorBtn.setText("Xóa");
                    colorBtn.setBackground(arrColor[i]);
                    colorBtn.addActionListener(e -> {
                        color = colorBtn.getBackground();
                        currentColor.setBackground(color);
                    });
                    selectColor.add(colorBtn);
                }
                add(selectColor);
            }

            public void addActionCurrent() {
                currentColor.addActionListener(e -> {
                    JColorChooser chooser = new JColorChooser();
                    JDialog colorDialog = JColorChooser.createDialog(this, "Chọn màu", false, chooser,
                            actionEvent -> {
                                color = chooser.getColor();
                                currentColor.setBackground(color);
                            },
                            actionEvent -> {
                                return;
                            }
                    );
                    colorDialog.setLocationRelativeTo(null);
                    colorDialog.setVisible(true);
                });
            }
        }
    }
}
