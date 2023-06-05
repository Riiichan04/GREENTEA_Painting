package painPaint;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("PainPaint");
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("JSwing2023\\src\\painPaint\\img\\paint.png").getImage());
//        setResizable(false);
        setLayout(null);

        MainPanel mp = new MainPanel(this.getWidth(), this.getHeight());
        add(mp);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
    }
}
