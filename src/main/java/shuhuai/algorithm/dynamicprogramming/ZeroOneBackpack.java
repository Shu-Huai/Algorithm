package shuhuai.algorithm.dynamicprogramming;

import java.util.Scanner;

public class ZeroOneBackpack {
    private final int[] weights;
    private final int[] values;
    private final int c;
    private final int[][] m;
    private int result;
    private final boolean[] backpack;

    public ZeroOneBackpack(int[] weights, int[] values, int capacity) {
        this.weights = new int[weights.length];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.values = new int[values.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
        this.c = capacity;
        m = new int[weights.length][capacity + 1];
        backpack = new boolean[weights.length];
    }

    public void dp() {
        for (int j = 0; j <= c; j++) {
            if (j >= weights[1]) {
                m[1][j] = values[1];
            } else {
                m[1][j] = 0;
            }
        }
        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j <= c; j++) {
                if (j < weights[i]) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        result = m[weights.length - 1][c];
        int i = m.length - 1;
        int j = m[0].length - 1;
        int p = weights.length - 1;
        while (i >= 1 && j >= 1) {
            if (m[i][j] == m[i - 1][j]) {
                backpack[p--] = false;
            } else {
                backpack[p--] = true;
                j -= weights[i];
            }
            i--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入重量数组：");
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] weights = new int[split.length + 1];
            for (int i = 0; i < split.length; i++) {
                weights[i + 1] = Integer.parseInt(split[i]);
            }
            System.out.println("请输入价值数组：");
            split = sc.nextLine().split(" ");
            int[] values = new int[split.length + 1];
            for (int i = 0; i < split.length; i++) {
                values[i + 1] = Integer.parseInt(split[i]);
            }
            System.out.println("请输入容量：");
            int capacity = sc.nextInt();
            ZeroOneBackpack zOB = new ZeroOneBackpack(weights, values, capacity);
            zOB.dp();
            System.out.println("最大值：" + zOB.result);
            System.out.println("放置情况：");
            for (int i = 1; i < zOB.backpack.length; i++) {
                System.out.print(zOB.backpack[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            System.out.println("请输入重量数组：");
            input = sc.nextLine();
        }
    }
}