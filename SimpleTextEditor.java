import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    
     Scanner scanner = new Scanner(System.in);
    int nOperations = Integer.parseInt(scanner.nextLine());
    String[] memory = new String[nOperations];
    String currentValue = "";

    String operation = scanner.nextLine();
    String[] operationArray = operation.split(" ");
    int count = 0;

    for (int i = 0; i < nOperations; i++) {
      if (operationArray.length == 1) {
        currentValue = memory[count - 2];
        memory[count] = "";
        count--;
      } else if (operationArray.length == 2) {
        int numOfOperation = Integer.parseInt(operationArray[0]);
        String value = operationArray[1];
        if (numOfOperation == 1) {
          currentValue += value;
          memory[count] = currentValue;
          count++;
        } else if (numOfOperation == 2) {
          int nToRemove = Integer.parseInt(value);
          int beginIndex = 0;
          int endIndex = currentValue.length() - nToRemove;
          currentValue = currentValue.substring(beginIndex, endIndex);
          memory[count] = currentValue;
          count++;
        } else if (numOfOperation == 3) {
          int nToPrint = Integer.parseInt(value);
          System.out.println(currentValue.charAt(nToPrint - 1));
        }
      }
      if(i!=nOperations-1) {
        operation = scanner.nextLine();
        operationArray = operation.split(" ");
      }
    }
    }
}
