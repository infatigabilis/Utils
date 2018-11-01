package education.algoritmics.methods;

import java.util.Scanner;

public class GCD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();

        System.out.println(find(a, b));
    }

    private static int find(int a, int b) {
        if (a == 0) return b;
        else if (b == 0) return a;
        else if (a >= b) return find(a % b, b);
        else return find(a, b % a);
    }
}
