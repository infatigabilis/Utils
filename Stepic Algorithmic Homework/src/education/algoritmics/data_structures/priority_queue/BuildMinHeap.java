package education.algoritmics.data_structures.priority_queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BuildMinHeap {
    int n;
    int[] A;
    List<String> out = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new BuildMinHeap().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        n = Integer.parseInt(br.readLine());

        String[] line = br.readLine().split(" ");
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(line[i]);
        }

        for (int i = n/2; i >= 0; i--) {
            siftDown(i);
        }

        System.out.println(out.size());
        for (String s : out) {
            System.out.println(s);
        }
    }

    private void siftDown(int i) {
        int minIndex = i;
        if (n > 1 && leftChild(i) < n && A[leftChild(i)] < A[minIndex]) minIndex = leftChild(i);
        if (n > 2 && rightChild(i) < n && A[rightChild(i)] < A[minIndex]) minIndex = rightChild(i);

        if (i != minIndex) {
            out.add(i + " " + minIndex);
            int temp = A[i];
            A[i] = A[minIndex];
            A[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }
}
