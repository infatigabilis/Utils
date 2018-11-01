package education.algoritmics.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by infat on 18.03.2017.
 */
public class Alg4110 {

    class Item implements Comparable<Item>{
        int cost;
        int weight;

        public Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("Item{cost=%d, weight=%d}", cost, weight);
        }

        @Override
        public int compareTo(Item o) {
            return -Long.compare(cost * o.weight, weight * o.cost);
        }
    }

    public void run() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int W = in.nextInt();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(in.nextInt(), in.nextInt());
        }

        Arrays.sort(items);

        double result = 0;
        for (Item item : items) {
            if (W > item.weight) {
                result += item.cost;
                W -= item.weight;
            } else {
                result += (double) item.cost * W / item.weight;
                break;
            }
        }
        System.out.format("%.3f\n", result);
    }
}
