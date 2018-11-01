package education.algoritmics.data_structures.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class SlidedWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        in.readLine();
        List<Integer> A = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int windowSize = Integer.parseInt(in.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < windowSize; i++) {
            while (!deque.isEmpty() && A.get(i) >= A.get(deque.peekLast()))
                deque.pollLast();

            deque.add(i);
        }

        for (int i = windowSize; i < A.size(); i++) {
            System.out.print(A.get(deque.peekFirst()) + " ");

            while (!deque.isEmpty() && deque.peekFirst() <= i - windowSize)
                deque.pollFirst();

            while (!deque.isEmpty() && A.get(i) >= A.get(deque.peekLast()))
                deque.pollLast();

            deque.add(i);
        }

        System.out.print(A.get(deque.peekFirst()) + " ");
    }
}
