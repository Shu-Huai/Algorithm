package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class RoundRobinSchedule {
    public void recursive(int[][] table, int a, int b, int n) {
        if (n != 1) {
            recursive(table, a, b, n / 2);
            recursive(table, a + n / 2, b, n / 2);
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    table[a + n / 2 + i][b + n / 2 + j] = table[a + i][b + j];
                }
            }
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    table[a + i][b + n / 2 + j] = table[a + n / 2 + i][b + j];
                }
            }
        }
    }

    public int[][] recursive(int n) {
        int[][] table = new int[n][n];
        for (int i = 0; i < table.length; i++) {
            table[i][0] = i + 1;
        }
        recursive(table, 0, 0, table.length);
        return table;
    }


    public int[][] loop(int n) {
        int[][] table = new int[n][n];
        for (int i = 0; i < table.length; i++) {
            table[i][0] = i + 1;
        }
        int r = 1;
        int k = n;
        while (k > 1) {
            for (int i = 0; i < n; i = i + 2 * r) {
                for (int s = 0; s < r; s++) {
                    System.arraycopy(table[i + s], 0, table[i + r + s], r, r);
                }
                for (int s = 0; s < r; s++) {
                    System.arraycopy(table[i + r + s], 0, table[i + s], r, r);
                }
                k--;
            }
            r *= 2;
        }
        return table;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.isEmpty()) {
            int n = Integer.parseInt(s);
            RoundRobinSchedule rr = new RoundRobinSchedule();
            int[][] table = rr.recursive(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }
            table = rr.loop(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }
            s = sc.nextLine();
        }
    }
}