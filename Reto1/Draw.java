package Reto1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Draw extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    
    String fileName = "Reto1/gancho.txt";

    public static void main(String [] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Puntos");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Draw());
        // Asignarle tamaño
        frame.setSize(WIDTH, HEIGHT);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        
        drawAxis(g);

        g.setColor(Color.black);
        Edge[] edges = readFile(fileName);
        for (int i = 0; i < edges.length; i++) {
            myDrawLine(g, edges[i].p1.x, edges[i].p1.y, edges[i].p2.x, edges[i].p2.y);
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

    public static Edge[] readFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int numPoints = scanner.nextInt();
            Point[] points = new Point[numPoints];
            for(int i = 0; i < numPoints; i++ ) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[i] = new Point(x, y); 
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

class Point {
    int x, y;

    //Constructor
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    Point p1, p2;

    public Edge (Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}