package education.algoritmics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by infatigabilis on 03.05.17.
 */
public class Generator {
    private static Random rand = new Random();

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("index.in");

        int n = 100;

        printWriter.println(n);

        for (int i = 0; i < n; i++) {
            int v = getRand();
            printWriter.print(v + " ");
        }

        printWriter.close();
    }

    private static int getRand() {
        return rand.nextInt() % 10_000 + 1;
    }
}
