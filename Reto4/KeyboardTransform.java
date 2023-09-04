import Math.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class KeyboardTransform extends JPanel implements KeyListener {
    
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static Edge[] edges;
    public static String fileName = "Reto4/casita.txt";

    double xPos = 0;
    double yPos = 0;

    public static void main(String[] args) {
        edges = readFile(fileName);
        
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Casita");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase) 
        frame.add(new KeyboardTransform());
        // Asignarle tamaño
        frame.setSize(WIDTH, HEIGHT);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);   
    }

    public KeyboardTransform() {
        // El panel, por defecto no es "focusable". 
        // Hay que incluir estas líneas para que el panel pueda
        // agregarse como KeyListsener.
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addKeyListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawAxis(g);
        g.setColor(Color.black);
        
        for (int i = 0; i < edges.length; i++) {
            myDrawLine(g, (int)edges[i].p1.x, (int)edges[i].p1.y, (int)edges[i].p2.x, (int)edges[i].p2.y);
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

    public static void Transform(String type, double param1, double param2) {
        Edge[] newEdge = edges;
        
        Matrix3x3 transform;

        if (type.equals("t")) {
            transform = new Matrix3x3(1,0,param1,
                                        0,1,param2,
                                        0,0,1);
        } else if (type.equals("s")) {
            transform = new Matrix3x3(param1, 0, 0,
                                            0, param2, 0, 
                                            0, 0, 1);
        } else if (type.equals("r")) {
            transform = new Matrix3x3(Math.cos(Math.toRadians(param1)), -(Math.sin(Math.toRadians(param1))), 0,
                                            Math.sin(Math.toRadians(param1)), Math.cos(Math.toRadians(param1)), 0, 
                                            0, 0, 1);
        } else {
            transform = new Matrix3x3(1, 0, 0, 
                                            0, 1, 0,
                                            0, 0, 1);
        }

        for (int i = 0; i < edges.length; i++) {
            newEdge[i].p1 = Matrix3x3.times(transform, edges[i].p1);
            newEdge[i].p2 = Matrix3x3.times(transform, edges[i].p2);
        }
        edges = newEdge;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        
        Double amount = 8.0;
        if(tecla == KeyEvent.VK_W) {
            Transform("t", 0, amount);
            yPos += amount;
        } else if (tecla == KeyEvent.VK_S) {
            Transform("t", 0, -amount);
            yPos -= amount;
        } else if (tecla == KeyEvent.VK_D) {
            Transform("t", amount, 0);
            xPos += amount;
        } else if (tecla == KeyEvent.VK_A) {
            Transform("t", -amount, 0);
            xPos -= amount;
        } else if (tecla == KeyEvent.VK_E) {

            Transform("t", -xPos, -yPos);
            Transform("r", -10, 0);
            Transform("t", xPos, yPos);
        } else if (tecla == KeyEvent.VK_Q) {

            Transform("t", -xPos, -yPos);
            Transform("r", 10, 0);
            Transform("t", xPos, yPos);
        } else if (tecla == KeyEvent.VK_M) {

            Transform("t", -xPos, -yPos);
            Transform("s", 1.1, 1.1);
            Transform("t", xPos, yPos);
        } else if (tecla == KeyEvent.VK_N) {

            Transform("t", -xPos, -yPos);
            Transform("s", 0.9, 0.9);
            Transform("t", xPos, yPos);
        }
        repaint();    
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    
    public static Edge[] readFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int numPoints = scanner.nextInt();
            Point3[] points = new Point3[numPoints];
            for(int i = 0; i < numPoints; i++ ) {
                double x = scanner.nextInt();
                double y = scanner.nextInt();
                double w = 1;
                points[i] = new Point3(x, y, w); 
                System.out.println("Point " + i + ": (" + x + ", " + y + ")");
            }
            int numEdges = scanner.nextInt();
            Edge[] edges = new Edge[numEdges];
            for(int i = 0; i < numEdges; i++ ) {
                int indice1 = scanner.nextInt();
                int indice2 = scanner.nextInt();
                edges[i] = new Edge(points[indice1], points[indice2]);
                System.out.println("Desde: " + indice1 + " hasta: " + indice2);
            }
            scanner.close();
            return edges;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
