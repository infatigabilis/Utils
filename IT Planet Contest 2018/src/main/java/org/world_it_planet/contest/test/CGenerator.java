package org.world_it_planet.contest.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CGenerator {
    public static void main(String[] args) throws IOException {
        Random random = new Random();

        int K = 5;
        int M = 100;
        int N = 100;
        char[] alphabet = "ABCDEFG".toCharArray();

        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        writer.write(M + " " + N + " " + K + "\n");
        for (int i = 0; i < M; i++) {
            char[] line = new char[N];
            for (int j = 0; j < N; j++) {
                writer.write(alphabet[random.nextInt(alphabet.length)]);
            }
            writer.write('\n');
        }

        writer.flush();
    }
}
