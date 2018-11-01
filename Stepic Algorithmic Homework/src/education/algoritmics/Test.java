package education.algoritmics;

import java.util.HashSet;
import java.util.Set;

public  class Test {
    public static void main(String[] args) {
        new Test().run();
    }

    private void run() {
        A aB = new B();
        A aC = new C();
        B bB= new B();
        B bC= new C();
        C cC = new C();

        System.out.println(aC.diff(1,2));
        System.out.println(bC.diff(1,2));
    }

    interface A {
        int diff(int x, int y);
    }
    class B implements A{
        public int diff(int x, int y){return x - y;}
        public int sum(int x, int y){return x + y;}
    }
    class C extends B{
        public int mult(int x, int y){return x*y;}
        public int diff(int x, int y){return y - x;}
    }

}