package shuhuai.algorithm.dynamicprogramming;

import java.util.Scanner;

public class FourHanoi {
    private int[] f;
    private int n;
    private int result;

    public FourHanoi(int n) {
        setData(n);
    }

    public void setData(int n) {
        this.n = n;
        f = new int[n + 1];
        result = 0;
    }

    public int dp() {
        f[0] = 0;
        if (n > 0) {
            f[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                f[i] = Math.min(f[i], 2 * f[i - j] + (int) Math.pow(2, j) - 1);
            }
        }
        result = f[n];
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            System.out.println(new FourHanoi(n).dp());
            input = sc.nextLine();
        }
    }
}