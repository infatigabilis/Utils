package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferRead.readLine());

        for (int i = 0; i < T; i++) {
            bufferRead.readLine();

            int temp = 0;
            for (String s : bufferRead.readLine().trim().split(" "))
                temp += Integer.parseInt(s) & 1;

            System.out.println((temp & 1) == 1 ? "IMPOSSIBLE" : "POSSIBLE");
        }
    }
}


