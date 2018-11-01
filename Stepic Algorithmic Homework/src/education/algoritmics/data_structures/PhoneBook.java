package education.algoritmics.data_structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneBook {
    int P = 10_000_000;

    public static void main(String[] args) throws IOException {
        new PhoneBook().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());

        String[] A = new String[P];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            switch (line[0]) {
                case "add":
                    A[hash(Integer.parseInt(line[1]))] = line[2];
                    break;
                case "del":
                    A[hash(Integer.parseInt(line[1]))] = null;
                    break;
                case "find":
                    if (A[Integer.parseInt(line[1])] == null) System.out.println("not found");
                    else System.out.println(A[hash(Integer.parseInt(line[1]))]);
                    break;
            }
        }
    }

    private int hash(int i) {
        return i % P;
    }
}
