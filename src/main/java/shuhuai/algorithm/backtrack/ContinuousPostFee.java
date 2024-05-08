package shuhuai.algorithm.backtrack;

import java.util.Scanner;

public class ContinuousPostFee {
    private int n;
    private int m;
    private int maxValue;
    private int[] result;
    private int max;

    public ContinuousPostFee(int n, int m, int maxValue) {
        setData(n, m, maxValue);
    }

    public void setData(int n, int m, int maxValue) {
        this.n = n;
        this.m = m;
        this.maxValue = maxValue;
        result = new int[n + 1];
        max = 0;
    }

    public int backtrack() {
        int[] x = new int[n + 1];
        int[] y = new int[maxValue + 1];
        for (int i = 1; i <= maxValue; i++) {
            y[i] = Integer.MAX_VALUE;
        }
        x[1] = 1;
        backtrack(2, 1, x, y);
        return max;
    }

    private void backtrack(int i, int r, int[] x, int[] y) {
        for (int j = 0; j <= x[i - 2] * (m - 1); j++) {
            if (y[j] < m) {
                for (int k = 1; k <= m - y[j]; k++) {
                    y[j + x[i - 1] * k] = Math.min(y[j + x[i - 1] * k], y[j] + k);
                }
            }
        }
        while (y[r] < Integer.MAX_VALUE) {
            r++;
        }
        if (i > n) {
            if (r - 1 > max) {
                max = r - 1;
                if (n >= 0) System.arraycopy(x, 1, result, 1, n);
            }
            return;
        }
        int[] z = new int[maxValue + 1];
        if (maxValue >= 0) System.arraycopy(y, 1, z, 1, maxValue);
        for (int j = x[i - 1] + 1; j <= r; j++) {
            x[i] = j;
            backtrack(i + 1, r, x, y);
            if (maxValue >= 0) System.arraycopy(z, 1, y, 1, maxValue);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            int m = sc.nextInt();
            int maxValue = sc.nextInt();
            ContinuousPostFee cf = new ContinuousPostFee(n, m, maxValue);
            System.out.println(cf.backtrack());
            for (int i = 1; i < cf.result.length; i++) {
                System.out.print(cf.result[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}