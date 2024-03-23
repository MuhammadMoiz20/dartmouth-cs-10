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

  public static Node buildTree(Map<Character, Integer> f) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    for (Map.Entry<Character, Integer> e : f.entrySet()) pq.add(new Node(e.getKey(), e.getValue()));
    if (pq.isEmpty()) return null;
    while (pq.size() > 1) {
      Node a = pq.poll(), b = pq.poll();
      pq.add(new Node(a, b));
    }
    return pq.poll();
  }

  public static Map<Character, Integer> freqs(String s) {
    Map<Character, Integer> m = new HashMap<>();
    for (char c : s.toCharArray()) m.merge(c, 1, Integer::sum);
    return m;
  }
}
