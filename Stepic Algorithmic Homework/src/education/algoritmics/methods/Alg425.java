package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.CharsetEncoder;
import java.util.*;

public class Alg425 {

    class Letter implements Comparable<Letter> {
        char letter;
        int count;

        public Letter(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }

        @Override
        public int compareTo(Letter o) {
            int result = -Integer.compare(count, o.count);
            if (result == 0) return Character.compare(letter, o.letter);
            return result;
        }
    }

    public void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        char[] text = in.nextLine().toCharArray();

        Set<Character> alphabet = new HashSet<>();
        for (char ch : text) {
            alphabet.add(ch);
        }

        List<Letter> analized = new ArrayList<>();
        for (Character letter : alphabet) {
            int count = 0;
            for (char ch : text) {
                if (letter == ch) count++;
            }
            analized.add(new Letter(letter, count));
        }
        analized.sort(Letter::compareTo);

        Map<Character, String> coder = new HashMap<>();
        String code = "0";
        for (Letter letter : analized) {
            coder.put(letter.letter, code);
            code = "1" + code;
        }
        if (analized.size() != 1) coder.put(analized.get(analized.size() - 1).letter, code.substring(0, code.length() - 2));

        String result = "";
        for (char ch : text) {
            result += coder.get(ch);
        }

        System.out.println(coder.size() + " " + result.length());
        coder.forEach((ch, bits) -> {
            System.out.println(ch + ": " + bits);
        });
        System.out.println(result);
    }
}
