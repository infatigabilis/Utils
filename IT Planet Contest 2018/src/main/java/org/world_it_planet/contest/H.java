package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class H {
    private static int module = 1_000_000_007;

    private int number;
    private List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferRead.readLine());

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < T; i++)
            arr.add(Integer.parseInt(bufferRead.readLine()));

        for (Integer i : arr)
            System.out.println(new H(i).run());

//        System.out.println(new H(1001).run());
    }

    public H(int number) {
        this.number = number;
    }

    private void findPrimes() {
        boolean[] primeMtx = new boolean[number+1];

        for (int i = 2; i * i <= number; i++)
            if (!primeMtx[i])
                for (int j = i * 2; j <= number; j += i)
                    primeMtx[j] = true;

        for (int i = 2; i <= number; i++)
            if (!primeMtx[i])
                primes.add(i);
    }

    private long run() {
        findPrimes();

        long result = 1;

        for (Integer prime : primes) {
            int exp = 0;

            int v = prime;
            while (true) {
                int temp = number / v;
                if (temp == 0) break;

                exp += temp;
                v *= prime;
            }

            result = (result * (exp + 1)) % module;
        }

        return result;
    }
}
