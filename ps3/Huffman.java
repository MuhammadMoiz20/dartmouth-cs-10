import java.util.*;

public class Huffman {

  static class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) { this.ch = ch; this.freq = freq; }
    Node(Node l, Node r) { this.freq = l.freq + r.freq; this.left = l; this.right = r; }

    boolean isLeaf() { return left == null && right == null; }

    public int compareTo(Node o) { return Integer.compare(this.freq, o.freq); }
  }

  public static Map<Character, Integer> freqs(String s) {
    Map<Character, Integer> m = new HashMap<>();
    for (char c : s.toCharArray()) m.merge(c, 1, Integer::sum);
    return m;
  }
}
