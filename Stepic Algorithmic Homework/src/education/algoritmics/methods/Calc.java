package education.algoritmics.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calc {
    private int[] D;
    private int[] out;

    public static void main(String[] args) throws IOException {
        Calc obj = new Calc();
        obj.run();
    }

    private void run() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());
        D = new int[n+1];

        for (int i = 1; i < n; i++) {
            D[i] = execTD(i);
        }

        int result = execTD(n);
        System.out.println(result);

        out = new int[result + 1];
        out[0] = 1;

        out(n, result);

        for (int i = 0; i <= result; i++) {
            System.out.print(out[i] + " ");
        }
    }

    private int execTD(int n) {
        if (n <= 1) return 0;
        if (D[n] != 0) return D[n];

        int mul3 = n % 3 == 0 ? execTD(n/3) : 2_000_000_000;
        int mul2 = n % 2 == 0 ? execTD(n/2) : 2_000_000_000;
        int sub = execTD(n-1);

        int result = Math.min(Math.min(mul3, mul2), sub) + 1;
        D[n] = result;

        return result;
    }

    private void out(int k, int ind) {
        out[ind] = k;
        if (ind == 0) return;

        int v = D[k];

        if (k % 3 == 0 && D[k/3] == (v-1)) out(k/3, ind-1);
        else if (k % 2 == 0 && D[k/2] == (v-1)) out(k/2, ind-1);
        else out(k-1, ind-1);
    }
}
