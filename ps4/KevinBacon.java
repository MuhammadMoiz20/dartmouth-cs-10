import java.io.*;
import java.util.*;

public class KevinBacon {
  public static void main(String[] args) throws Exception {
    Graph<String, String> g = new Graph<>();
    // tiny example dataset
    g.addEdge("Kevin Bacon", "Apollo 13", "movie");
    g.addEdge("Tom Hanks", "Apollo 13", "movie");
    g.addEdge("Tom Hanks", "Forrest Gump", "movie");
    g.addEdge("Robin Wright", "Forrest Gump", "movie");

    Graph<String, Integer> tree = GraphLib.bfs(g, "Kevin Bacon");
    String target = args.length > 0 ? args[0] : "Robin Wright";
    List<String> p = GraphLib.pathFromTree(tree, "Kevin Bacon", target);
    System.out.println(target + " has bacon number " + ((p.size() - 1) / 2));
    System.out.println("path: " + p);
  }
}
