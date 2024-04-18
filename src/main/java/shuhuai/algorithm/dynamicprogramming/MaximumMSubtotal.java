package shuhuai.algorithm.dynamicprogramming;

import java.util.Scanner;

public class MaximumMSubtotal {
    public int dp(int[] nums, int m) {
        if (m > nums.length || m < 1) {
            return 0;
        }
        int[][] b = new int[m + 1][nums.length + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= nums.length; j++) {
                b[i][j] = 0;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= nums.length - m + i; j++) {
                b[i][j] = b[i][j - 1] + nums[j - 1];
                for (int k = i - 1; k < j; k++) {
                    b[i][j] = Math.max(b[i][j], b[i - 1][k] + nums[j - 1]);
                }
            }
        }
        int sum = 0;
        for (int j = m; j <= nums.length; j++) {
            sum = Math.max(sum, b[m][j]);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] nums = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                nums[i] = Integer.parseInt(split[i]);
            }
            int m = sc.nextInt();
            System.out.println(new MaximumMSubtotal().dp(nums, m));
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}