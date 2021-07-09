import java.sql.SQLOutput;
import java.util.*;

class Result1 {

    static HashMap<String, Integer> prefixCountMap = new HashMap<>();

    static void addWord(String word){
        for(int i = 1; i <= word.length(); i++){
            String prefix = word.substring(0,i);
            System.out.println(prefix);

            if(!prefixCountMap.containsKey(prefix)){
                prefixCountMap.put(prefix, 0);
            }
            prefixCountMap.put(prefix, prefixCountMap.get(prefix) + 1);
        }
    }

    static Integer getWordCount(String word){
        return  prefixCountMap.get(word);
    }



    public static List<Integer> contacts(List<List<String>> queries) {
        // Write your code here

        List<Integer> result = new LinkedList<>();

        for (List<String> query : queries) {
            String task = query.get(0);  // task
            String word = query.get(1);  // word
            if(task.equals("add")){
                addWord(word);
            }
            else {
                int wordCount = getWordCount(word) == null? 0 : getWordCount(word);
                result.add(wordCount);
            }
        }
        return result;
    }

}
public class PrefixSearchAsHashMap {

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

        List<Integer> result = Result1.contacts(queries);

        System.out.println(result);

    }
}


