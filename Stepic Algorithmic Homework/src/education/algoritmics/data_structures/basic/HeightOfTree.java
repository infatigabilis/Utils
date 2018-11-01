package education.algoritmics.data_structures.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeightOfTree {
    int n;


    public static void main(String[] args) throws IOException {
        new HeightOfTree().run();
    }

    private void run() throws IOException {
//        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        n = Integer.parseInt(br.readLine());

        Map<Integer, Tree> trees = new HashMap<>();

        String[] input = br.readLine().split(" ");

        for (int i = 0; i < n; i++) trees.put(i, new Tree());

        Tree main = null;
        for (int i = 0; i < n; i++) {
            int index = Integer.parseInt(input[i]);

            Tree t = trees.get(i);
            t.setIndex(i);

            if (index == -1) {
                main = t;
                continue;
            }
            Tree parent = trees.get(index);
            parent.setChildren(t);
        }

        System.out.println(getHeight(main));
    }

    private int getHeight(Tree t) {
        int height = 1;
        for (Tree c : t.getChildren()) {
            height = Math.max(height, getHeight(c) + 1);
        }

        return height;
    }

    private class Tree {
        int index = 0;
        ArrayList<Tree> children = new ArrayList<>();

        public Tree() { }

        public int getIndex() {
            return index;
        }

        public ArrayList<Tree> getChildren() {
            return children;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setChildren(Tree child) {
            this.children.add(child);
        }
    }
}
