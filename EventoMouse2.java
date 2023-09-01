/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */
public class EventoMouse2 
    extends JPanel
    implements MouseListener {
    
    Line2D.Double linea1;
    
    int x1;
    int y1;
    int x2;
    int y2;
    
    public EventoMouse2() {
        linea1 = new Line2D.Double();
        this.addMouseListener(this);
    }

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.PINK);
      /*
      g.drawLine(x1, y1, x2, y2);
      */
      g.drawRect(x1, y1, x2 - x1, y2 - y1);
  }
  
    @Override 
  public void mouseClicked(MouseEvent e) {}
  
    @Override
  public void mouseEntered(MouseEvent e) {}

    @Override
  public void mouseExited(MouseEvent e) {}

    @Override
  public void mousePressed(MouseEvent e) {
      x1 = e.getX();
      y1 = e.getY();
  }

    @Override
  public void mouseReleased(MouseEvent e) {
      x2 = e.getX();
      y2 = e.getY();    
      repaint();
  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Eventos del Mouse");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      EventoMouse2 ev = new EventoMouse2();
      frame.add(ev);
      //frame.addMouseListener(ev);
      // Asignarle tamaño
      frame.setSize(250, 200);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }

    
}
