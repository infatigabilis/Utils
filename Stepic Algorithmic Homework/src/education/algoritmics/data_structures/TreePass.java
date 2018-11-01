package education.algoritmics.data_structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreePass {
    int[][] input;

    public static void main(String[] args) throws IOException {
        new TreePass().run();
    }

    private void run() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());

        input = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] t = br.readLine().split(" ");
            input[i][0] = Integer.parseInt(t[0]);
            input[i][1] = Integer.parseInt(t[1]);
            input[i][2] = Integer.parseInt(t[2]);
        }

        Tree root = inputTree(0);

        inOrderPass(root);
        System.out.println();

        preOrderPass(root);
        System.out.println();

        postOrderPass(root);
        System.out.println();
    }

    private Tree inputTree(int i) {
        Tree tree = new Tree();
        tree.value = input[i][0];
        tree.left = input[i][1] != -1 ? inputTree(input[i][1]) : null;
        tree.right = input[i][2] != -1 ? inputTree(input[i][2]) : null;
        return tree;
    }

    private void inOrderPass(Tree tree) {
        if (tree.left != null) inOrderPass(tree.left);
        System.out.print(tree.value + " ");
        if (tree.right != null) inOrderPass(tree.right);
    }

    private void preOrderPass(Tree tree) {
        System.out.print(tree.value + " ");
        if (tree.left != null) preOrderPass(tree.left);
        if (tree.right != null) preOrderPass(tree.right);
    }

    private void postOrderPass(Tree tree) {
        if (tree.left != null)  postOrderPass(tree.left);
        if (tree.right != null) postOrderPass(tree.right);
        System.out.print(tree.value + " ");
    }

    class Tree {
        int value;
        Tree left;
        Tree right;
    }
}
