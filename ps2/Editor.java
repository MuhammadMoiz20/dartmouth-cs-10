import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Editor extends JFrame {
  private List<Shape> shapes = new ArrayList<>();
  private Shape moving = null;
  private int lastX, lastY;
  private String tool = "ellipse";
  private Color color = Color.BLUE;

  public Editor() {
    setTitle("editor");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel canvas = new JPanel() {
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) s.draw(g);
      }
    };
    canvas.setBackground(Color.WHITE);

    canvas.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        // find topmost shape under mouse
        for (int i = shapes.size() - 1; i >= 0; i--) {
          if (shapes.get(i).contains(e.getX(), e.getY())) {
            moving = shapes.get(i);
            lastX = e.getX(); lastY = e.getY();
            return;
          }
        }
        // create new
        if (tool.equals("ellipse")) shapes.add(new Ellipse(e.getX()-20, e.getY()-20, 40, 40, color));
        else shapes.add(new Rect(e.getX()-20, e.getY()-20, 40, 40, color));
        repaint();
      }
      public void mouseReleased(MouseEvent e) { moving = null; }
    });
    canvas.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        if (moving != null) {
          moving.move(e.getX() - lastX, e.getY() - lastY);
          lastX = e.getX(); lastY = e.getY();
          repaint();
        }
      }
    });

    add(canvas);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Editor().setVisible(true));
  }
}
