package education.algoritmics.data_structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckTreeProperties {
    long[][] input;
    long v;

    public static void main(String[] args) throws IOException {
        new CheckTreeProperties().run();
    }

    private void run() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());

        input = new long[n][3];
        for (int i = 0; i < n; i++) {
            String[] t = br.readLine().split(" ");
            input[i][0] = Long.parseLong(t[0]);
            input[i][1] = Long.parseLong(t[1]);
            input[i][2] = Long.parseLong(t[2]);
        }

        if (input.length == 0) {
            System.out.println("CORRECT");
            System.exit(0);
        }

        Tree root = inputTree(0);
        v = min(root) - 1;

        check(root);

        System.out.println("CORRECT");
    }

    private void check(Tree t) {
        if (t.left != null) check(t.left);

        if (t.value >= v && (t.left == null || t.left.value != t.value)) v = t.value;
        else {
            System.out.println("INCORRECT");
            System.exit(0);
        }

        if (t.right != null) check(t.right);
    }

    private long min(Tree t) {
        if (t.left == null) return t.value;

        return min(t.left);
    }

    private void out(Tree tree) {
        if (tree.left != null) out(tree.left);
        System.out.print(tree.value + " ");
        if (tree.right != null) out(tree.right);
    }

    private Tree inputTree(long i) {
        int k = (int) i;

        Tree tree = new Tree();
        tree.value = input[k][0];
        tree.left = input[k][1] != -1 ? inputTree(input[k][1]) : null;
        tree.right = input[k][2] != -1 ? inputTree(input[k][2]) : null;
        return tree;
    }

    class Tree {
        long value;
        Tree left;
        Tree right;
    }
}
