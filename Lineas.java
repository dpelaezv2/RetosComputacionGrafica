import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
    

/**
 *
 * @author htrefftz
 */
public class Lineas extends JPanel {

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.BLUE);
      Line2D.Double linea1 = new Line2D.Double(10,10, 100, 100);
      g2d.draw(linea1);

      //g2d.setColor(Color.RED);
      //Line2D.Double linea2 = new Line2D.Double(200, 20, 20, 80);
      //g2d.draw(linea2);
      
      // Invocar el algoritmo de Bresenham
      // P1 (10, 10) -> P2 (100, 100)
      
  }
  
  // Insertar el algoritmo de Bresenham
  // Dibujar línea desde (x, y, x, y)

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Lineas");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Lineas());
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    
}
