package education.algoritmics.data_structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplayTree {
    private Tree root;

    public static void main(String[] args) throws IOException {
        new SplayTree().run();
    }

    private void run() throws IOException {
        // InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader = new FileReader("index.in");
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            switch(line[0]) {
                case "+":
                    insert(root, Integer.parseInt(line[1]));
                    break;
            }
        }
    }

    private void insert(Tree t, int v) {
        if (root == null) {
            root = new Tree(v);
            return;
        }

        if (t.left == null && t.key > v) {
            t.left = new Tree(v);
            t.left.parent = t;
            splay(t.left);
        }
        else if (t.right == null && t.key < v) {
            t.right = new Tree(v);
            t.right.parent = t;
            splay(t.right);
        }
        else if (t.key > v) insert(t.left, v);
        else insert(t.right, v);
    }

    private void splay(Tree t) {
        if (t.parent == null) return;

        if (t.parent.parent != null) {
            if ((t.parent.left == t) && (t.parent.parent.left == t.parent)) zigzig(t);
            else if ((t.parent.right == t) && (t.parent.parent.right == t.parent)) zagzag(t);
            else if ((t.parent.left == t) && (t.parent.parent.right == t.parent)) zigzag(t);
            else zagzig(t);
        }
        else if (t.parent.right == t) zag(t);
        else zig(t);
    }

    private void zig(Tree t) {
        Tree u = t;
        Tree a = u.parent;

        Tree A = u.left;
        Tree B = u.right;
        Tree C = a.right;

        u.right = a;
        a.parent = u;
        u.left = A;
        if (A != null) A.parent = u;
        a.left = B;
        if (B != null) B.parent = a;
        a.right = C;
        if (C != null) C.parent = a;

        u.parent = null;
        root = u;

        splay(u);
    }

    private void zag(Tree t) {
        Tree u = t;
        Tree a = u.parent;

        Tree A = a.left;
        Tree B = u.left;
        Tree C = u.right;

        u.left = a;
        a.parent = u;
        a.left = A;
        if (A != null) A.parent = a;
        a.right = B;
        if (B != null) B.parent = a;
        u.right = C;
        if (C != null) C.parent = u;

        u.parent = null;
        root = u;

        splay(u);
    }

    private void zigzig(Tree t) {
        Tree parent = t.parent;

        Tree u = t;
        Tree a = t.parent;
        Tree b = a.parent;

        Tree A = u.left;
        Tree B = t.right;
        Tree C = a.right;
        Tree D = b.right;

        u.left = A;
        if (A != null) A.parent = u;
        u.right = a;
        a.parent = u;
        a.left = B;
        if (B != null) B.parent = a;
        a.right = b;
        b.parent = a;
        b.left = C;
        if (C != null) C.parent = b;
        b.right = D;
        if (D != null) D.parent = b;

        u.parent = parent;

        splay(u);
    }

    private void zagzag(Tree t) {
        Tree parent = t.parent;

        Tree u = t;
        Tree a = t.parent;
        Tree b = a.parent;

        Tree A = b.left;
        Tree B = a.left;
        Tree C = u.left;
        Tree D = u.right;

        u.left = a;
        a.parent = u;
        u.right = D;
        if (D != null) D.parent = u;
        a.left = b;
        b.parent = a;
        a.right = C;
        if (C != null) C.parent = a;
        b.left = A;
        if (A != null) A.parent = b;
        b.left = B;
        if (B != null) B.parent = b;

        u.parent = parent;

        splay(u);
    }

    private void zigzag(Tree t) {
        Tree parent = t.parent;

        Tree u = t;
        Tree b = t.parent;
        Tree a = b.parent;

        Tree A = a.left;
        Tree B = u.left;
        Tree C = u.right;
        Tree D = b.right;

        u.left = a;
        a.parent = u;
        u.right = b;
        b.parent = u;
        a.left = A;
        if (A != null) A.parent = a;
        a.right = B;
        if (B != null) B.parent = a;
        b.left = C;
        if (C != null) C.parent = b;
        b.right = D;
        if (D != null) D.parent = b;

        u.parent = parent;

        splay(u);
    }

    private void zagzig(Tree t) {
        Tree parent = t.parent;

        Tree u = t;
        Tree b = t.parent;
        Tree a = b.parent;

        Tree A = b.left;
        Tree B = u.left;
        Tree C = u.right;
        Tree D = a.right;

        u.left = b;
        b.parent = u;
        u.right = a;
        a.parent = u;
        b.left = A;
        if (A != null) A.parent = b;
        b.right = B;
        if (B != null) B.parent = b;
        a.left = C;
        if (C != null) C.parent = a;
        a.right = D;
        if (D != null) D.parent = a;

        u.parent = parent;

        splay(u);
    }

    private class Tree {
        int key;
        Tree parent;
        Tree left;
        Tree right;

        Tree(int v) {
            this.key = v;
        }
    }
}
