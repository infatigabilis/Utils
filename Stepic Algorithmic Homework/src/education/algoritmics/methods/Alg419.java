package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by infat on 18.03.2017.
 */
public class Alg419 {
    class Line implements Comparable<Line> {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(end, o.end);
        }
    }

    public void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lines.add(new Line(in.nextInt(), in.nextInt()));
        }

        lines.sort(Line::compareTo);

        List<Integer> result = new ArrayList<>();
        while (!lines.isEmpty()) {
            Line line = lines.iterator().next();
            result.add(line.end);
            lines.removeIf(new Predicate<Line>() {
                @Override
                public boolean test(Line l) {
                    return l.start <= line.end;
                }
            });
        }

        System.out.println(result.size());
        for (Integer r : result) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}
