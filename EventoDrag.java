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
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */
public class EventoDrag
    extends JPanel
    implements MouseListener, MouseMotionListener {
    
    Line2D.Double linea1;
    
    public EventoDrag() {
        linea1 = new Line2D.Double();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
  public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.BLUE);
      g2d.draw(linea1);

  }
  
    @Override
  public void mouseClicked(MouseEvent e) {}

    @Override
  public void mouseEntered(MouseEvent e) {}

    @Override
  public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
      linea1.x1 = e.getX();
      linea1.y1 = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
      linea1.x2 = e.getX();
      linea1.y2 = e.getY();    
      repaint();
    }
  
    @Override
  public void mouseMoved(MouseEvent e) {}

    @Override
  public void mouseDragged(MouseEvent e) {
      linea1.x2 = e.getX();
      linea1.y2 = e.getY();    
      repaint();      
  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Eventos del Mouse");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      EventoDrag ev = new EventoDrag();
      frame.add(ev);
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }

    
}
