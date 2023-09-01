package Reto2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Llanta {
    /*public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;*/

    public static void main(String [] args) {
        SwingUtilities.invokeLater(Llanta::run);
    }   
    
    private static void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle("Llanta");

        f.getContentPane().add(new BresenhamPanel());
        f.pack();

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class BresenhamPanel extends JPanel {
    private final int pixelSize = 1;

    BresenhamPanel() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawAxis(g);

        int radio = 160;
        g.setColor(Color.black);
        for (int i = 0; i < 360; i += 10) {
            myDrawLine(g, 0, 0, 
                (int)(radio * Math.cos(Math.toRadians(i))), 
                (int)(radio * Math.sin(Math.toRadians(i)))
            );
        }
    }

    public void drawAxis(Graphics g) {
        g.setColor(Color.red);
        myDrawLine(g, -200, 0, 200, 0);
        g.setColor(Color.green);
        myDrawLine(g, 0, -200, 0, 200);
    }

    private void myDrawLine(Graphics g, int x1, int y1, int x2, int y2) {
        // delta of exact value and rounded value of the dependent variable
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                plot(g, x, y);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                plot(g, x, y);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }

    private void plot(Graphics g, double x, double y) {
        int w = (getWidth() - 1) / pixelSize;
        int h = (getHeight() - 1) / pixelSize;
        int maxX = (w - 1) / 2;
        int maxY = (h - 1) / 2;

        int borderX = getWidth() - ((2 * maxX + 1) * pixelSize + 1);
        int borderY = getHeight() - ((2 * maxY + 1) * pixelSize + 1);
        int left = (int)(x + maxX) * pixelSize + borderX / 2;
        int top = (int)(y + maxY) * pixelSize + borderY / 2;

        g.setColor(Color.black);
        g.drawOval(left, top, pixelSize, pixelSize);
    }
}
