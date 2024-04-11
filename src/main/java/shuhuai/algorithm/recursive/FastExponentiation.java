package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class FastExponentiation {
    public int recursive(int a, int n) {
        if (a == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        int result = recursive(a, n / 2);
        if (n % 2 == 0) {
            return result * result;
        }
        return result * result * a;
    }

    public int loop(int a, int n) {
        int result = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                result *= a;
            }
            n /= 2;
            a *= a;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] arr = input.split(" ");
            int a = Integer.parseInt(arr[0]);
            int n = Integer.parseInt(arr[1]);
            System.out.println(new FastExponentiation().recursive(a, n));
            System.out.println(new FastExponentiation().loop(a, n));
            input = sc.nextLine();
        }
    }
}