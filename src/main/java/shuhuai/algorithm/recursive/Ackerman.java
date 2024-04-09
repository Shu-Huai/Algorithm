package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class Ackerman {
    public int recursive(int n, int m) {
        if (n == 1 && m == 0) {
            return 2;
        }
        if (n == 0 & m >= 0) {
            return 1;
        }
        if (n >= 2 && m == 0) {
            return n + 2;
        }
        return recursive(recursive(n - 1, m), m - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] str = input.split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            System.out.println(new Ackerman().recursive(n, m));
            input = sc.nextLine();
        }
    }
}