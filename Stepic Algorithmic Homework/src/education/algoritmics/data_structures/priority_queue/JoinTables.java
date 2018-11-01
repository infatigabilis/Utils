package education.algoritmics.data_structures.priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoinTables {
    private int max;
    private List<Integer> tables;


    public static void main(String[] args) throws IOException {
        new JoinTables().run();
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = in.readLine().split(" ");
        int M = Integer.parseInt(temp[1]);

        tables = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        max = tables.stream().max(Integer::compareTo).orElse(0);

        for (int i = 0; i < M; i++) {
            temp = in.readLine().split(" ");
            handle(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));

            System.out.println(max);
        }
    }

    private void handle(int destination, int source) {
        int trueDestination = symlink(destination);
        int trueSource = symlink(source);

        if (destination != trueDestination) tables.set(destination-1, -trueDestination);
        if (source != trueSource) tables.set(source-1, -trueSource);

        if (trueDestination == trueSource) return;

        tables.set(trueDestination-1, tables.get(trueDestination-1) + tables.get(trueSource-1));
        tables.set(trueSource-1, -trueDestination);

        if (tables.get(trueDestination-1) > max) max = tables.get(trueDestination-1);
    }

    private int symlink(int tableId) {
        int value = tables.get(tableId-1);
        return value < 0 ? symlink(Math.abs(value)) : tableId;
    }
}
