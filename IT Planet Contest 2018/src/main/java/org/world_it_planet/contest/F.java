package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferRead.readLine().trim());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(bufferRead.readLine().trim());

        for (long i : arr) {
            boolean ans = test(BigInteger.valueOf(i));

            if (ans) System.out.println("1");
            else System.out.println("0");
        }
    }

    private static BigInteger five = BigInteger.valueOf(5);
    private static BigInteger two = BigInteger.valueOf(2);
    private static BigInteger one = BigInteger.valueOf(1);

    private static boolean test(BigInteger y) {
        BigInteger x = y.pow(2).multiply(five).add(y.multiply(two)).add(one);
        BigInteger predict = sqrtN(x);
        return predict.pow(2).equals(x);
    }

    private static BigInteger sqrtN(BigInteger x) {
        if (x .equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
            return x;
        }

        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;

        for (y = x.divide(two); y.compareTo(x.divide(y)) > 0; y = ((x.divide(y)).add(y)).divide(two));

        return y;
    }
}
