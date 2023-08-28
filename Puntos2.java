/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
    
public class Puntos2 extends JPanel
{

    @Override
  public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.blue);

      // Dibujar unos puntos.
      // g2d.drawLine(10, 10, 10, 10);
      // g2d.drawLine(20, 20, 20, 20);
      // g2d.drawLine(30, 30, 30, 30);
      // g2d.drawLine(40, 40, 40, 40);
      
      bresenham(10, 10, 100, 100, g2d);
  }

  public void bresenham(int x1, int y1, int x2, int y2, Graphics2D g2d) {
      int dx = x2 - x1;
      int dy = y2 - y1;
      int incE = 2 * dy;
      int incNE = 2 * dy - 2 * dx;
      int d = 2 * dy - dx;
      int y = y1;
      for (int x = x1; x <= x2; x++) {
          g2d.drawLine(x, y, x, y);
          if (d <= 0) {
              d += incE;
          } else {
              d += incNE;
              y += 1;
          }
      }
  }
  
  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Puntos2");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Puntos2());
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    
}
