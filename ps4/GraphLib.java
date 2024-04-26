import java.util.*;

public class GraphLib {

  public static <V, E> Graph<V, Integer> bfs(Graph<V, E> g, V root) {
    Graph<V, Integer> tree = new Graph<>();
    tree.addVertex(root);
    Map<V, V> parent = new HashMap<>();
    Queue<V> q = new ArrayDeque<>();
    q.add(root);
    while (!q.isEmpty()) {
      V cur = q.poll();
      for (V n : g.neighbors(cur)) {
        if (!tree.has(n)) {
          parent.put(n, cur);
          tree.addEdge(n, cur, 1);
          q.add(n);
        }
      }
    }
    return tree;
  }

  public static <V> List<V> pathFromTree(Graph<V, Integer> tree, V root, V dest) {
    List<V> p = new ArrayList<>();
    if (!tree.has(dest)) return p;
    V cur = dest;
    p.add(cur);
    while (!cur.equals(root)) {
      V next = null;
      for (V n : tree.neighbors(cur)) {
        // pick neighbor that is one closer to root - here we just pick first
        next = n; break;
      }
      if (next == null) return Collections.emptyList();
      cur = next;
      p.add(cur);
      if (p.size() > 200) return Collections.emptyList(); // safety
    }
    return p;
  }
}
