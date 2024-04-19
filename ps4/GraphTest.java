public class GraphTest {
  public static void main(String[] args) {
    Graph<String, String> g = new Graph<>();
    g.addEdge("a", "b", "x");
    g.addEdge("b", "c", "y");
    assert g.size() == 3;
    assert g.neighbors("a").contains("b");
    assert g.edgeLabel("b", "c").equals("y");
    System.out.println("graph tests ok");
  }
}
