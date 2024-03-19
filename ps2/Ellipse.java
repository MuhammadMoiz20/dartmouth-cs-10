import java.awt.*;

public class Ellipse implements Shape {
  private int x, y, w, h;
  private Color color;

  public Ellipse(int x, int y, int w, int h, Color color) {
    this.x = x; this.y = y; this.w = w; this.h = h; this.color = color;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(x, y, w, h);
  }

  public boolean contains(int px, int py) {
    double dx = (px - (x + w/2.0)) / (w/2.0);
    double dy = (py - (y + h/2.0)) / (h/2.0);
    return dx*dx + dy*dy <= 1.0;
  }

  public void move(int dx, int dy) { x += dx; y += dy; }
}
