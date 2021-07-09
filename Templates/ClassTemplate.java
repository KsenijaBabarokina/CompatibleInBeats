class HelperClass {

    public HelperClass(){
        //constructor
    }

    public void method(){
        // a method for the helper class
    }
}


public class ClassTemplate {

    public static void helperMethod(){
        // helper method for the current class
    }

    public static void main(String[] args) {
        // "runner"
        HelperClass hc = new HelperClass();   // create instance of the HelperClass
        hc.method();                          // invoke method on the instance of the Helper Class

        helperMethod();                       // call helperMethod
    }
}
