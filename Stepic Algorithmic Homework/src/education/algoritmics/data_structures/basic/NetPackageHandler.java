package education.algoritmics.data_structures.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NetPackageHandler {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = in.readLine().split(" ");
        int size = Integer.parseInt(firstLine[0]);
        int N = Integer.parseInt(firstLine[1]);

        Handler handler = new Handler(size);

        for (int i = 0; i < N; i++) {
            String[] t = in.readLine().split(" ");
            int arrival = Integer.parseInt(t[0]);
            int duration = Integer.parseInt(t[1]);

            handler.add(arrival, duration);
        }
    }

    static class Handler {
        private int size;
        private Deque<Integer> buffer = new ArrayDeque<>();

        public Handler(int size) {
            this.size = size;
        }

        private void add(int arrival, int duration) {
            execBefore(arrival);

            if (isFull()) {
                System.out.println(-1);
                return;
            }

            int time = Optional.ofNullable(buffer.peekLast()).orElse(arrival);
            buffer.add(time + duration);

            System.out.println(time);
        }

        private void execBefore(int arrival) {
            while (buffer.peekFirst() != null && arrival >= buffer.peekFirst()) {
                buffer.pollFirst();
            }
        }

        private boolean isFull() {
            if (buffer.size() > size) throw new IllegalStateException();

            return buffer.size() == size;
        }
    }
}
