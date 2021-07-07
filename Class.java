import java.util.Scanner;

class AnotherClass{

    public AnotherClass(){
        // constructor
    }

    public static void method(){
        // a method of a class
    }
}


public class Class {

    public static void helperMethod(){
        // some helper method to solve the problem
    }

    /** Documentation on Scanner:
     * https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
     **/

    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);     // init scanner

        int n    = sc.nextInt();        	// get next int from stdin (usually how many lines of input)
                                            // sc.nextLong(); sc.nextByte(); sc.nextBoolean();

        // for n inputs
        for(int i = 0; i< n; i++){
            String s = sc.nextLine();       // reads next line or read next int (depend on input type)

            // parse the String
            String[] input1 = s.split("/");
            String[] input2 = s.split(" ");

            // String to Integer/Double/Boolean
            Integer n1 = Integer.valueOf(input1[0]);
            Integer n2 = Integer.parseInt(input1[1]);
            Double n3 = Double.parseDouble(input1[1]);
            Boolean n4 = Boolean.parseBoolean(input1[1]);

            // use it however you want
            // -> put it in a DataStructure to use it as an input for Functions
            // -> make direct actions on them

        }

        sc.close();                         // closes the scanner
    }

}
