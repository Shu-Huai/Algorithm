package shuhuai.algorithm.dynamicprogramming;

import shuhuai.algorithm.utils.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MaximumSubMatrix {
    public int[] dp(int[][] matrix) {
        int result = Integer.MIN_VALUE;
        int beginI = 0;
        int endI = 0;
        int beginJ = 0;
        int endJ = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] cur = new int[matrix[i].length];
            for (int j = i; j < matrix.length; j++) {
                for (int k = 0; k < matrix[j].length; k++) {
                    cur[k] += matrix[j][k];
                }
                MaximumSubtotal ms = new MaximumSubtotal();
                int[] index = ms.dp(cur);
                int curSum = ms.sum(cur, index[0], index[1]);
                if (curSum > result) {
                    result = curSum;
                    beginI = i;
                    endI = j;
                    beginJ = index[0];
                    endJ = index[1];
                }
            }
        }
        return new int[]{beginI, beginJ, endI, endJ};
    }

    public int sum(int[][] matrix, int beginI, int beginJ, int endI, int endJ) {
        int result = 0;
        for (int i = beginI; i <= endI; i++) {
            for (int j = beginJ; j <= endJ; j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            List<int[]> list = new ArrayList<>();
            while (!input.isEmpty()) {
                String[] split = input.split(" ");
                int[] nums = new int[split.length];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = Integer.parseInt(split[i]);
                }
                list.add(nums);
                input = sc.nextLine();
            }
            int[][] matrix = list.toArray(new int[0][]);
            MaximumSubMatrix ms = new MaximumSubMatrix();
            int[] result = ms.dp(matrix);
            Printer<Integer> printer = new Printer<>();
            for (int i = result[0]; i <= result[2]; i++) {
                printer.printArray(Arrays.stream(matrix[i]).boxed().toArray(Integer[]::new), result[1], result[3] + 1);
            }
            System.out.println(ms.sum(matrix, result[0], result[1], result[2], result[3]));
            input = sc.nextLine();
        }
    }
}