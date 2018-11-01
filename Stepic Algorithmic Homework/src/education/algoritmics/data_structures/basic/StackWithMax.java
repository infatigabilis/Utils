package education.algoritmics.data_structures.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMax {
    public static void main(String[] args) throws IOException {
        new StackWithMax().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());

//        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "push":
                    int v = Integer.parseInt(input[1]);
//                    deque.push(v);
                    if (max.size() == 0 || v > max.peek()) max.push(v);
                    else max.push(max.peek());
                    break;
                case "pop":
//                    deque.pop();
                    max.pop();
                    break;
                case "max":
                    System.out.println(max.peek());
                    break;
            }
        }
    }
}
