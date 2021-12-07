package basics;

import org.junit.jupiter.api.Test;

public class BasicsHomework1 {
    public int fuel(int a, int b, int c) {

        return a * c / b;
    }

    @Test
    public void test() {
         int a = fuel(10, 100, 10);
         int b = fuel(10, 100, 20);
         int c = fuel(10, 100, 50);


        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }
}
