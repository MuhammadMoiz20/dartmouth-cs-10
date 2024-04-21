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
    setLayout(new BorderLayout());

    JPanel canvas = new JPanel() {
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) s.draw(g);
      }
    };
    canvas.setBackground(Color.WHITE);

    canvas.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
          for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(e.getX(), e.getY())) {
              shapes.remove(i);
              repaint();
              return;
            }
          }
          return;
        }
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

    JToolBar bar = new JToolBar();
    JButton ellipse = new JButton("ellipse"); ellipse.addActionListener(e -> tool = "ellipse"); bar.add(ellipse);
    JButton rect = new JButton("rect"); rect.addActionListener(e -> tool = "rect"); bar.add(rect);
    for (Color c : new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.BLACK}) {
      JButton b = new JButton(); b.setBackground(c); b.setPreferredSize(new Dimension(24,24));
      b.addActionListener(e -> color = c); bar.add(b);
    }
    add(bar, BorderLayout.NORTH);
    add(canvas, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Editor().setVisible(true));
  }
}
