import java.awt.*;

public interface Shape {
  void draw(Graphics g);
  boolean contains(int x, int y);
  void move(int dx, int dy);
}
