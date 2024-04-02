import java.util.Map;

public class HuffmanTest {
  public static void main(String[] args) {
    String s = "the quick brown fox jumps over the lazy dog";
    Map<Character, Integer> f = Huffman.freqs(s);
    Huffman.Node root = Huffman.buildTree(f);
    Map<Character, String> codes = Huffman.codes(root);
    String enc = Huffman.encode(s, codes);
    String dec = Huffman.decode(enc, root);
    System.out.println("orig bits: " + (s.length() * 8));
    System.out.println("enc bits: " + enc.length());
    System.out.println("roundtrip ok: " + s.equals(dec));
  }
}
