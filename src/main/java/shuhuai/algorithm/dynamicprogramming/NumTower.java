package shuhuai.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumTower {
    public List<Object> dp(int[][] a) {
        int[][] s = new int[a.length][a.length];
        System.arraycopy(a[a.length - 1], 0, s[a.length - 1], 0, a.length);
        for (int i = a.length - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                s[i][j] = Math.max(a[i][j] + s[i + 1][j], a[i][j] + s[i + 1][j + 1]);
            }
        }
        int[] result = new int[a.length];
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            result[i] = a[i][temp];
            if (s[i + 1][temp] < s[i + 1][temp + 1]) {
                temp++;
            }
        }
        result[a.length - 1] = a[a.length - 1][temp];
        return new ArrayList<>() {{
            add(s[0][0]);
            add(result);
        }};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i + 1; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            List<Object> result = new NumTower().dp(a);
            System.out.println(result.get(0));
            int[] route = (int[]) result.get(1);
            for (int j : route) {
                System.out.print(j + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}