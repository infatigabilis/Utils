package education.algoritmics.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Knapsack {
    public static void main(String[] args) throws IOException {
        new Knapsack().run();
    }

    private void vanileRun() {
        int W = 10;
        int n = 4;

        int[] w = new int[]{6, 3, 4, 2};
        int[] c = new int[]{30, 14, 16, 9};

        int[][] D = new int[W+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < W+1; j++) {
                D[j][i] = D[j][i-1];
                if (w[i-1] <= j) {
                    D[j][i] = Math.max(D[j][i], D[j-w[i-1]][i-1] + c[i-1]);
                }
            }
        }

        System.out.println(D[W][n]);
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < W+1; j++) {
                System.out.print(D[j][i] + "\t");
            }
            System.out.println();
        }
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        String[] firstLine = br.readLine().split(" ");

        int W = Integer.parseInt(firstLine[0]);
        int n = Integer.parseInt(firstLine[1]);

        String[] secondLine = br.readLine().split(" ");

        int[] w = new int[n];
        int ind = 0;
        for (String str : secondLine) {
            w[ind] = Integer.parseInt(str);
            ind++;
        }

        int[][] D = new int[W+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < W+1; j++) {
                D[j][i] = D[j][i-1];
                if (w[i-1] <= j) {
                    D[j][i] = Math.max(D[j][i], D[j-w[i-1]][i-1] + w[i-1]);
                }
            }
        }

        System.out.println(D[W][n]);
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < W+1; j++) {
                System.out.print(D[j][i] + "\t");
            }
            System.out.println();
        }
    }
}
