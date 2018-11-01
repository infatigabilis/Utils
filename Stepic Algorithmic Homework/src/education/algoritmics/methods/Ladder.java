package education.algoritmics.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ladder {
    int n;
    int[] A;
    int[] D;

    public static void main(String[] args) throws IOException {
        Ladder obj = new Ladder();

        long startTime = System.currentTimeMillis();
        obj.init();
        obj.runTD();

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
    }

    private void init() throws IOException {
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        n = Integer.parseInt(br.readLine());

        A = new int[n];
        D = new int[n];
        String[] secondLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(secondLine[i]);
        }
    }

    private void runBU() {

    }

    private void runTD() throws IOException {
        System.out.println(exec(n-1));
    }

    private int exec(int k) {
        if (k < 0) return 0;
        if (D[k] != 0) return D[k];

        int result = Math.max(exec(k-2) + A[k], exec(k-1) + A[k]);
        D[k] = result;
        return result;
    }
}
