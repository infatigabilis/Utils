package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Integer number = Integer.valueOf(bufferRead.readLine());
        while(!number.equals(42)) {
            System.out.println(number);
            number = Integer.valueOf(bufferRead.readLine());
        }
    }
}
