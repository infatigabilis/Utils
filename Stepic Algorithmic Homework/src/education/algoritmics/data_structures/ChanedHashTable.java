package education.algoritmics.data_structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class ChanedHashTable {
    int p = 1_000_000_007;
    int x = 263;
    int m;

    public static void main(String[] args) throws IOException {
        new ChanedHashTable().run();
    }

    private void run() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<String>> hashTable = new ArrayList<ArrayList<String>>(m);
        for (int i = 0; i < m; i++) {
            hashTable.add(new ArrayList<String>());
        }

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            switch (line[0]) {
                case "add":
                    if (!hashTable.get(hash(line[1])).contains(line[1])) hashTable.get(hash(line[1])).add(line[1]);
                    break;
                case "del":
                    if (hashTable.get(hash(line[1])).contains(line[1])) hashTable.get(hash(line[1])).remove(line[1]);
                    break;
                case "find":
                    if (hashTable.get(hash(line[1])).contains(line[1])) System.out.println("yes");
                    else System.out.println("no");
                    break;
                case "check":
                    ArrayList<String> list = hashTable.get(Integer.parseInt(line[1]));
                    if (list.size() == 0) System.out.println();
                    else {
                        for (int j = list.size()-1; j >= 0; j--) {
                            System.out.print(list.get(j) + " ");
                        }
                        System.out.println();
                    }
                    break;
            }
        }
    }

    private int hash(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            long X = ((((long) Math.pow(x, i)) % p) + p) % p;
            int S = ((((int) str.charAt(i)) % p) + p) % p;
            result += (((S * X) % p) + p) % p;
            result = ((result % p) + p) % p;
        }

        return ((result % m) + p) % p;
    }

    private int hash2(String str) {
        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < str.length(); i++) {
            BigInteger X = BigInteger.valueOf(x).pow(i).mod(BigInteger.valueOf(p));
            int S = (int) str.charAt(i);
            result.add(BigInteger.valueOf(S).multiply(X).mod(BigInteger.valueOf(p)));
        }

        return result.mod(BigInteger.valueOf(m)).intValue();
    }
}
