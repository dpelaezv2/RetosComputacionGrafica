package display;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import geometry.PolygonObject;
import geometry.Camera;
import geometry.ObjectTransformation;

import java.awt.Color;
import java.awt.Graphics;

public class GuiMain extends JPanel
    implements KeyListener {

  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  Graphics g;

  PolygonObject po;

  //ArrayList <PolygonObject> objects;

  public GuiMain() {
    setFocusable(true);
    requestFocusInWindow();
    //objects = new ArrayList<PolygonObject>();
    addKeyListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g = g;

    g.setColor(Color.RED);
    drawOneLine(-100, 0, 100, 0);

    g.setColor(Color.GREEN);
    drawOneLine(0, -100, 0, 100);

    //for(PolygonObject po : objects) {
      g.setColor(Color.BLACK);
      po.transformObject();
      po.setCamera();
      po.projectObject();
      po.draw();
    //}

  }

  public void drawOneLine(int x1, int y1, int x2, int y2) {
    x1 = x1 + WIDTH / 2;
    y1 = HEIGHT / 2 - y1;
    x2 = x2 + WIDTH / 2;
    y2 = HEIGHT / 2 - y2;
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_D) {
        po.ot.dx += ObjectTransformation.DELTA_TRANSL;
        repaint();
      } else if(key == KeyEvent.VK_A) {
        po.ot.dx -= ObjectTransformation.DELTA_TRANSL;
        repaint();
      } else if(key == KeyEvent.VK_W) {
        po.ot.dy += ObjectTransformation.DELTA_TRANSL;
        repaint();
      } else if(key == KeyEvent.VK_S) {
        po.ot.dy -= ObjectTransformation.DELTA_TRANSL;
        repaint();
      } else if(key == KeyEvent.VK_Q) {
        po.ot.sx += ObjectTransformation.DELTA_SCAL;
        po.ot.sy += ObjectTransformation.DELTA_SCAL;
        po.ot.sz += ObjectTransformation.DELTA_SCAL;
        repaint();
      } else if(key == KeyEvent.VK_E) {
        po.ot.sx -= ObjectTransformation.DELTA_SCAL;
        po.ot.sy -= ObjectTransformation.DELTA_SCAL;
        po.ot.sz -= ObjectTransformation.DELTA_SCAL;
        repaint();
      } else if(key == KeyEvent.VK_R) {
        po.ot.thetaX += ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_F) {
        po.ot.thetaX -= ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_T) {
        po.ot.thetaY += ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_G) {
        po.ot.thetaY -= ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_Y) {
        po.ot.thetaZ += ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_H) {
        po.ot.thetaZ -= ObjectTransformation.DELTA_ROT;
        repaint();
      } else if(key == KeyEvent.VK_LEFT) {
        po.camera.theta -= Camera.DELTA_THETA;
        repaint();
      } else if(key == KeyEvent.VK_RIGHT) {
        po.camera.theta += Camera.DELTA_THETA;
        repaint();
      } else if (key == KeyEvent.VK_UP) {
        if(po.camera.phi < Math.PI / 2 - 1.5 * Camera.DELTA_PHI) {
          po.camera.phi += Camera.DELTA_PHI;
          repaint();
        }
      } else if (key == KeyEvent.VK_DOWN) {
        if(po.camera.phi > -Math.PI / 2 + 1.5 * Camera.DELTA_PHI) {
          po.camera.phi -= Camera.DELTA_PHI;
          repaint();
        }
      } else if(key == KeyEvent.VK_Z) {
        po.resetVertices();
        repaint();
      } else if(key == KeyEvent.VK_C) {
        po.camera.radius += Camera.DELTA_RADIUS;
        repaint();
      } else if(key == KeyEvent.VK_V) {
        po.camera.radius -= Camera.DELTA_RADIUS;
        repaint();
      }
    }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  public static void main(String[] args) {
    // Crear un nuevo Frame (Ventana)
    JFrame frame = new JFrame("Superficies de Bézier");
    // Al cerrar el frame, termina la ejecución de este programa
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Agregar un JPanel que se llama Main (esta clase)
    GuiMain main = new GuiMain();
    // Create a PolygonObject
    //PolygonObject po = new PolygonObject();
    main.po = new PolygonObject();
    // Reading takes a long time. Read the file before adding the
    // JPanel to the JFrame.
    //main.po.readObject("casita3D.txt");
    //main.po.readObject("casita3Db.txt");
    main.po.readBezierSurface("Reto8/superficie.txt");
    //po.readObject("casita3D.txt");
    main.po.setCanvas(main);
    //po.setCanvas(main);
    //main.objects.add(po);
    // En true para que el objeto rote y se escale en torno a sí mismo
    // math.TranslScalRot4x4.CENTER_TRANFORMS = true;
    // Agregar el JPanel al frame
    frame.add(main);
    // Asignarle tamaño
    frame.setSize(WIDTH, HEIGHT);
    // Poner el frame en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Mostrar el frame
    frame.setVisible(true);
  }
}
