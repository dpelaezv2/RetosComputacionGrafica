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
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */
public class MoverRectangulos
        extends JPanel
        implements MouseListener, MouseMotionListener {

    Rectangle2D.Double rectangulo1;
    Rectangle2D.Double rectangulo2;
    double xAgarre = 0;
    double yAgarre = 0;
    // Cuál rectángulo está agarrado
    int agarrado = 0;

    // Coordenadas a utilizar como punto inicial del rectángulo que se va a mover
    double xi = 0;
    double yi = 0;

    public MoverRectangulos() {
        rectangulo1 = new Rectangle2D.Double(10, 10, 50, 50);
        rectangulo2 = new Rectangle2D.Double(80, 20, 20, 80);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.fill(rectangulo1);
        g2d.draw(rectangulo1);

        g2d.setColor(Color.RED);
        g2d.draw(rectangulo2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Guardar la coordenada desde la cual se agarra el rectángu
        xAgarre = e.getX();
        yAgarre = e.getY();
        // Agarró el rectángulo 1?
        if (rectangulo1.x <= xAgarre && xAgarre <= rectangulo1.x + rectangulo1.width
                && rectangulo1.y <= yAgarre && yAgarre <= rectangulo1.y + rectangulo1.height) {
            agarrado = 1;
            xi = rectangulo1.x;
            yi = rectangulo1.y;
        }
        // Agarró el rectángulo 2?
        if (rectangulo2.x <= xAgarre && xAgarre <= rectangulo2.x + rectangulo2.width
                && rectangulo2.y <= yAgarre && yAgarre <= rectangulo2.y + rectangulo2.height) {
            agarrado = 2;
            xi = rectangulo2.x;
            yi = rectangulo2.y;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double xF = e.getX();
        double yF = e.getY();
        double deltaX = xF - xAgarre;
        double deltaY = yF - yAgarre;
        if (agarrado == 1) {
            rectangulo1.x = xi + deltaX;
            rectangulo1.y = yi + deltaY;
        }
        if (agarrado == 2) {
            rectangulo2.x = xi + deltaX;
            rectangulo2.y = yi + deltaY;
        }
        agarrado = 0;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double xF = e.getX();
        double yF = e.getY();
        
        double deltaX = xF - xAgarre;
        double deltaY = yF - yAgarre;
        if (agarrado == 1) {
            rectangulo1.x = xi + deltaX;
            rectangulo1.y = yi + deltaY;
        }
        if (agarrado == 2) {
            rectangulo2.x = xi + deltaX;
            rectangulo2.y = yi + deltaY;
        }
        repaint();
    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Mover Rectángulos");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new MoverRectangulos());
        // Asignarle tamaño
        frame.setSize(250, 200);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }

}
