import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BlobFinder {
  private BufferedImage img;
  private boolean[][] visited;

  public BlobFinder(BufferedImage img) {
    this.img = img;
    this.visited = new boolean[img.getWidth()][img.getHeight()];
  }

  public List<List<int[]>> findBlobs() {
    List<List<int[]>> blobs = new ArrayList<>();
    // TODO
    return blobs;
  }
}
