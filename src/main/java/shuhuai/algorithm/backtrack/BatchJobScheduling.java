package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BatchJobScheduling {
    private int[][] matrix;
    private int[] array;
    private int minFinish;

    public BatchJobScheduling(int[][] matrix) {
        setData(matrix);
    }

    public void setData(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[0].length);
        }
        array = new int[matrix.length];
        minFinish = Integer.MAX_VALUE;
    }

    public int backtrack() {
        int[] curArray = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            curArray[i] = i;
        }
        backtrack(0, 0, 0, new int[matrix.length], curArray);
        return minFinish;
    }

    public void backtrack(int i, int finish, int finishA, int[] finishB, int[] curArray) {
        if (i >= matrix.length) {
            if (minFinish > finish) {
                minFinish = finish;
                System.arraycopy(curArray, 0, array, 0, matrix.length);
            }
            return;
        }
        for (int j = i; j < matrix.length; j++) {
            finishA += matrix[curArray[j]][0];
            finishB[i] = (Math.max(i - 1 >= 0 ? finishB[i - 1] : 0, finishA)) + matrix[curArray[j]][1];
            finish += finishB[i];
            if (finish < minFinish) {
                int temp = curArray[i];
                curArray[i] = curArray[j];
                curArray[j] = temp;
                backtrack(i + 1, finish, finishA, finishB, curArray);
                temp = curArray[i];
                curArray[i] = curArray[j];
                curArray[j] = temp;
            }
            finishA -= matrix[curArray[j]][0];
            finish -= finishB[i];
        }
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
            int[][] matrix = new int[list.size()][];
            for (int i = 0; i < matrix.length; i++) {
                matrix[i] = list.get(i).clone();
            }
            BatchJobScheduling bs = new BatchJobScheduling(matrix);
            System.out.println(bs.backtrack());
            for (int i = 0; i < bs.array.length; i++) {
                System.out.print(bs.array[i] + " ");
            }
            System.out.println();
            input = sc.nextLine();
        }
    }
}