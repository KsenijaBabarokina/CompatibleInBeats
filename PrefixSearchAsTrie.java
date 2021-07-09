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

    char letter;
    boolean isAWord;
    HashMap<Character, Node> children;
    int numOfWords;   // holds the amount of words which the path has

    public Node(char letter, boolean isAWord, int numOfWords){
        //constructor
        this.letter = letter;
        this.isAWord = isAWord;
        this.children = new HashMap<>();
        this.numOfWords = numOfWords;
    }
}

class Result {

    static Map<String, Node> hashedNodes = new HashMap<>();

    static void addNode(String word, int pointer, Node currentNode, String subWord){
        if(pointer == word.length()){
            return;
        }

        char currentChar = word.charAt(pointer);
        boolean isWord = pointer == word.length() - 1;
        int numOfWords = pointer == word.length() - 1 ? 1 : 0;
        subWord += currentChar;

        if(!currentNode.children.containsKey(currentChar)){
            Node newNode = new Node(currentChar, isWord, numOfWords);                                currentNode.children.put(currentChar, newNode);
            hashedNodes.put(subWord, newNode);
        }


        currentNode.numOfWords++;

        addNode(word, pointer + 1, currentNode.children.get(currentChar), subWord);
    }

    static Node findNode(String word){
        return hashedNodes.get(word);
    }


    public static List<Integer> contacts(List<List<String>> queries) {
        // Write your code here

        List<Integer> result = new LinkedList<>();

        Node root = new Node(' ', false, 0);

        for (List<String> query : queries) {
            String task = query.get(0);  // task
            String word = query.get(1);  // word
            if(task.equals("add")){
                addNode(word, 0, root, "");
            }
            else {
                Node node = findNode(word);
                int foundWords = node == null? 0 : node.numOfWords;
                result.add(foundWords);
            }
        }
        return result;
    }

}
public class PrefixSearchAsTrie {

    public static void main(String[] args){

        List<List<String>> queries = new ArrayList<>();

        List<String> query1 = Arrays.asList("add", "s");
        List<String> query2 = Arrays.asList("add", "ss");
        List<String> query3 = Arrays.asList("add", "sss");
        List<String> query4 = Arrays.asList("add", "ssss");
        List<String> query5 = Arrays.asList("add", "sssss");

        List<String> query6 = Arrays.asList("find", "s");
        List<String> query7 = Arrays.asList("find", "ss");
        List<String> query8 = Arrays.asList("find", "sss");
        List<String> query9 = Arrays.asList("find", "ssss");
        List<String> query10 = Arrays.asList("find", "sssss");
        List<String> query11 = Arrays.asList("find", "ssssss");

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);
        queries.add(query11);

        List<Integer> result = Result.contacts(queries);

        System.out.println(result);

    }
}

