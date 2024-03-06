import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class BlobApp extends JFrame {
  private BufferedImage img;
  private List<List<int[]>> blobs;

  public BlobApp(String path) throws Exception {
    img = ImageIO.read(new File(path));
    BlobFinder bf = new BlobFinder(img);
    blobs = bf.findBlobs();
    setTitle("blobs: " + blobs.size());
    setSize(img.getWidth(), img.getHeight());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void paint(Graphics g) {
    g.drawImage(img, 0, 0, null);
    g.setColor(Color.RED);
    for (List<int[]> blob : blobs) {
      for (int[] p : blob) g.fillRect(p[0], p[1], 1, 1);
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) { System.out.println("usage: BlobApp <image>"); return; }
    new BlobApp(args[0]).setVisible(true);
  }
}
