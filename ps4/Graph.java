import java.util.*;

public class Graph<V, E> {
  private Map<V, Map<V, E>> adj = new HashMap<>();

  public void addVertex(V v) { adj.putIfAbsent(v, new HashMap<>()); }
  public void addEdge(V u, V v, E label) {
    addVertex(u); addVertex(v);
    adj.get(u).put(v, label);
    adj.get(v).put(u, label);
  }
  public Set<V> vertices() { return adj.keySet(); }
  public Set<V> neighbors(V v) {
    return adj.getOrDefault(v, Collections.emptyMap()).keySet();
  }
  public E edgeLabel(V u, V v) { return adj.get(u).get(v); }
  public boolean has(V v) { return adj.containsKey(v); }
  public int size() { return adj.size(); }
}
