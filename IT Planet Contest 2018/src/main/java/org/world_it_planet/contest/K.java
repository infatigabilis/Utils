package org.world_it_planet.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class K {
    private String[] numbers;

    public static void main(String[] args) throws IOException {
        List<String> numbers = new ArrayList<>();

//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(bufferRead.readLine());
//
//        for (int i = 0; i < N; i++)
//            numbers.add(bufferRead.readLine().trim());

        for (int i = 0; i < 1000000; i++) {
            numbers.add("" + (new Random().nextInt(10000)));
        }

        new K(numbers.toArray(new String[numbers.size()])).run();
    }

    public K(String[] numbers) {
        this.numbers = numbers;
    }

    private void run() {
        for (String number : numbers) {
            String answer = find(number);
//            System.out.println(answer);

            int numberInt = Integer.parseInt(number);
            int answerInt = Integer.parseInt(answer);
            int t = tempT(numberInt);

             if (answerInt != t)
                 System.out.println(number + " | " + answer + " | " + t);
        }
    }

    private int tempT(int number) {
        int n = number + 1;
        while (!tempTemp("" + n)) {
            n++;
        }

        return n;
    }

    private boolean tempTemp(String str) {
        if (str.length() % 2 == 0) {
            String sub = str.substring(0, str.length() / 2);
            String reverse = new StringBuilder(str.substring(str.length() / 2)).reverse().toString();

            return sub.equals(reverse);
        } else {
            String sub = str.substring(0, str.length() / 2);
            String reverse = new StringBuilder(str.substring(str.length() / 2 + 1)).reverse().toString();

            return sub.equals(reverse);
        }
    }

    private String find(String number) {
        StringBuilder numberBuilder = new StringBuilder(number);

        int length = number.length();
        int index = (length >> 1) - 1;

        if (number.equals("9")) return "11";
        if (length == 1) return "" + (Integer.parseInt(number) + 1);

        boolean odd = false;
        if ((length & 1) != 0) {
            index++;
            odd = true;
        }

        int i1;
        int i2;
        if (odd) {
            i1 = Integer.parseInt("" + number.charAt(index-1));
            i2 = Integer.parseInt("" + number.charAt(index+1));
        } else {
            i1 = Integer.parseInt("" + number.charAt(index));
            i2 = Integer.parseInt("" + number.charAt(index+1));
        }

        if (i1 > i2) {
            return mirror(numberBuilder, index+1, odd);
        } if (i1 == i2) {
            if (isRightPartBigger(numberBuilder, odd ? index-1 : index, index+1)) {
                return handleNine(numberBuilder, index, odd);
            } else {
                return mirror(numberBuilder, index+1, odd);
            }
        } else {
            return handleNine(numberBuilder, index, odd);
        }
    }

    private String handleNine(StringBuilder numberBuilder, int index, boolean odd) {
        if (numberBuilder.charAt(index) == '9') {
            StringBuilder stringBuilder = getIncrementedNinePart(numberBuilder, index);
            if (stringBuilder == null) return temp(numberBuilder.length());

            numberBuilder = stringBuilder;
        }
        else numberBuilder.setCharAt(index, (char) ((int) numberBuilder.charAt(index) + 1));

        return mirror(numberBuilder, index+1, odd);
    }

    private String temp(int length) {
        char[] seq = new char[length + 1];
        seq[0] = '1';
        for (int i = 1; i < seq.length - 1; i++) seq[i] = '0';
        seq[seq.length-1] = '1';

        return new String(seq);
    }

    private StringBuilder getIncrementedNinePart(StringBuilder numberBuilder, int index) {
        if (index == -1) return null;

        if (numberBuilder.charAt(index) != '9') {
            numberBuilder.setCharAt(index, (char) ((int) numberBuilder.charAt(index) + 1));
            return numberBuilder;
        }
        else {
            numberBuilder.setCharAt(index, '0');
            return getIncrementedNinePart(numberBuilder, index-1);
        }
    }

    private boolean isRightPartBigger(StringBuilder numberBuilder, int index1, int index2) {
        if (index1 < 0)
            return true;

        if (numberBuilder.charAt(index1) < numberBuilder.charAt(index2))
            return true;
        else if (numberBuilder.charAt(index1) == numberBuilder.charAt(index2))
            return isRightPartBigger(numberBuilder, index1-1, index2+1);
        else return false;
    }

    private String mirror(StringBuilder numberBuilder, int index, boolean odd) {
        if (!odd) {
            String subnumber = numberBuilder.substring(0, index);
            return subnumber + new StringBuilder(subnumber).reverse().toString();
        } else {
            String subnumber = numberBuilder.substring(0, index-1);
            return subnumber + numberBuilder.charAt(index-1) + new StringBuilder(subnumber).reverse().toString();
        }
    }
}
