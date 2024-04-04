import java.util.*;

public class GraphLib {

  public static <V, E> Graph<V, Integer> bfs(Graph<V, E> g, V root) {
    Graph<V, Integer> tree = new Graph<>();
    tree.addVertex(root);
    Queue<V> q = new ArrayDeque<>();
    q.add(root);
    while (!q.isEmpty()) {
      V cur = q.poll();
      for (V n : g.neighbors(cur)) {
        if (!tree.has(n)) {
          tree.addEdge(n, cur, 1);
          q.add(n);
        }
      }
    }
    return tree;
  }

  public static <V, E> List<V> path(Graph<V, Integer> tree, V root, V dest) {
    List<V> p = new ArrayList<>();
    if (!tree.has(dest)) return p;
    V cur = dest;
    p.add(cur);
    while (!cur.equals(root)) {
      Iterator<V> it = tree.neighbors(cur).iterator();
      if (!it.hasNext()) return Collections.emptyList();
      cur = it.next();
      p.add(cur);
    }
    return p;
  }
}
