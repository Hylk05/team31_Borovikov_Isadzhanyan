package example;
/**
 * 
 * @author Roman,Konstantin
 * @version 1.1
 * @since 1.0
 * This is my main class, here i will write my <strong>code</strong>
 * <img src="doc-files/images.png" alt="bla"/>

 */

public class Main{
    /**
     * This is int fields where i will store my values
     */
    int Field1;

    /**
     * Default constructor
     */
    public Main() {
        this.field1 = 0;
        instanceCount++;
    }

    /**
     * Here start point of program
     * @param args command line values
     *
     */

    public static void main(String[] args) {
        System.out.println("Program started!");
        Main obj = new Main();
        obj.field1 = 10;
        System.out.println("Field value: " + obj.field1);
        System.out.println("Double value: " + obj.getDoubleFields(5, "test"));
        obj.newMethod();
        obj.oldmethod();
    }
    /**
     * This method will return i*2
     * @param i some int value
     * @param s soma other str value
     * @throws RuntimeException if smth goes wrong
     * @return i*2 from params
     */

    int getDoubleFields(int i, String s) throws RuntimeException{
        return i*2;
    }

    /**
     * @deprecated please use newMethod()
     */
    void oldmethod(){

    }

    /**
     * this is some descr with {@link OtherClass#otherMethod()}
     * @see OtherClass#otherMethod()
     * @see "second chapter"
     */

    void newMethod(){
    }


}
