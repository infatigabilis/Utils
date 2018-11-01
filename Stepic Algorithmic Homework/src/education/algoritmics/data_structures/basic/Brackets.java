package education.algoritmics.data_structures.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Brackets {
    public static void main(String[] args) throws IOException {
        new Brackets().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        char[] input = br.readLine().toCharArray();
        Deque<Bracket> deque = new ArrayDeque<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i] == ')') {
                if (deque.size() != 0 && deque.peek().getCh() == '('){
                    deque.pop();
                } else {
                    System.out.println(i+1);
                    System.exit(0);
                }

            }
            else if (input[i] == ']') {
                if (deque.size() != 0 && deque.peek().getCh() == '['){
                    deque.pop();
                } else {
                    System.out.println(i+1);
                    System.exit(0);
                }
            }
            else if (input[i] == '}') {
                if (deque.size() != 0 && deque.peek().getCh() == '{'){
                    deque.pop();
                } else {
                    System.out.println(i+1);
                    System.exit(0);
                }
            }
            else if (input[i] == '(' || input[i] == '[' || input[i] == '{') deque.push(new Bracket(input[i], i));
        }

        if (deque.size() == 0) System.out.println("Success");
        else System.out.println(deque.peek().getIndex()+1);
    }

    private class Bracket {
        char ch;
        int index;

        public Bracket(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }

        public char getCh() {
            return ch;
        }

        public int getIndex() {
            return index;
        }
    }
}
