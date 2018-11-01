package education.algoritmics.data_structures.hash_table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ChainHash {
    private int m;

    private List<List<String>> hashTable = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ChainHash().run();
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        m = Integer.parseInt(in.readLine());
        for (int i = 0; i < m; i++) hashTable.add(new ArrayList<>());

        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            String[] line = in.readLine().split(" ");

            switch (line[0]) {
                case "add":
                    add(line[1]);
                    break;
                case "del":
                    delete(line[1]);
                    break;
                case "find":
                    find(line[1]);
                    break;
                case "check":
                    check(Integer.parseInt(line[1]));
                    break;
            }
        }
    }

    private void add(String str) {
        if (!hashTable.get(hash(str)).contains(str))
            hashTable.get(hash(str)).add(0, str);
    }

    private void delete(String str) {
        int hash = hash(str);
        if (hashTable.get(hash).contains(str))
            hashTable.get(hash).remove(str);
    }

    private void find(String str) {
        if (hashTable.get(hash(str)).contains(str)) System.out.println("yes");
        else System.out.println("no");
    }

    private void check(int index) {
        System.out.println(hashTable.get(index).stream().reduce((s1, s2) -> s1 + " " + s2).orElse(""));
    }

    private int hash(String str) {
        BigInteger x = BigInteger.valueOf(263);
        BigInteger p = BigInteger.valueOf(1_000_000_007);

        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < str.length(); i++) {
            BigInteger S = BigInteger.valueOf((int) str.charAt(i));
            BigInteger X = x.modPow(BigInteger.valueOf(i), p);

            sum = sum.add(S.multiply(X).mod(p));
        }
        sum = sum.mod(p);

        return sum.intValue() % m;
    }
}
