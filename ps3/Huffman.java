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

  public static Map<Character, String> codes(Node root) {
    Map<Character, String> m = new HashMap<>();
    if (root == null) return m;
    // single char file: assign "0" as code
    if (root.isLeaf()) { m.put(root.ch, "0"); return m; }
    walk(root, "", m);
    return m;
  }

  private static void walk(Node n, String prefix, Map<Character, String> m) {
    if (n.isLeaf()) { m.put(n.ch, prefix); return; }
    walk(n.left, prefix + "0", m);
    walk(n.right, prefix + "1", m);
  }

  public static String decode(String bits, Node root) {
    StringBuilder sb = new StringBuilder();
    Node cur = root;
    for (char b : bits.toCharArray()) {
      cur = b == '0' ? cur.left : cur.right;
      if (cur.isLeaf()) { sb.append(cur.ch); cur = root; }
    }
    return sb.toString();
  }

  public static String encode(String s, Map<Character, String> codes) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) sb.append(codes.get(c));
    return sb.toString();
  }

  public static Map<Character, Integer> freqs(String s) {
    Map<Character, Integer> m = new HashMap<>();
    for (char c : s.toCharArray()) m.merge(c, 1, Integer::sum);
    return m;
  }
}
