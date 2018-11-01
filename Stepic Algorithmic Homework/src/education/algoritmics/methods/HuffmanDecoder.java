package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanDecoder {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        new HuffmanDecoder().run();

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " ms");
    }

    private void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("index.in"));
        int alphabetLength = in.nextInt();
        int codeLength = in.nextInt();
        in.nextLine();

        Map<String, Character> alphabet = new HashMap<>();
        for (int i = 0; i < alphabetLength; i++) {
            String line = in.nextLine();
            alphabet.put(line.substring(3), line.charAt(0));
        }

        String result = "";
        String acc = "";
        for (char ch : in.nextLine().toCharArray()) {
            acc += ch;
            if (alphabet.containsKey(acc)) {
                result += alphabet.get(acc);
                acc = "";
            }
        }

        System.out.println(result);
    }
}
