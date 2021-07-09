import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Node {
    Node left;
    Node right;
    int val;
    
    public Node(int val) {
        this.val = val;
    }
    
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Result {

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        int n = indexes.size();
        
        // creating n nodes, without children at the moment
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        
        // attaching children to nodes
        for (int i = 0; i < n; i++) {
            List<Integer> children = indexes.get(i);
            int a = children.get(0);
            int b = children.get(1);
            
            if (a != -1) {
                nodes[i].left = nodes[a - 1];
            }
            if (b != -1) {
                nodes[i].right = nodes[b - 1];
            }
        }
        
        Node root = nodes[0];           // the tree root
        int depth = maxDepth(root);     // the tree depth
        
        // running the queries
        List<List<Integer>> results = new ArrayList<>();
        for (int k : queries) {
            for (int q = k; q <= depth; q += k) {
                List<Node> layer = nodesAtDepth(root, q);
                for (Node node : layer) {
                    Node temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                }
            }
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            results.add(result);
        }
        
        return results;

    }
    
    private static void inorder(Node root, List<Integer> result) {
        if (root == null) return;
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
    
    private static List<Node> nodesAtDepth(Node root, int d) {
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        for (int i = 1; i < d; i++) {
            LinkedList<Node> children = new LinkedList<>();
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
            }
            nodes = children;
        }
        return nodes;
    }
    
    private static int maxDepth(Node node) {
        if (node == null) return 0;
        else return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
            .map(
                r -> r.stream()
                    .map(Object::toString)
                    .collect(joining(" "))
            )
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

