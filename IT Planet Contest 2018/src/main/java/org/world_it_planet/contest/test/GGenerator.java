package org.world_it_planet.contest.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GGenerator {
    public static void main(String[] args) throws IOException {
        Random random = new Random();

        int N = 100000;

        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        writer.write(N + "\n");
        for (int i = 0; i < N; i++) {
            int action = random.nextInt(100) > 30 ? 1 : 2;
            int favorite = random.nextInt(1_000_000_000) + 1;

            writer.write(action + " " + favorite + "\n");
        }

        writer.flush();
    }
}
