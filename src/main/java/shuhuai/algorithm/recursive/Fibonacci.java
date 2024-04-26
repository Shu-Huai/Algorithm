package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class Fibonacci {
    public int recursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public int loop(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int f1 = 1;
        int f2 = 1;
        int result = 0;
        for (int i = 2; i <= n; ++i) {
            result = f1 + f2;
            f2 = f1;
            f1 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            Fibonacci fibonacci = new Fibonacci();
            System.out.println(fibonacci.recursive(n));
            System.out.println(fibonacci.loop(n));
            input = sc.nextLine();
        }
    }
}