package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class Hanoi {
    public void recursive(int n, int a, int b, int c) {
        if (n == 0) {
            return;
        }
        recursive(n - 1, a, c, b);
        System.out.println(a + "->" + b);
        recursive(n - 1, c, b, a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] arr = input.split(" ");
            int n = Integer.parseInt(arr[0]);
            int a = Integer.parseInt(arr[1]);
            int b = Integer.parseInt(arr[2]);
            int c = Integer.parseInt(arr[3]);
            new Hanoi().recursive(n, a, b, c);
            input = sc.nextLine();
        }
    }
}