/**
 * Clase para dibujar algunos puntos.
 * 
 * @author Helmuth Trefftz
 * @version Septiembre 2016
 */
package Reto1.Ejemplos;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
    
public class Puntos extends JPanel {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      //Graphics2D g2d = (Graphics2D) g;

      g.setColor(Color.blue);

      // Dibujar unos puntos.
      g.drawLine(10, 10, 10, 10);
      g.drawLine(20, 20, 20, 20);
      g.drawLine(30, 30, 30, 30);
      g.drawLine(40, 40, 40, 40);

      drawAxis(g);

      for(int i = 0; i <= 100; i++ ) {
        //g.drawLine(i, i, i, i);
        g.setColor(Color.blue);
        myDrawPoint(g, i, i);
      }


  }

  public void drawAxis(Graphics g) {
    g.setColor(Color.red);
    myDrawLine(g, -100, 0, 100, 0);
    g.setColor(Color.green);
    myDrawLine(g, 0, -100, 0, 100);
  }

  public void myDrawPoint(Graphics g, int x, int y) {
    int xj = x + WIDTH/2;
    int yj = HEIGHT/2 - y;
    g.drawLine(xj, yj, xj, yj);
  }

  public void myDrawLine(Graphics g, int x1, int y1, int x2, int y2) {
    int xj1 = x1 + WIDTH/2;
    int yj1 = HEIGHT/2 - y1;
    int xj2 = x2 + WIDTH/2;
    int yj2 = HEIGHT/2 - y2;
    g.drawLine(xj1, yj1, xj2, yj2);
  }



  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Puntos");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Puntos());
      // Asignarle tamaño
      frame.setSize(WIDTH, HEIGHT);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    
}