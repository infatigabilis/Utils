package education.algoritmics.methods;

import java.util.Scanner;

// https://stepik.org/lesson/%D0%A7%D0%B8%D1%81%D0%BB%D0%B0-%D0%A4%D0%B8%D0%B1%D0%BE%D0%BD%D0%B0%D1%87%D1%87%D0%B8-13228/step/7?course=%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D1%8B-%D1%82%D0%B5%D0%BE%D1%80%D0%B8%D1%8F-%D0%B8-%D0%BF%D1%80%D0%B0%D0%BA%D1%82%D0%B8%D0%BA%D0%B0-%D0%9C%D0%B5%D1%82%D0%BE%D0%B4%D1%8B&unit=3414
public class Fibonacci {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        System.out.println(arr[n]);
    }
}