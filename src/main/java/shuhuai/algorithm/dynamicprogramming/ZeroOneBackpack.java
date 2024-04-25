package shuhuai.algorithm.dynamicprogramming;

import java.util.Scanner;

public class ZeroOneBackpack {
    private int[] weights;
    private int[] values;
    private int c;
    private int[][] m;
    private int result;
    private boolean[] backpack;
    private int[][] p;
    private int[] head;

    public ZeroOneBackpack(int[] weights, int[] values, int capacity) {
        setData(weights, values, capacity);
    }

    public void setData(int[] weights, int[] values, int capacity) {
        this.weights = new int[weights.length];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.values = new int[values.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
        this.c = capacity;
        m = new int[weights.length][capacity + 1];
        backpack = new boolean[weights.length];
        p = new int[weights.length * c][2];
        head = new int[weights.length + 2];
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

    public void dpClever() {
        head[0] = 0;
        head[1] = 1;
        p[0][0] = 0;
        p[0][1] = 0;
        int left = 0;
        int right = 0;
        int next = 1;
        for (int i = 1; i < weights.length; i++) {
            int k = left;
            for (int j = left; j <= right; j++) {
                if (p[j][0] + weights[i] > c) {
                    break;
                }
                int y = p[j][0] + weights[i];
                int m = p[j][1] + values[i];
                while (k <= right && p[k][0] < y) {
                    p[next][0] = p[k][0];
                    p[next++][1] = p[k++][1];
                }
                if (k <= right && p[k][0] == y) {
                    if (m < p[k][1]) {
                        m = p[k][1];
                    }
                    k++;
                }
                if (m > p[next - 1][1]) {
                    p[next][0] = y;
                    p[next++][1] = m;
                }
                while (k <= right && p[k][1] == p[next - 1][1]) {
                    k++;
                }
            }
            while (k <= right) {
                p[next][0] = p[k][0];
                p[next++][1] = p[k++][1];
            }
            left = right + 1;
            right = next - 1;
            head[i + 1] = next;
        }
        result = p[next - 1][1];
        int j = p[head[weights.length] - 1][0];
        int m = p[head[weights.length] - 1][1];
        for (int i = weights.length - 1; i >= 1; i--) {
            backpack[i] = false;
            for (int k = head[i - 1]; k <= head[i] - 1; k++) {
                if (p[k][0] + weights[i] == j && p[k][1] + values[i] == m) {
                    backpack[i] = true;
                    j = p[k][0];
                    m = p[k][1];
                    break;
                }
            }
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
            zOB.setData(weights, values, capacity);
            zOB.dpClever();
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