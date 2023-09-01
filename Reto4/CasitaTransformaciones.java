package Reto4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Math.Matrix3x3;
import Math.Point3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CasitaTransformaciones extends JPanel implements KeyListener {
    
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    
    public Point3[] originalPoints;
    public Point3[] transformedPoints;

    public double angle = 0.0;  // Angulo de rotación en radianes
    public double scaleFactor = 1.0;  // Factor de escala
    public double translateX = 0.0;  // Traslación en X
    public double translateY = 0.0;  // Traslación en Y

    public static void main(String [] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Casita Transformable");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new CasitaTransformaciones());
        // Asignarle tamaño
        frame.setSize(WIDTH, HEIGHT);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }  

    public CasitaTransformaciones() {
        originalPoints = readPointsFromFile("Reto1/casita.txt");
        transformedPoints = new Point3[originalPoints.length];
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        drawAxis(g);

        g.setColor(Color.black);

        for (int i = 0; i < originalPoints.length; i++) {
            transformedPoints[i] = transformPoint(originalPoints[i]);
        }

        for (int i = 0; i < transformedPoints.length; i++) {
            Point3 p1 = transformedPoints[i];
            Point3 p2 = transformedPoints[(i + 1) % transformedPoints.length];
            myDrawLine(g, (int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
        }
    }

    public void drawAxis(Graphics g) {
        g.setColor(Color.red);
        myDrawLine(g, -300, 0, 300, 0);
        g.setColor(Color.green);
        myDrawLine(g, 0, -300, 0, 300);
    }

    public void myDrawLine(Graphics g, int x1, int y1, int x2, int y2) {
        int xj1 = x1 + WIDTH/2;
        int yj1 = HEIGHT/2 - y1;
        int xj2 = x2 + WIDTH/2;
        int yj2 = HEIGHT/2 - y2;
        g.drawLine(xj1, yj1, xj2, yj2);
    }

    public Point3 transformPoint(Point3 point) {
        Matrix3x3 translationMatrix = new Matrix3x3(new double[][] {
            {1, 0, translateX},
            {0, 1, translateY},
            {0, 0, 1}
        });

        Matrix3x3 rotationMatrix = new Matrix3x3(new double[][] {
            {Math.cos(angle), -Math.sin(angle), 0},
            {Math.sin(angle), Math.cos(angle), 0},
            {0, 0, 1}
        });

        Matrix3x3 scaleMatrix = new Matrix3x3(new double[][] {
            {scaleFactor, 0, 0},
            {0, scaleFactor, 0},
            {0, 0, 1}
        });

        Point3 transformedPoint = Matrix3x3.times(scaleMatrix, point);
        transformedPoint = Matrix3x3.times(rotationMatrix, transformedPoint);
        transformedPoint = Matrix3x3.times(translationMatrix, transformedPoint);

        return transformedPoint;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_S) {
            translateY -= 10;
        } else if (key == KeyEvent.VK_A) {
            translateX -= 10;
        } else if (key == KeyEvent.VK_W) {
            translateY += 10;
        } else if (key == KeyEvent.VK_D) {
            translateX += 10;
        } else if (key == KeyEvent.VK_Q) {
            angle -= Math.toRadians(1);
        } else if (key == KeyEvent.VK_E) {
            angle += Math.toRadians(1);
        } else if (key == KeyEvent.VK_M) {
            scaleFactor -= 0.1;
        } else if (key == KeyEvent.VK_N) {
            scaleFactor += 0.1;
        }

        repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    private Point3[] readPointsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int numPoints = Integer.parseInt(reader.readLine());
            Point3[] points = new Point3[numPoints];

            for (int i = 0; i < numPoints; i++) {
                String[] pointData = reader.readLine().split(" ");
                double x = Double.parseDouble(pointData[0]);
                double y = Double.parseDouble(pointData[1]);
                points[i] = new Point3(x, y, 1);
            }

            reader.close();
            return points;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}