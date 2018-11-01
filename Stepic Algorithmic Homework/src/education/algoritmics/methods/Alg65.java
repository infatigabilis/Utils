package education.algoritmics.methods;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by infatigabilis on 03.05.17.
 */
public class Alg65 {
    int n;
    int m;
    int[] dotValue;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        new Alg65().exec();
        long endTime = System.currentTimeMillis();
        System.out.println("Total: " + (endTime - startTime) + " ms");
    }

    private void exec() throws IOException {
        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new FileReader("index.in"));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        dotValue = new int[m];

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            segments.add(new Segment(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        input = br.readLine().split(" ");
        List<Dot> dots = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            dots.add(new Dot(Integer.parseInt(input[i]), 0, i));
        }

        endTime = System.currentTimeMillis();
        System.out.println("Read: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        segments.sort(Segment::compareTo);
        dots.sort(Dot::compareTo);

        endTime = System.currentTimeMillis();
        System.out.println("Sort: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        int index = 0;
        int[] segmentStart = new int[n];
        int[] segmentEnd = new int[n];
        int[] dotCount = new int[m];

        for (Segment segment : segments) {
            segmentStart[index] = segment.start;
            segmentEnd[index] = segment.end;
            index++;
        }
        index = 0;

        for (Dot dot : dots) {
            dot.fIndex = index;
            dotValue[index] = dot.value;
            index++;
        }

        endTime = System.currentTimeMillis();
        System.out.println("Feather: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        int lastDot = 0;
        for (int i = 0; i < n; i++) {
            int dotIndex = lastDot;
            for (int j = lastDot; j < m; j++) {
                if (segmentStart[i] > dotValue[j]) {
                    lastDot = j+1;
                    dotIndex = lastDot;
                }
                else {
                    dotIndex = j;
                    break;
                }
            }

            int last = search(segmentEnd[i]);
            for (int k = dotIndex; k <= last; k++) dotCount[k]++;
        }

        dots.sort(new Comparator<Dot>() {
            @Override
            public int compare(Dot dot, Dot t1) {
                return Integer.compare(dot.index, t1.index);
            }
        });

        endTime = System.currentTimeMillis();
        System.out.println("Finding: " + (endTime - startTime) + " ms");

        for (Dot dot : dots) System.out.print(dotCount[dot.fIndex] + " ");
        System.out.println();
    }

    private int search(int k) {
        int l = 0;
        int r = m - 1;
        int ind = 0;
        while (r >= l) {
            ind = (r + l) / 2;
            int value = dotValue[ind];
            if (value == k) {
                return ind;
            }
            else if (value > k) {
                r = ind - 1;
            }
            else {
                l = ind + 1;
            }
        }
        if (k > dotValue[ind]) return ind;
        else return ind - 1;
    }


    class Segment implements Comparable<Segment> {
        int start;
        int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{start= " + this.start + "; end= " + this.end + "}";
        }

        @Override
        public int compareTo(Segment segment) {
            if (this.start == segment.start) {
                return Integer.compare(this.end, segment.end);
            }

            return Integer.compare(this.start, segment.start);
        }
    }

    class Dot implements Comparable<Dot> {
        int value;
        int count;
        int index;
        int fIndex;

        public Dot(int value, int count, int index) {
            this.value = value;
            this.count = count;
            this.index = index;
        }

        @Override
        public String toString() {
            return "{value= " + this.value + "; count= " + this.count + "}";
        }

        @Override
        public int compareTo(Dot dot) {
            return Integer.compare(this.value, dot.value);
        }
    }
}
