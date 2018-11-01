package education.algoritmics.methods;

import java.io.*;

public class GreaterSequenceDivider {
    int n;
    int[] A;

    public static void main(String[] args) throws IOException {
        new GreaterSequenceDivider().run();
    }

    private void run() throws IOException {
//        InputStreamReader in = new InputStreamReader(System.in);
        InputStreamReader in = new FileReader("index.in");
        BufferedReader br = new BufferedReader(in);

        n = Integer.parseInt(br.readLine());

        A = new int[n];
        String[] arrLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(arrLine[i]);
        }

        int[] D = new int[n];
        for (int i = 0; i < n; i++) {
            D[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] % A[j] == 0 && D[j]+1 > D[i])
                    D[i] = D[j] + 1;
            }
        }

        int result = 0;
        for (int i : D) {
            if (i > result) result = i;
        }

        System.out.println(result);
    }
}
