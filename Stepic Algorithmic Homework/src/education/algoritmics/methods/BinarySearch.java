package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearch {
    private int n;
    private int[] A;

    public void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("index.in"));

        n = in.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }

        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            System.out.print(search(in.nextInt()) + " ");
        }
    }

    private int search(int k) {
        int l = 0;
        int r = n - 1;
        while (r >= l) {
            int m = (r + l) / 2;
            int value = A[m];
            if (value == k) {
                return m + 1;
            }
            else if (value > k) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new BinarySearch().run();
    }
}
