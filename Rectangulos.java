/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
    

/**
 *
 * @author htrefftz
 */
public class Rectangulos extends JPanel {

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.BLUE);      
      Rectangle2D.Double rectangulo1 = new Rectangle2D.Double(10, 10, 50, 50);
      g2d.fill(rectangulo1);
      //g2d.draw(rectangulo1);
      
      g2d.setColor(Color.RED);
      Rectangle2D.Double rectangulo2 = 
        new Rectangle2D.Double(80, 20, 20, 80);
      g2d.draw(rectangulo2);
  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Rectángulos");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Rectangulos());
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    

}
