package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        new Huffman().run();

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " ms");
    }

    private void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("index.in"));
        String text = in.nextLine();

        Map<Character, Integer> alphabet = new HashMap<>();
        for (char ch : text.toCharArray()) {
            if (alphabet.containsKey(ch)) {
                alphabet.put(ch, alphabet.get(ch) + 1);
            }
            else {
                alphabet.put(ch, 1);
            }
        }

        if (alphabet.size() == 1) {
            System.out.println("1 " + text.length());
            System.out.println(text.charAt(0) + ": 0");

            String code = "";
            for (int i = 0; i < text.length(); i++) code += "0";
            System.out.println(code);
            return;
        }

        Map<Character, Node> charNode = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : alphabet.entrySet()) {
            Node node = new LeafNode(entry.getKey(), entry.getValue());
            charNode.put(entry.getKey(), node);
            queue.add(node);
        }

        while(queue.size() > 1)
            queue.add(new ParentNode(queue.poll(), queue.poll()));
        Node rootNode = queue.poll();

        rootNode.buildCode("");

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : text.toCharArray()) {
            stringBuilder.append(charNode.get(ch).code);
        }
        String resultCode = stringBuilder.toString();


        System.out.println(charNode.size() + " " + resultCode.length());
        for (Map.Entry<Character, Node> entry : charNode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().code);
        }
        System.out.println(resultCode);
    }

    class Node implements Comparable<Node> {
        final int sum;
        String code;

        public Node(int sum) {
            this.sum = sum;
        }

        public void buildCode(String code) {
            this.code = code;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }
    }

    class ParentNode extends Node {
        Node left;
        Node right;

        public ParentNode(Node left, Node right) {
            super(left.sum + left.sum);
            this.left = left;
            this.right = right;
        }

        @Override
        public void buildCode(String code) {
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }
    }

    class LeafNode extends Node {
        char ch;

        public LeafNode(char ch, int sum) {
            super(sum);
            this.ch = ch;
        }
    }
}
