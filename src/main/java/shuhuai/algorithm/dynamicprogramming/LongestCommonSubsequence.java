package shuhuai.algorithm.dynamicprogramming;

import java.util.Scanner;

public class LongestCommonSubsequence {
    public String dp(String a, String b) {
        int[][] c = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < b.length(); j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = a.length();
        int j = b.length();
        while (i > 0 && j > 0) {
            if (c[i - 1][j] < c[i][j] && c[i][j - 1] < c[i][j]) {
                sb.append(a.charAt(i - 1));
                i--;
                j--;
            } else if (c[i - 1][j] == c[i][j]) {
                i--;
            } else if (c[i][j - 1] == c[i][j]) {
                j--;
            }
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String b = sc.nextLine();
            String result = new LongestCommonSubsequence().dp(input, b);
            System.out.println(result);
            System.out.println(result.length());
            input = sc.nextLine();
        }
    }
}