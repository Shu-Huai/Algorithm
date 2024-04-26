package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class Factorial {
    public int recursive(int n) {
        if (n == 0) {
            return 1;
        }
        return n * recursive(n - 1);
    }

    public int loop(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            Factorial fibonacci = new Factorial();
            System.out.println(fibonacci.recursive(n));
            System.out.println(fibonacci.loop(n));
            input = sc.nextLine();
        }
    }
}