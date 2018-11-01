package education.algoritmics.methods;

import java.io.*;

public class EditDist {
    String A;
    String B;
    int[][] D;
    boolean[][] exist;

    public static void main(String[] args) throws IOException {
        new EditDist().runR();
    }

    private void run() throws IOException {
        InputStreamReader reader = new FileReader("index.in");
//        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int n = A.length+1;
        int m = B.length+1;

        int[][] D = new int[n][m];

        for (int i = 0; i < A.length; i++) {
            D[i][0] = i;
        }

        for (int i = 0; i < B.length; i++) {
            D[0][i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                D[i][j] = min(D[i-1][j] + 1, D[i][j-1] + 1, D[i-1][j-1] + diff(A[i-1], B[j-1]));
            }
        }

        System.out.println(D[A.length][B.length]);
    }

    private void runR() throws IOException {
        InputStreamReader reader = new FileReader("index.in");
//        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        A = br.readLine();
        B = br.readLine();

        D = new int[A.length()+1][B.length()+1];
        exist = new boolean[A.length()+1][B.length()+1];

        int result = exec(A.length(), B.length());
        System.out.println(result);
    }

    private int exec(int n, int m) {
        if (n == 0) return m;
        if (m == 0) return n;
        if (exist[n][m]) return D[n][m];

        int ins = exec(n-1, m) + 1;
        int del = exec(n, m-1) + 1;
        int rep = exec(n-1, m-1) + diff(A.charAt(n-1), B.charAt(m-1));

        int result = min(ins, del, rep);

        D[n][m] = result;
        exist[n][m] = true;

        return result;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int diff(char a, char b) {
        if (a == b) return 0;
        return 1;
    }
}
