import java.io.*;
import java.util.*;

class Queue{
    
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void enqueue(int n){

        //put on one stack
        stack1.push(n);
    }

    int dequeue(){
        // check stack2
        if(stack2.isEmpty()){
            // push all s1 -> s2
            while(!stack1.isEmpty()){
                // pop from s1
                int temp = stack1.pop();

                // push to s2
                stack2.push(temp);
            }
        }
        return stack2.pop();
    }

    int peek(){
        if(stack2.isEmpty()){
            // push all s1 -> s2
            while(!stack1.isEmpty()){
                // pop from s1
                int temp = stack1.pop();

                // push to s2
                stack2.push(temp);
            }
        }
        return stack2.peek();
    }
}


public class TwoStacksQueue {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner (System.in);
        int nQueries = scanner.nextInt();

        Queue q = new Queue();

        for(int i = 0; i < nQueries; i++){
            int opType = scanner.nextInt();
            if(opType == 1){
                int val = scanner.nextInt();
                q.enqueue(val);
            }
            else if (opType == 2) {
                q.dequeue();
            }
            else if( opType == 3) {
                System.out.println(q.peek());
            }
        }

    }
}
