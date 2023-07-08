import java.util.*;

public class Test {
    public static void main(String args[]) {
        Vector<Integer> v1 = new Vector<Integer>();
        v1.add(1); v1.add(2); v1.add(3);
        Vector<Integer> v2 = new Vector<Integer>(v1);
        v2.set(0, 4);
        System.out.println(v1.get(0));
        System.out.println(v2.get(0));
    }
}
