package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C {
    private final int K;
    private final int tableHeight;
    private final int tableWidth;
    private final char[][] table;

    private Set<Character> heap = new HashSet<>();

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferRead = new BufferedReader(new FileReader("test.txt"));

        String[] firstLine = bufferRead.readLine().split(" ");
        int M = Integer.parseInt(firstLine[0]);
        int N = Integer.parseInt(firstLine[1]);
        int K = Integer.parseInt(firstLine[2]);

        char[][] table = new char[M][N];

        for (int i = 0; i < M; i++)
            table[i] = bufferRead.readLine().toCharArray();

        new C(K, M, N, table).run();
    }

    public C(int k, int tableHeight, int tableWidth, char[][] table) {
        K = k;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.table = table;
    }

    private void run() {
        long startTime = System.currentTimeMillis();
        long windowEndTime = 0;
        long rectEndTime = 0;

        int result = 0;

        for (Window w : getWindows()) {
            windowEndTime = System.currentTimeMillis();

            for (Rect r : getRects(w)) {
                rectEndTime = System.currentTimeMillis();

//                if(check(r)) result++;
            }
        }

        System.out.println(result);

        long endTime = System.currentTimeMillis();
        System.out.println("time: " + (endTime - startTime) + "ms");
        System.out.println("window: " + (windowEndTime - startTime) + "ms");
        System.out.println("rect: " + (rectEndTime - windowEndTime) + "ms");
        System.out.println("check: " + (endTime - rectEndTime) + "ms");
    }

    private Window[] getWindows() {
        List<Window> windows = new ArrayList<>();
        for (int width = 1; width <= tableWidth; width++) {
            for (int height = 1; height <= tableHeight; height++) {
//                if (width * height < K) continue;
                windows.add(new Window(width, height));
            }
        }

        return windows.toArray(new Window[windows.size()]);
    }

    private Rect[] getRects(Window window) {
        List<Rect> rects = new ArrayList<>();
        for (int x = 0; x < tableWidth; x++) {
            if (x + window.width > tableWidth) break;

            for (int y = 0; y < tableHeight; y++) {
                if (y + window.height > tableHeight) break;

                rects.add(new Rect(x, y, x+window.width-1, y+window.height-1));
            }
        }

        return rects.toArray(new Rect[rects.size()]);
    }

    private boolean check(Rect rect) {
        heap.clear();

        for (int i = rect.y1; i <= rect.y2; i++) {
            for (int j = rect.x1; j <= rect.x2; j++) {
                heap.add(table[i][j]);

                // test performance
                if (heap.size() > K) return false;
            }
        }

        return heap.size() == K;
    }

    class Window {
        final int width;
        final int height;

        public Window(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            String result = "";
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) result += "#";
                result += "\n";
            }

            return result;
        }
    }

    class Rect {
        final int x1;
        final int y1;
        final int x2;
        final int y2;

        public Rect(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public String toString() {
            String result = "";
            for (int y = 0; y < tableHeight; y++) {
                for (int x = 0; x < tableWidth; x++) {
                    if (x >= x1 && x <= x2 && y >= y1 && y <= y2) result += '#';
                    else result += '0';
                }
                result += '\n';
            }
            return result;
        }
    }
}
