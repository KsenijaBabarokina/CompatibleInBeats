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

class Result3 {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int solve(List<Integer> arr) {
        // Write your code here
        int maxProd = 0;
        for(int i = 0; i < arr.size(); i++){
            int curVal = arr.get(i);
            int left = 0;
            int right = 0;
            for(int l = i-1; l >= 0; l--){
                if(arr.get(l) > curVal){
                    left = l + 1;
                    break;
                }

            }
            boolean foundRight = false;
            for(int r = i + 1; r < arr.size(); r++){
                if(arr.get(r) > curVal){
                    right = r + 1;
                    break;
                }
            }

            if(left * right > maxProd){
                maxProd = left*right;
            }
        }
        return maxProd;

        // Stack<Integer> st1=new Stack();
        // Stack<Integer> st2=new Stack();
        // int n=arr.size();
        // int i;
        // ArrayList<Integer> left = new ArrayList<>(n);
        // ArrayList<Integer> right = new ArrayList<>(n);

        // left.add(0, 0);
        // st1.push(0);
        // for(i=1;i<n;i++){
        //     while(!st1.isEmpty()&&arr.get(i)>=arr.get(st1.peek())){
        //         st1.pop();
        //     }
        //     if(st1.isEmpty()) left.add(i, 0);
        //     else left.add(i, st1.peek()+1);
        //     st1.push(i);
        // }

        // right.add(n-1,0);
        // st2.push(n-1);
        // for(i=n-2;i>=0;i--){
        //     while(!st2.isEmpty()&&arr.get(i)>=arr.get(st2.peek())){
        //         st2.pop();
        //     }
        //     if(st2.isEmpty()) right.add(i,0);
        //     else right.add(i, st2.peek()+1);
        //     st2.push(i);
        // }
        // int ans=0;

        // for(i=0;i<n;i++){
        //     ans = Math.max(ans,(int)left.get(i) * right.get(i));
        // }
        // return ans;
    }
}

public class MaxIntProduct {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result3.solve(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
