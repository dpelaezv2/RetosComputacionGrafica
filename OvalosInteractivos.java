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
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
    

/**
 *
 * @author htrefftz
 */
public class OvalosInteractivos 
extends JPanel 
implements MouseListener, MouseMotionListener {
    
    Ellipse2D.Double ovalo;
    double xCentro = 0;
    double yCentro = 0;
    
    public OvalosInteractivos() {
        ovalo = new Ellipse2D.Double();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
  public void mouseClicked(MouseEvent e) {}

    @Override
  public void mouseEntered(MouseEvent e) {}

    @Override
  public void mouseExited(MouseEvent e) {}

    @Override
  public void mousePressed(MouseEvent e) {
      ovalo.x = e.getX();
      ovalo.y = e.getY();
      ovalo.width = 1;
      ovalo.height = 1;
      xCentro = ovalo.x;
      yCentro = ovalo.y;
      repaint();
  }

    @Override
  public void mouseReleased(MouseEvent e) {
      double xF = e.getX();
      double yF = e.getY(); 
      double deltaX = xF - xCentro;
      double deltaY = yF - yCentro;
      ovalo.x = xCentro - deltaX;
      ovalo.y = yCentro - deltaY;
      ovalo.width = 2 * deltaX;
      ovalo.height = 2 * deltaY;
      repaint();      
  }
  
    @Override
  public void mouseMoved(MouseEvent e) {}

    @Override
  public void mouseDragged(MouseEvent e) {
      double xF = e.getX();
      double yF = e.getY(); 
      double deltaX = xF - xCentro;
      double deltaY = yF - yCentro;
      ovalo.x = xCentro - deltaX;
      ovalo.y = yCentro - deltaY;
      ovalo.width = 2 * deltaX;
      ovalo.height = 2 * deltaY;
      repaint();      
  }
    

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.BLUE);      
      g2d.fill(ovalo);
      g2d.draw(ovalo);
  }
    
    
  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Ovalos");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new OvalosInteractivos());
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
    
}
