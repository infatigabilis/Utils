package education.algoritmics.data_structures.priority_queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramAutoAnalize {
    int[] parent;
    int[] rank;

    public static void main(String[] args) throws IOException {
        new ProgramAutoAnalize().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int e = Integer.parseInt(line[1]);
        int d = Integer.parseInt(line[2]);

        parent = new int[n+1];
        rank = new int[n+1];
        for (int i = 0; i < n; i++) {
            makeSet(i);
        }

        for (int i = 0; i < e; i++) {
            line = br.readLine().split(" ");
            union(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        int complete = 1;
        for (int i = 0; i < d; i++) {
            line = br.readLine().split(" ");
            if (find(Integer.parseInt(line[0])) == find(Integer.parseInt(line[1]))) {
                complete = 0;
                break;
            }
        }

        System.out.println(complete);
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = i;
    }

    private int find(int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

    private void union(int i, int j) {
        int i_id = find(i);
        int j_id = find(j);

        if (i_id == j_id) return;

        if (rank[i_id] > rank[j_id]) parent[j_id] = i_id;
        else {
            parent[i_id] = j_id;
            if (rank[i_id] == rank[j_id]) rank[j_id]++;
        }
    }
}
