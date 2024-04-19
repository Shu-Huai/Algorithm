package shuhuai.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalTriangulationOfConvexPolygons {
    public int dp(int[][] q) {
        int[][] m = new int[q.length + 1][q.length + 1];
        for (int i = 1; i < q.length + 1; i++) {
            m[i][i] = 0;
        }
        for (int r = 2; r < q.length + 1; r++) {
            for (int i = 1; i + r - 1 < q.length + 1; i++) {
                int j = i + r - 1;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = m[i][k] + m[k + 1][j] + q[i - 1][k] + q[i - 1][j] + q[k][j];
                    if (temp < min) {
                        min = temp;
                    }
                }
                m[i][j] = min;
            }
        }
        return m[q.length + 1][q.length + 1];
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
            System.out.println(new OptimalTriangulationOfConvexPolygons().dp(matrix));
        }
    }
}