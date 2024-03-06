import java.awt.image.BufferedImage;
import java.util.*;

public class BlobFinder {
  private BufferedImage img;
  private boolean[][] visited;
  private int threshold = 50;

  public BlobFinder(BufferedImage img) {
    this.img = img;
    this.visited = new boolean[img.getWidth()][img.getHeight()];
  }

  public List<List<int[]>> findBlobs() {
    List<List<int[]>> blobs = new ArrayList<>();
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        if (!visited[x][y] && isDark(x, y)) {
          List<int[]> blob = floodFill(x, y);
          if (blob.size() > 5) blobs.add(blob);
        }
      }
    }
    return blobs;
  }

  private boolean isDark(int x, int y) {
    int rgb = img.getRGB(x, y);
    int r = (rgb >> 16) & 0xff, g = (rgb >> 8) & 0xff, b = rgb & 0xff;
    return (r + g + b) / 3 < threshold;
  }

  private List<int[]> floodFill(int sx, int sy) {
    List<int[]> pixels = new ArrayList<>();
    Deque<int[]> stack = new ArrayDeque<>();
    stack.push(new int[]{sx, sy});
    while (!stack.isEmpty()) {
      int[] p = stack.pop();
      int x = p[0], y = p[1];
      if (x < 0 || y < 0 || x >= img.getWidth() || y >= img.getHeight()) continue;
      if (visited[x][y] || !isDark(x, y)) continue;
      visited[x][y] = true;
      pixels.add(new int[]{x, y});
      stack.push(new int[]{x+1, y});
      stack.push(new int[]{x-1, y});
      stack.push(new int[]{x, y+1});
      stack.push(new int[]{x, y-1});
    }
    return pixels;
  }
}
