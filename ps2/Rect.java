import java.awt.*;

public class Rect implements Shape {
  private int x, y, w, h;
  private Color color;

  public Rect(int x, int y, int w, int h, Color color) {
    this.x = x; this.y = y; this.w = w; this.h = h; this.color = color;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, w, h);
  }

  public boolean contains(int px, int py) { return px >= x && px <= x+w && py >= y && py <= y+h; }
  public void move(int dx, int dy) { x += dx; y += dy; }
}
