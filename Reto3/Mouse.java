
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Mouse
    extends JPanel
    implements MouseListener {
    
    int decider = 0;   
    int x1;
    int y1;
    int x2;
    int y2; 
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static final int ClippingX1 = 250;
    public static final int ClippingX2 = 750;
    public static final int ClippingY1 = 150;
    public static final int ClippingY2 = 450;
    private final int pixelSize = 1;
    
    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Mouse");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        Mouse ev = new Mouse();
        frame.add(ev);
        //frame.addMouseListener(ev);
        // Asignarle tamaño
        frame.setSize(WIDTH, HEIGHT);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    } 

    public Mouse() {
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawClippingArea(g2d);

        g2d.setColor(Color.BLUE);
        myDrawLine(g2d, x1, y1, x2, y2);

    }
  
    @Override 
    public void mouseClicked(MouseEvent e) {}
  
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (decider == 0) {
            x1 = e.getX();
            y1 = e.getY();
            decider = 1;
        } else if (decider == 1) {
            x2 = e.getX();
            y2 = e.getY();
            decider = 0;
            repaint();
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //linea1.x2 = e.getX();
        //linea1.y2 = e.getY();   
        //repaint();
    }

    public void drawClippingArea(Graphics g) {
        g.setColor(Color.red);
        myDrawLine(g, ClippingX1, ClippingY1, ClippingX2, ClippingY1);
        myDrawLine(g, ClippingX1, ClippingY1, ClippingX1, ClippingY2);
        myDrawLine(g, ClippingX2, ClippingY1, ClippingX2, ClippingY2);
        myDrawLine(g, ClippingX1, ClippingY2, ClippingX2, ClippingY2);
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
        g.setColor(Color.black);
        if (x >= ClippingX1 && x <= ClippingX2 && y >= ClippingY1 && y <= ClippingY2)
            g.drawOval((int)x, (int)y, pixelSize, pixelSize);
    }
}

